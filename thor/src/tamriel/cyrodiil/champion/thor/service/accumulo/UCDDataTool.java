package tamriel.cyrodiil.champion.thor.service.accumulo;

import java.net.URI;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.swing.SwingWorker;

import org.apache.accumulo.core.client.BatchWriter;
import org.apache.accumulo.core.client.Connector;
import org.apache.accumulo.core.client.Scanner;
import org.apache.accumulo.core.client.ZooKeeperInstance;
import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Mutation;
import org.apache.accumulo.core.data.Range;
import org.apache.accumulo.core.data.Value;
import org.apache.accumulo.core.security.Authorizations;
import org.apache.accumulo.core.security.ColumnVisibility;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RawLocalFileSystem;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.Reader;
import org.apache.hadoop.io.SequenceFile.Writer;
import tamriel.cyrodiil.champion.thor.bo.AccumuloServerNode;

public class UCDDataTool extends SwingWorker {

    private String zookeepers;
    private String username;
    private String password;
    private String instance;
    private Authorizations authorizations;
    private String directory;
    private List<String> tableNames;
    private Configuration configuration = new Configuration();
    private String operation;
    private AccumuloServerNode associatedNode;
    private Connector connector;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public UCDDataTool(AccumuloServerNode as, String dbInstance, String targetTables, String targetDir) {
        associatedNode = as;
        username = as.getDbAccount();
        password = as.getDbPassword();
        instance = dbInstance;
        zookeepers = as.getZookeeper();
        String tables = targetTables;
        directory = targetDir;
        tableNames = Arrays.asList(tables.split(","));
        ZooKeeperInstance zooInstance = new ZooKeeperInstance(instance, zookeepers);
        try {
            connector = zooInstance.getConnector(username, password.getBytes());
            getUserAuths();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    private boolean getUserAuths() {
        try {

            this.authorizations = connector.securityOperations().getUserAuthorizations(username);
            return true;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }

    }

    public void extract() throws Exception {
        firePropertyChange("status", "", "Initializing Local File System");
        FileSystem fs = new RawLocalFileSystem();
        fs.initialize(new URI("file:///"), configuration);

        for (String tableName : tableNames) {
            Path output = new Path(String.format("%s%s%s", directory, Path.SEPARATOR, tableName));
            firePropertyChange("status", "", String.format("Writing data for table:\r\n %s to:\r\n %s", tableName, output.makeQualified(fs)
                    .toString()));
            System.err.println(String.format("Writing data for table:\r\n %s to:\r\n %s", tableName, output.makeQualified(fs)
                    .toString()));
            Writer writer = SequenceFile.createWriter(fs, configuration, output, Key.class, Value.class);
            if (!connector.tableOperations().exists(tableName)) {
                System.err.println(String.format("No table named %s, skipping", tableName));
                continue;
            }

            Scanner scanner = connector.createScanner(tableName, authorizations);
            scanner.setRange(new Range());
            Iterator<Entry<Key, Value>> iter = scanner.iterator();
            int records = 0;
            while (iter.hasNext()) {
                Entry<Key, Value> entry = iter.next();
                writer.append(entry.getKey(), entry.getValue());
                ++records;
            }
            System.err.println(String.format("Extracted %d records from table:\r\n %s", records, tableName));
            firePropertyChange("status", "", String.format("Extracted %d records from table:\r\n %s", records, tableName));
            writer.close();
            scanner.clearColumns();
            scanner.clearScanIterators();
        }

    }

    public void load() throws Exception {
        FileSystem fs = new RawLocalFileSystem();
        fs.initialize(new URI("file:///"), configuration);

        for (String tableName : tableNames) {
            Path path = new Path(String.format("%s%s%s", directory, Path.SEPARATOR, tableName));
            Reader reader = new SequenceFile.Reader(fs, path, configuration);
            if (!connector.tableOperations().exists(tableName)) {
                System.err.println(String.format("Creating table %s", tableName));
                connector.tableOperations().create(tableName);
            }
            BatchWriter writer = connector.createBatchWriter(tableName, 1024l * 1024l, 0l, 1);
            Key key = new Key();
            Value value = new Value();
            int records = 0;
            while (reader.next(key, value)) {
                Mutation mutation = new Mutation(key.getRow());
                mutation.put(key.getColumnFamily(), key.getColumnQualifier(),
                        new ColumnVisibility(key.getColumnVisibility()), value);
                writer.addMutation(mutation);
                ++records;
            }
            System.err.println(String.format("Added %d records to table %s", records, tableName));
            writer.close();
            reader.close();
        }
    }

    @Override
    protected Object doInBackground() throws Exception {

        try {
            if (operation.equalsIgnoreCase("load")) {
                load();
            } else if (operation.equalsIgnoreCase("extract")) {
                extract();
                firePropertyChange("status", "", "Done.");
            }
        } catch (Exception err) {
            firePropertyChange("status", "", err.getMessage().toString());
        }
        return 0;
    }
}
