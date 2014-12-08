package edu.missouri.hadoop;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class HRReducer extends Reducer<Text,Text,Text,Text>{
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
		List<String> valList = new ArrayList<String>();
		valList.clear();
		for(Text val :  values){
			valList.add(val.toString());
		}
		List<String> friendsList = new ArrayList<String>();
		for(String val: valList){
			String[] friends = val.split(" ");
			for(int i = 0; i < friends.length; i++){
				friendsList.add(friends[i]);
			}
				} 
		/*for(int i = 0; i < friendsList.size(); i++){
			context.write(key,new Text(friendsList.get(i)));
		}*/
		HashSet<String> friendsHashSet = new HashSet<String>();
		String commonFriends = " ";
		for(int i = 0; i < friendsList.size(); i++){
				if(friendsHashSet.contains(friendsList.get(i))){
					commonFriends += friendsList.get(i);
					
				}else{
					friendsHashSet.add(friendsList.get(i));
				}
				//context.write(key,new Text(commonFriends));
		}
		context.write(key,new Text("'s friends --->" + commonFriends));
		//contxt.write(key, new Text(commonFriends));
		
		
	}

}
