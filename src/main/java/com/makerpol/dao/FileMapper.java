package com.makerpol.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.makerpol.entity.File;

public interface FileMapper {
	int addFile(File file) throws DataAccessException ;
	int deleteFile(Integer id) throws DataAccessException ;
	int updataFile(File file) throws DataAccessException ;
	
	List<File> getAllFiles(Integer start, Integer num) throws DataAccessException ;
	List<File> getFileList(String param, Integer start, Integer num) throws DataAccessException ;
	File getFileById(Integer id) throws DataAccessException ;
	File getFileByName(String name) throws DataAccessException ;
	int getCount(String param) throws DataAccessException ;
}
