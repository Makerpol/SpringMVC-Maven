package com.baidu.ueditor.upload;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import com.makerpol.Utils.VideoUtil;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class BinaryUploader {

	public static final State save(HttpServletRequest request,
			Map<String, Object> conf) {
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile(conf.get("fieldName").toString());

			String savePath = (String) conf.get("savePath");
			String originFileName = multipartFile.getOriginalFilename();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}
			/***********/
			// 自定义
			savePath = PathFormat.parse(savePath, originFileName);

			String[] savePathBySplit_temp = savePath.split("/");
			String temp = "";
			String fileName = savePathBySplit_temp[savePathBySplit_temp.length - 1];
			for (int i = 2; i < savePathBySplit_temp.length - 1; i++) {
				if (i != savePathBySplit_temp.length - 2) {
					temp += savePathBySplit_temp[i] + "/";
				} else {
					temp += savePathBySplit_temp[i];
				}
			}
			String pathTemp = request.getSession().getServletContext().getRealPath("/");
			pathTemp = String.valueOf(pathTemp) + "\\upload\\" + temp;

			File targetFile = new File(pathTemp);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}

			/************/
			// State storageState =
			// StorageManager.saveFileByInputStream(multipartFile.getInputStream(),savePath,
			// maxSize);
			State storageState = StorageManager.saveFileByInputStream(multipartFile.getInputStream(),
					pathTemp + "/" + fileName, maxSize);
			
			if (storageState.isSuccess()) {
				if("true".equals(conf.get("isVideo"))&& !"mp4".equals(suffix)) {
					String[] urlList = VideoUtil.transforToMp4(savePath);
					savePath = urlList[0];
					String iconPath = urlList[1];
					storageState.putInfo("icon", PathFormat.format(iconPath));
				}
				
				storageState.putInfo("url", PathFormat.format(savePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}

			return storageState;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}
	
	private static String transforToMp4(String path) {
		
		return "";
	}
	
	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
}
