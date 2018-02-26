package com.makerpol.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.makerpol.common.Common;
import com.makerpol.entity.User;


public class FileUtil {
	private static final Logger logger;
	public static final RuntimeException IgnoreException;
	
	
	/**
	 * 单文件上传
	 * @param file
	 * @param req
	 */
	public static Map<String,Object> upLoad(MultipartFile file, HttpServletRequest req) {
		Map<String,Object> map = new HashMap<String,Object>();
		String path =req.getSession().getServletContext().getRealPath("/");
		User user = (User)req.getSession().getAttribute("LoginUser");
		path += Common.DF_UPLOAD_PATH +user.getName();
		
		String fileName = file.getOriginalFilename();
		String URL = Common.DF_UPLOAD_PATH+user.getName()+"\\"+fileName;
		System.out.println(URL);
		File temp = new File(path+"\\"+fileName);
		
		if(!temp.getParentFile().exists()) {
			temp.getParentFile().mkdir();
		}
	
		try {
			if(!temp.exists()) {
				temp.createNewFile();
			}
			file.transferTo(temp);
			map.put("code", 200);
			map.put("msg", "success");
			map.put("URL", URL);
		}catch(IOException | IllegalStateException e) {
			map.put("msg", "error");
		}
		return map;
	}
	
	/**
	 * 多文件上传
	 * @param upfile
	 * @param req
	 */
	public static Map<String,Object> upLoad(MultipartFile[] upfile,HttpServletRequest req) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<Object> list = new ArrayList<Object>();
		CommonsMultipartResolver MultipartResolver= new CommonsMultipartResolver(req.getSession().getServletContext());
		if(MultipartResolver.isMultipart(req)) {
			MultipartHttpServletRequest multipartReq = (MultipartHttpServletRequest)req;
			Iterator<String> fileNames =multipartReq.getFileNames();
			if(fileNames.hasNext()) {
				MultipartFile mfile = multipartReq.getFile(fileNames.next().toString());
				list.add(upLoad(mfile,req).get("URL"));
			}
		}
		map.put("code", 200);
		map.put("msg", "success");
		map.put("URL", list);
		return map;
	}
	
	/**
	 * 读取文件
	 * @param is
	 * @param charset
	 * @param handler
	 */
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
	
	/**
	 * 删除文件
	 * @param path
	 */
	public static void  remove(String path) {
		File file = new File(path);
		file.delete();
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
