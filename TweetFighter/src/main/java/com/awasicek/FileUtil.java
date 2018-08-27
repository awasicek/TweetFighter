package com.awasicek;

public final class FileUtil {

	private FileUtil()
	{
	}
	
	// TODO reconfigure so points to appropriate file when launching from command line
	public static String getURLString(String fileString)
	{
		//this.getClass().getResource("/TweetFighter.css").toString()
		String pathString = FileUtil.class.getResource(fileString).toString();
		System.out.println(pathString);
		return pathString;
	}
}
