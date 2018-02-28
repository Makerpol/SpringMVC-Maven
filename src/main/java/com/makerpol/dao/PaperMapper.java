package com.makerpol.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.makerpol.entity.Paper;

public interface PaperMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id) throws DataAccessException;
    
    int deleteByInnerJoin(Integer id) throws DataAccessException;
    
    public List<Paper> getPaperList(Integer start,Integer num) throws DataAccessException;
    
    public int getPaperCount(String param) throws DataAccessException;
    
    public List<Paper> getPaperListByName(String param,Integer start, Integer num) throws DataAccessException;
    
    public List<Paper> getPaperListByTime(String starTime, String endTime) throws DataAccessException;
    
    public List<Map> getPaperTypeList() throws DataAccessException;
    
    Object checkVideo(Integer id) throws DataAccessException;
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated
     */
    int insert(Paper record) throws DataAccessException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated
     */
    int insertSelective(Paper record) throws DataAccessException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated
     */
    Paper selectByPrimaryKey(Integer id) throws DataAccessException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Paper record) throws DataAccessException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Paper record) throws DataAccessException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Paper record) throws DataAccessException;
}