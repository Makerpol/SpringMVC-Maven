package com.makerpol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.makerpol.dao.FileMapper;
import com.makerpol.entity.File;
@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileMapper mapper;
	
	@Override
	public int addFile(File file) throws DataAccessException {
		return mapper.addFile(file);
	}

	@Override
	public int deleteFile(Integer id) throws DataAccessException {
		return mapper.deleteFile(id);
	}

	@Override
	public int updataFile(File file) throws DataAccessException {
		return mapper.updataFile(file);
	}

	@Override
	public List<File> getAllFiles(Integer start, Integer num) throws DataAccessException {
		return mapper.getAllFiles(start, num);
	}

	@Override
	public List<File> getFileList(String param, Integer start, Integer num) throws DataAccessException {
		if(param ==null) {
			return mapper.getAllFiles(start, num);
		}
		return mapper.getFileList(param, start, num);
	}

	@Override
	public File getFile(Integer id) throws DataAccessException {
		return mapper.getFileById(id);
	}

	@Override
	public File getFile(String name) throws DataAccessException {
		return mapper.getFileByName(name);
	}

	@Override
	public int getCount(String param) throws DataAccessException {
		return mapper.getCount(param);
	}
}
