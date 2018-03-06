package com.makerpol.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.makerpol.entity.PDF;

public interface FileService {
	public int addFile(PDF file) throws DataAccessException ;
	public int deleteFile(Integer id,String path) throws DataAccessException ;
	public int updataFile(PDF file) throws DataAccessException ;
	
	public List<PDF> getAllFiles(Integer start, Integer num) throws DataAccessException ;
	public  List<PDF> getFileList(String param, Integer start, Integer num) throws DataAccessException ;
	public PDF getFile(Integer id) throws DataAccessException ;
	public PDF getFile(String name) throws DataAccessException ;
	public int getCount(String param) throws DataAccessException ;
}
