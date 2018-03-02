package com.makerpol.Utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.makerpol.common.Common;
import com.makerpol.controller.FileController;
import com.makerpol.entity.User;


public class FileUtil {
	private static final Logger log = LoggerFactory.getLogger(FileController.class);
	
	
	/**
	 * 单文件上传
	 * @param file
	 * @param req
	 */
	public static Map<String,Object> upLoad(MultipartFile file, HttpServletRequest req) {
		Map<String,Object> map = new HashMap<String,Object>();
		String path =req.getSession().getServletContext().getRealPath("/");
		
		String fileName = file.getOriginalFilename();
		String URL = getPath(req,fileName);
		String type = getStrIndexOf(fileName, ".");
		
		File temp = new File(path+URL);
		
		checkParentFile(temp);
		String icon = null;
		
		try {
			if(!temp.exists()) {
				temp.createNewFile();
			}
			file.transferTo(temp);
			
			if("pdf".equals(type)) {
				icon = generateBookIamge(path+URL);
				map.put("icon", icon);
			}
			
			map.put("code", 200);
			map.put("msg", "success");
			map.put("URL", URL);
			
		}catch(IOException | IllegalStateException e) {
			map.put("msg", "error");
		}
		return map;
	}
	
	/**
	 * 生成PDF的略缩图
	 * @param pdfPath	pdf路径
	 * @return outputFile 生成略缩图路径
	 */
	private static String generateBookIamge(String pdfPath) {
		String outputFile = getBookIamgePath(pdfPath);
		Document document = new Document();
		try {
			document.setFile(pdfPath);
			BufferedImage image = (BufferedImage) document.getPageImage(0, GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, 0f, 1);
			Iterator iter = ImageIO.getImageWritersBySuffix("jpg");
			ImageWriter writer = (ImageWriter) iter.next();
			
			FileOutputStream out = new FileOutputStream(new File(outputFile));
            ImageOutputStream outImage = ImageIO.createImageOutputStream(out);
			
			writer.setOutput(outImage);
			writer.write(image);
			
		} catch (PDFException | PDFSecurityException | IOException | InterruptedException e) {
			log.error(e.getMessage());
		}
		return outputFile;
	}
	
	private static String getBookIamgePath(String path) {
		return path.replace(".pdf", ".jpg");
	}
	
	private static void checkParentFile(File temp) {
		if(!temp.getParentFile().exists()) {
			checkParentFile(temp.getParentFile());
		}
		temp.mkdir();
	}
	
	private static String getFileType(String end) {
		if(checkImageType(end)!=null) {
			return "images";
		}else if(checkVideoType(end)!=null) {
			return "video";
		}else if(checkFileType(end)!=null) {
			return "file";
		}
		return null;
	} 
	
	private static String checkImageType(String end) {
		String image = "png,jpg,jpeg,gif,bmp";
		if(image.indexOf(end)>0) {
			return "images";
		}
		return null;
	}
	
	private static String checkVideoType(String end) {
		String video = "flv,swf, mkv, avi,rm, rmvb, mpeg, mpg,ogg,ogv, mov, wmv, mp4, webm, mp3, wav, mid";
		if(video.indexOf(end)>0) {
			return "video";
		}
		return null;
	}
	
	private static String checkFileType(String end) {
		String file = "rar, zip, tar, gz, 7z, bz2, cab, iso,doc, docx, xls, xlsx, ppt, pptx, pdf, txt, md, xml";
		if(file.indexOf(end)>0) {
			return "file";
		}
		return null;
	}
	
	/**
	 * 获取上传文件的路径
	 * @param req
	 * @param filename
	 * @return
	 */
	private static String getPath(HttpServletRequest req, String filename) {
		String type = getFileType(getStrIndexOf(filename,"."));
		if("images".equals(type)) {
			User user = (User)req.getSession().getAttribute("LoginUser");
			return Common.DF_UPLOAD_PATH +type+"\\"+user.getName()+"\\"+filename;
		}
		return Common.DF_UPLOAD_PATH +type+"\\"+filename;
	}
	
	
	private static String getStrIndexOf(String s,String i) {
		return s.substring(s.indexOf(i)+1).toLowerCase();
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
			log.debug(e.getMessage());
		}finally {
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					log.debug(e.getMessage());
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
	
	public interface Handler
    {
        void doHandle(final String p0);
    }
}
