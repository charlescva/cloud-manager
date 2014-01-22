package tamriel.cyrodiil.champion.thor.service.accumulo;

import java.io.IOException;

import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Mutation;
import org.apache.accumulo.core.data.Value;
import org.apache.accumulo.core.security.ColumnVisibility;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class KeyValueToMutation extends Mapper<Key, Value, Text, Mutation> {

	private static final Text EMPTY_TEXT = new Text();

	@Override
	protected void map(Key key, Value value, Context context) throws IOException,
			InterruptedException {
		Mutation mutation = new Mutation(key.getRow());
		ColumnVisibility visibility = new ColumnVisibility(key.getColumnVisibility());
		mutation.put(key.getColumnFamily(), key.getColumnQualifier(), visibility, value);
		context.write(EMPTY_TEXT, mutation);
	}

}
