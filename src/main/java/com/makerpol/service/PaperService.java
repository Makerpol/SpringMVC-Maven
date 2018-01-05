package com.makerpol.service;

import java.util.List;

import com.makerpol.entity.Paper;

public interface PaperService {
	public void addPaper(Paper paper);
	public void deletePaper(Integer id);
	public List<Paper> searchAllPaper();
	public Paper getPaper(Integer id);
	public List<Paper> getPaper(String param);
	public List<Paper> getPaperByName(String param);
	public void upDataPaper(Paper paper);
}
