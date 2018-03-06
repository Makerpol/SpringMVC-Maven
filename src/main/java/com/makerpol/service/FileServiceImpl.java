package com.makerpol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.makerpol.Utils.FileUtil;
import com.makerpol.dao.FileMapper;
import com.makerpol.entity.PDF;
@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileMapper mapper;
	
	@Override
	public int addFile(PDF file) throws DataAccessException {
		return mapper.addFile(file);
	}

	@Override
	public int deleteFile(Integer id,String path) throws DataAccessException {
		PDF pdf = this.getFile(id);
		FileUtil.remove(path+pdf.getPath());
		FileUtil.remove(path+pdf.getIcon());
		return mapper.deleteFile(id);
	}

	@Override
	public int updataFile(PDF file) throws DataAccessException {
		return mapper.updataFile(file);
	}

	@Override
	public List<PDF> getAllFiles(Integer start, Integer num) throws DataAccessException {
		return mapper.getAllFiles(start, num);
	}

	@Override
	public List<PDF> getFileList(String param, Integer start, Integer num) throws DataAccessException {
		if(param ==null) {
			return mapper.getAllFiles(start, num);
		}
		return mapper.getFileList(param, start, num);
	}

	@Override
	public PDF getFile(Integer id) throws DataAccessException {
		return mapper.getFileById(id);
	}

	@Override
	public PDF getFile(String name) throws DataAccessException {
		return mapper.getFileByName(name);
	}

	@Override
	public int getCount(String param) throws DataAccessException {
		return mapper.getCount(param);
	}
}
