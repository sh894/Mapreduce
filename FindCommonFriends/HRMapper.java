package edu.missouri.hadoop;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class HRMapper extends Mapper<Object, Text, Text, Text> {
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String inputLine = value.toString();
		String[] personFriendsArray = inputLine.split(":");
		String personID = personFriendsArray[0];
		String[] friendsList = personFriendsArray[1].split(" ");
		String outkey = " ";
		for(String aFriend : friendsList){
			if(personID.compareTo(aFriend) < 0){
				 outkey = personID + aFriend;
				//context.write(new Text(outkey), new Text(personFriendsArray[1]));
			}else if(personID.compareTo(aFriend) > 0){
				 outkey =  aFriend + personID;
				//context.write(new Text(outkey), new Text(personFriendsArray[1]));
			}
			context.write(new Text(outkey), new Text(personFriendsArray[1]));
		}
		
		/*String[] personID = new String[personFriendsArray.length / 2];
		String[] friendsArray = new String[personFriendsArray.length / 2];
		int personNum = 0;
		int friendsNum = 1;
		//String outkey = key.toString();
		String outkey = "";
		
		for(int i = 0; i < personID.length; i++){
			personID[i] = personFriendsArray[personNum];
			personNum  = personNum + 2;
		}
		for(int i = 0; i < friendsArray.length; i++){
			friendsArray[i] = personFriendsArray[friendsNum];
			friendsNum = friendsNum + 2;
		}*/
		/*//just for method 1 to provide some mind
		for(int i = 0; i < personID.length; i++){
			String[] single = friendsArray[i].split(" ");
			for(int j = 0; j < single.length; j++){
					if(personID[i].compareTo(single[j]) < 0){
						outkey = personID[i] + single[j];
					}else{
						outkey =  single[j] + personID[i];
					}
					context.write(new Text(outkey), new Text(friendsArray[i]));
			}
			*/
		/*
		//method 1
		for(int i = 0; i < personID.length; i++){
			char[] single = friendsArray[i].toCharArray();
			for(int j = 0; j < single.length; j++){
				String aFriend = "";
				if(single[j] != ' '){
					 aFriend =  String.valueOf(single[j]);
					 if(personID[i].compareTo(aFriend) < 0){
							outkey = personID[i] + aFriend;
						}else if(personID[i].compareTo(aFriend) > 0){
							outkey =  aFriend + personID[i];
						}
					 context.write(new Text(outkey.toString()), new Text(friendsArray[i].toString()));
					 }
				}	
			}
			*/
		//method 2
		/*
		int i = 0;
		for(String friendsList : friendsArray){
			String[] friends = friendsList.split(" ");
			for(int j = 0; j < friends.length; j++){
				if(personID[i].compareTo(friends[j]) < 0){
					outkey = personID[i] + friends[j];
				}else if(personID[i].compareTo(friends[j]) > 0){
					outkey = friends[j] + personID[i];
				}
				System.out.println("context" + " i: " + context);
				 context.write(new Text(outkey.toString()), new Text(friendsArray[i].toString()));
			}
			i++;
		}*/
		
		}
		
	}

