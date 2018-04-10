package com.makerpol.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import com.makerpol.entity.Video;

public interface VideoService {
	public List<Map> getAllVideos(String param, Integer start, Integer num) throws DataAccessException;
	public int getCount(String param) throws DataAccessException;
	public List<Video> getVideoList(String param,Integer start, Integer num) throws DataAccessException ;
	public Video getVideo(Integer id) throws DataAccessException ;
	public Video getVideo(String name) throws DataAccessException ;
	public List<String> getUserNameList(List<Integer> list) throws DataAccessException ;
	public void updataVideo(Video video) throws DataAccessException ;
	public void deleteVideo(Integer id,String path) throws DataAccessException ;
	public void addVideo(Video video) throws DataAccessException ;
}
