package com.makerpol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.makerpol.Utils.FileUtil;
import com.makerpol.dao.VideoMapper;
import com.makerpol.entity.Video;

@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	private VideoMapper mapper;
	
	@Override
	public List getAllVideos(String param, Integer start, Integer num) throws DataAccessException {
		if(param!=null&& param !="") {
			return mapper.getVideoList1(param, start, num);
		}
		return mapper.getAllVideos(start, num);
	}

	@Override
	public int getCount(String param) throws DataAccessException {
		return mapper.getCount(param);
	}

	@Override
	public List getVideoList(String param, Integer start, Integer num) throws DataAccessException {
		return mapper.getVideoList1(param, start, num);
	}

	@Override
	public Video getVideo(Integer id) throws DataAccessException {
		return mapper.getVideoById(id);
	}

	@Override
	public Video getVideo(String name) throws DataAccessException {
		return mapper.getVideoByName(name);
	}

	@Override
	public List<String> getUserNameList(List<Integer> list) throws DataAccessException {
		return mapper.getUserNameList(list);
	}

	@Override
	public void updataVideo(Video video) throws DataAccessException {
		mapper.updataVideo(video);
	}

	@Override
	public void deleteVideo(Integer id,String path) throws DataAccessException {
		Video video = this.getVideo(id);
		FileUtil.remove(path+video.getIcon());
		FileUtil.remove(path+video.getPath());
		mapper.deleteVideo(id);
	}

	@Override
	public void addVideo(Video video) throws DataAccessException {
		
		if(video.getPaperid()!=0) {
			Video v = mapper.getVideoByPaperId(video.getPaperid());
			if(v!=null&&v.getPaperid()!=0) {
				mapper.updataVideo(video);
			}
		}else {
			mapper.addVideo(video);
		}
	}
}
