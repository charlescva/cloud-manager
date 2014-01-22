package tamriel.cyrodiil.champion.thor.service.accumulo;

import java.util.Arrays;
import java.util.List;

import org.apache.accumulo.core.client.mapreduce.AccumuloInputFormat;
import org.apache.accumulo.core.client.mapreduce.AccumuloOutputFormat;
import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Value;
import org.apache.accumulo.core.security.Authorizations;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.google.common.collect.Lists;

public class MapReduceCopy extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		System.err.println("Starting job to copy tables");
		Configuration conf = getConf();

		List<String> inputTableNames = Lists.newArrayList(conf.getStringCollection(Constants.INPUT_TABLE_NAMES));
		List<String> outputTableNames = Lists.newArrayList(conf.getStringCollection(Constants.OUTPUT_TABLE_NAMES));

		if (inputTableNames.size() != outputTableNames.size()) {
			throw new IllegalArgumentException("Table names must be of the same length");
		}

		for (int i = 0; i < inputTableNames.size(); i++) {
			String inputTableName = inputTableNames.get(i);
			String outputTableName = outputTableNames.get(i);

			CopyConf inputConf = createCopyConf(Constants.INPUT_PREFIX, conf);
			System.err.println("Input configuration: " + inputConf.toString());
			CopyConf outputConf = createCopyConf(Constants.OUTPUT_PREFX, conf);
			System.err.println("Output configuration: " + outputConf.toString());

			Job job = new Job(conf);
			job.setJobName(String.format("Copy table from %s:%s:%s to %s:%s:%s", inputConf.getZookeepers(),
					inputConf.getInstance(), inputTableName, outputConf.getZookeepers(), outputConf.getInstance(),
					outputTableName));
			/* input configuration */

			AccumuloInputFormat.setInputInfo(job.getConfiguration(), inputConf.getUsername(), inputConf.getPasswd(),
					inputTableName, inputConf.getAuths());
			AccumuloInputFormat.setZooKeeperInstance(job.getConfiguration(), inputConf.getInstance(),
					inputConf.getZookeepers());

			/* output configuration */
			AccumuloOutputFormat.setZooKeeperInstance(job.getConfiguration(), outputConf.getInstance(),
					outputConf.getZookeepers());
			AccumuloOutputFormat.setOutputInfo(job.getConfiguration(), outputConf.getUsername(),
					outputConf.getPasswd(), true, outputTableName);

			job.setOutputFormatClass(AccumuloOutputFormat.class);
			job.setInputFormatClass(AccumuloInputFormat.class);
			job.setJarByClass(this.getClass());
			job.setMapOutputKeyClass(Key.class);
			job.setMapOutputValueClass(Value.class);
			job.setOutputKeyClass(Key.class);
			job.setOutputValueClass(Value.class);
			job.setNumReduceTasks(0);
			job.setMapperClass(KeyValueToMutation.class);
			job.waitForCompletion(true);
		}
		return 0;

	}

	public static void main(String[] args) throws Exception {
		MapReduceCopy mapReduceCopy = new MapReduceCopy();
		int status = ToolRunner.run(mapReduceCopy, args);
		System.exit(status);
	}

	public CopyConf createCopyConf(String prefix, Configuration conf) {
		String format = "%s.%s";
		String user = conf.get(String.format(format, prefix, Constants.USER_PROPERTY));
		String passwd = conf.get(String.format(format, prefix, Constants.PASSWD_PROPERTY));
		String auths = conf.get(String.format(format, prefix, Constants.AUTHS_PROPERTY));
		String zookeepers = conf.get(String.format(format, prefix, Constants.ZOO_PROPERTY));
		String instance = conf.get(String.format(format, prefix, Constants.INSTANCE_PROPERTY));
		return new CopyConf(user, passwd, auths, zookeepers, instance);
	}

	private static class CopyConf {
		private String username;
		private byte[] passwd;
		private Authorizations auths;
		private String zookeepers;
		private String instance;

		public CopyConf(String username, String passwd, String auths, String zookeepers, String instanceName) {
			this.username = username;
			this.passwd = passwd.getBytes();
			this.auths = new Authorizations(auths.split(","));
			this.zookeepers = zookeepers;
			this.instance = instanceName;
		}

		public String getUsername() {
			return username;
		}

		public byte[] getPasswd() {
			return passwd;
		}

		public Authorizations getAuths() {
			return auths;
		}

		public String getZookeepers() {
			return zookeepers;
		}

		public String getInstance() {
			return instance;
		}

		@Override
		public String toString() {
			return "CopyConf [username=" + username + ", passwd=" + Arrays.toString(passwd) + ", auths=" + auths
					+ ", zookeepers=" + zookeepers + ", instance=" + instance + "]";
		}
	}
}
