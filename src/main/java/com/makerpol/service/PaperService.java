package com.makerpol.service;

import java.util.List;

import com.makerpol.entity.Paper;

public interface PaperService {
	
	//�������
	public void addPaper(Paper paper);  
	//ɾ������
	public void deletePaper(Integer id);
	//����������Ϣ
	public void upDataPaper(Paper paper);
	//��ȡ������Ϣ
	public Paper getPaper(Integer id);
	//��ȡ��������
	public int getPaperCount(String param);
	
	public List<Paper> getPaper(String param);
	public List<Paper> getPaper(String param,Integer start, Integer num);
	
	public List<Paper> getPaper(String starTime,String endTime);
	public List<List> getPaperTypeList();	
}
