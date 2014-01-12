
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tamriel.cyrodiil.champion.thor.service.workers;

import java.io.File;

import java.net.URI;
import javax.swing.SwingWorker;
import tamriel.cyrodiil.champion.thor.service.hadoop.HdfsConnector;

/**
 * @author Charles
 *
 *
 *
 */
public class HdfsSwingWorker extends SwingWorker<String, Integer> {

    private String uri;
    private String soureFile;
    private String targetFolder;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getSoureFile() {
        return soureFile;
    }

    public void setSoureFile(String soureFile) {
        this.soureFile = soureFile;
    }

    public String getTargetFolder() {
        return targetFolder;
    }

    public void setTargetFolder(String targetFolder) {
        this.targetFolder = targetFolder;
    }

    @Override
    protected String doInBackground() {
        int totalcopied = 0;
        HdfsConnector hdfs = null;
        try {
            URI hdfsUrl = new URI(uri);

            hdfs = new HdfsConnector(hdfsUrl.getHost(), hdfsUrl.getPort());

            if (!hdfs.exists(targetFolder)) {
                hdfs.mkdirs(targetFolder);
            }
            hdfs.copyToHdfs(new File(soureFile), targetFolder);
            totalcopied = new Integer(hdfs.getTotalCopied());
            hdfs.destroy();

        } catch (Exception err) {
            err.printStackTrace();
            return "Failed.  See Log for details.";
        } finally {
            StringBuilder response = new StringBuilder("File Sent. Copied " + totalcopied + " bytes.");

            firePropertyChange("sendreport", "", response.toString());

            return response.toString();
        }

    }
}
