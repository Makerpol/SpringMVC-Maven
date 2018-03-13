package com.makerpol.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VideoUtil {
	
	private static final Logger log = LoggerFactory.getLogger(VideoUtil.class);
	
	public static String[] transforToMp4(String videoPath) {
		String temp = getOutPath(videoPath);
		StringBuffer videoCmd = new StringBuffer();
		videoCmd.append(getClassPath());
		videoCmd.append("\\ffmpeg -i ");
		videoCmd.append(videoPath);
		videoCmd.append(" -vcodec libx264 -y -r 29.97 -b 768k -ar 24000 -ab 64k -s 1280x720 ");
		videoCmd.append(temp);
		videoCmd.append(".mp4");
		run(videoCmd.toString());
		
		StringBuffer imgCmd = new StringBuffer();
		imgCmd.append(getClassPath());
		imgCmd.append("\\ffmpeg -ss 00:00:04 -i ");
		imgCmd.append(temp);
		imgCmd.append(".mp4");
		imgCmd.append(" -f image2 -y ");
		imgCmd.append(temp);
		imgCmd.append(".jpg");
		run(imgCmd.toString());
		
		FileUtil.remove(videoPath);
		String[] r = {temp+".mp4",temp+".jpg"};
		return r;
	}
	
	//判断系统类型是否是windows
	private static boolean isWindows() {
		String osname = System.getProperty("os.name");
		if(osname.startsWith("Win")) {
			return true;
		}
		return false;
	}
	
	private static String checkFilePath(String filePath) {
		
		File file = new File(filePath);
		System.out.println(file.getAbsolutePath());
		
		if(!file.exists()) {
			filePath = getProjectPath()+"\\WebContent"+filePath;
		}
		return filePath;
	}
	
	private static String getOutPath(String path) {
		path = checkFilePath(path);
		return path.substring(0,path.lastIndexOf("."));
	}
	
	//获取当前类的绝对路径
	private static String getClassPath() {
		return new File(VideoUtil.class.getResource("/").getPath()).getPath();
	}
	
	private static String getProjectPath() {
		return System.getProperty("user.dir");
	}
	
	private static void run(String cmd) {
		Runtime runtime = Runtime.getRuntime();
		try {
			log.debug(cmd);
			Process pro = runtime.exec(cmd);
			InputStream input = pro.getErrorStream();
			InputStreamReader reader = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(reader);
			String msg = null;
			
			while((msg=br.readLine())!=null) {
				log.debug(msg);
			}
			log.debug(pro.exitValue()+"");
			
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
	
	public static void main(String[] arg) {
		checkFilePath("\\upload\\video\\20180367035428.avi");
		//log.debug(isWindows()+"");
		//transforToMp4("F:\\JAVAworkspace\\SpringMVC-Maven\\WebContent\\upload\\video\\20180367035428.avi");
		//System.out.println(getClassPath());
		//String out = getOutPath("F:\\JAVAworkspace\\SpringMVC-Maven\\WebContent\\upload\\video\\20180367035428.avi");
		//System.out.println(out);
	}
}
