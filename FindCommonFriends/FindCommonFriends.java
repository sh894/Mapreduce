package edu.missouri.hadoop;
import java.io.IOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.GenericOptionsParser;

public class FindCommonFriends {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		if (args.length != 2) {
			System.err.println("Usage: FindCommonFriends <in> <out>");
			System.exit(2);
			}
		Job job = Job.getInstance(new Configuration());
			job.setJobName("FindCommonFriends");
			job.setJarByClass(FindCommonFriends.class);
			job.setNumReduceTasks(1);
			job.setMapperClass(HRMapper.class);
			job.setReducerClass(HRReducer.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			//job.setInputFormatClass(KeyValueTextInputFormat.class);
			//job.setOutputFormatClass(TextOutputFormat.class);
			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			System.out.println(HRMapper.class);
			System.exit(job.waitForCompletion(true) ? 0 : 1);
			
			
		/*
			JobClient client = new JobClient();
			JobConf conf = new JobConf();
			conf.setJobName("FindCommonFriends");
			conf.setJarByClass(FindCommonFriends.class);
			conf.setNumReduceTasks(1);
			conf.setMapperClass(HRMapper.class);
			conf.setReducerClass( HRReducer.class);
			conf.setOutputKeyClass(Text.class);
			conf.setOutputValueClass(Text.class);
			FileInputFormat.addInputPath(conf, new Path(args[0]));
			FileOutputFormat.setOutputPath(conf, new Path(args[1]));
			*/
			}
	}


