package com.makerpol.service;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.makerpol.entity.Video;

public interface VideoService {
	public List<Video> getAllVideos(String param, Integer start, Integer num) throws DataAccessException;
	public int getCount(String param) throws DataAccessException;
	public List<Video> getVideoList(String param,Integer start, Integer num) throws DataAccessException ;
	public Video getVideo(Integer id) throws DataAccessException ;
	public Video getVideo(String name) throws DataAccessException ;
	public void updataVideo(Video video) throws DataAccessException ;
	public void deleteVideo(Integer id) throws DataAccessException ;
	public void addVideo(Video video) throws DataAccessException ;
}
