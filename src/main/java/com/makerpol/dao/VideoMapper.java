package com.makerpol.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.makerpol.entity.Video;

public interface VideoMapper {
	public List<Video> getAllVideos(Integer start, Integer num) throws DataAccessException;
	public Video getVideoByName(String name) throws DataAccessException;
	public Video getVideoById(Integer id) throws DataAccessException;
	public List<Video> getVideoList(String param,Integer start, Integer num) throws DataAccessException;
	public int getCount(String param) throws DataAccessException;
	public Video getVideoByPaperId(Integer paperid) throws DataAccessException;
	
	public void updataVideo(Video video) throws DataAccessException;
	public void deleteVideo(Integer id) throws DataAccessException;
	public void addVideo(Video video) throws DataAccessException;
}
