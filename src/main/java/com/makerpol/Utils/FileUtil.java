package com.makerpol.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;


public class FileUtil {
	private static final Logger logger;
	private static final String UTF8 = "UTF-8";
	private static final String GBK = "GBK";
	public static final RuntimeException IgnoreException;
	
	public static void read(final InputStream is, final String charset, final Handler handler){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is,charset));
			for(String lineString = reader.readLine();lineString != null;lineString = reader.readLine()) {
				handler.doHandle(lineString);
			}
		} catch (IOException e) {
			FileUtil.logger.warning(e.getMessage());
		}finally {
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					FileUtil.logger.warning(e.getMessage());
				}
			}
		}
	}
	
	
	static {
        logger = Logger.getLogger(FileUtil.class.getName());
        IgnoreException = new RuntimeException();
    }
	
	public interface Handler
    {
        void doHandle(final String p0);
    }
}
