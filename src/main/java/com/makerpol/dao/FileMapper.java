package com.makerpol.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.makerpol.entity.PDF;

public interface FileMapper {
	int addFile(PDF file) throws DataAccessException ;
	int deleteFile(Integer id) throws DataAccessException ;
	int updataFile(PDF file) throws DataAccessException ;
	
	List<PDF> getAllFiles(Integer start, Integer num) throws DataAccessException ;
	List<PDF> getFileList(String param, Integer start, Integer num) throws DataAccessException ;
	PDF getFileById(Integer id) throws DataAccessException ;
	PDF getFileByName(String name) throws DataAccessException ;
	int getCount(String param) throws DataAccessException ;
}
