package com.makerpol.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.makerpol.entity.File;

public interface FileService {
	public int addFile(File file) throws DataAccessException ;
	public int deleteFile(Integer id) throws DataAccessException ;
	public int updataFile(File file) throws DataAccessException ;
	
	public List<File> getAllFiles(Integer start, Integer num) throws DataAccessException ;
	public  List<File> getFileList(String param, Integer start, Integer num) throws DataAccessException ;
	public File getFile(Integer id) throws DataAccessException ;
	public File getFile(String name) throws DataAccessException ;
	public int getCount(String param) throws DataAccessException ;
}
