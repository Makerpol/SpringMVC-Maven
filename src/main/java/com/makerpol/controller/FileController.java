package com.makerpol.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.baidu.ueditor.ActionEnter;
import com.makerpol.entity.User;

@Controller("fileController")
public class FileController {
	
	/**
	 * º”‘ÿconfig.json≈‰÷√Œƒº˛
	 */ 
	@RequestMapping(value="config")
	public void config(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		request.setCharacterEncoding( "utf-8" );
		response.setHeader("Content-Type" , "text/html");
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String ae = new ActionEnter( request, rootPath).exec();
		PrintWriter pw =response.getWriter();
		pw.write(ae);
		pw.flush();
		pw.close();
	}
	
	
	/*@RequestMapping("config1")
    @ResponseBody
    public String viewConfig(String action, HttpServletRequest request, HttpServletResponse response) {

        String rootPath = request.getSession().getServletContext().getRealPath("/");
        
        
        System.out.println(action);
        System.out.println(rootPath+"\\UEditor\\jsp\\config.json");
        // return new ActionEnter( request, rootPath ).exec();
        if ("config".equals(action)) {
            try {
                return readFile(rootPath + "\\UEditor\\jsp\\config.json");
            } catch (IOException e) {
                e.printStackTrace();
                return "{\"state\":\"flie is not exist!\"}";
            }
        } 
        else {
            return "{\"state\":\"Can not!\"}";
        }
    }
	
	@RequestMapping(value="/upload")
	@ResponseBody
	public Map<String, Object> upload(@RequestParam MultipartFile[] upfile,HttpServletRequest req) throws IllegalStateException, IOException {
		CommonsMultipartResolver MultipartResolver= new CommonsMultipartResolver(req.getSession().getServletContext());
		String path =req.getSession().getServletContext().getRealPath("/");
		User user = (User)req.getSession().getAttribute("user");
		path += "\\upload\\"+user.getName();
		System.out.println(FileController.class.getName()+"path : "+ path);
		System.out.println(FileController.class.getName()+"userName : "+ user.getName());
		
		List<String> list = new ArrayList<String>();
		//
		if(MultipartResolver.isMultipart(req)) {
			MultipartHttpServletRequest  multiReq = (MultipartHttpServletRequest)req;
			Iterator<?> ite = multiReq.getFileNames();
			while(ite.hasNext()) {
				MultipartFile mf = multiReq.getFile(ite.next().toString());
				
				if(mf!=null) {
					String fileName = mf.getOriginalFilename();
					File temp = new File(path+"\\"+fileName);
					if(!temp.getParentFile().exists()) {
						temp.getParentFile().mkdir();
					}
					if(!temp.exists()) {
						temp.createNewFile();
					}
					mf.transferTo(temp);
					list.add(path+"\\"+fileName);
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		map.put("msg", "success");
		map.put("url", list);
		return map;
	} 
	
	private String readFile(String path) throws IOException {
		StringBuilder builder = new StringBuilder();
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
			BufferedReader bfReader = new BufferedReader(reader);

			String tmpContent = null;
			while ((tmpContent = bfReader.readLine()) != null) {
				builder.append(tmpContent);
			}
			bfReader.close();
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		return filter(builder.toString());
	}

    private String filter(String input) {
        //return input.replaceAll("/\\*[\\s\\S]*?\\", "");
*/   /* }*/
}
