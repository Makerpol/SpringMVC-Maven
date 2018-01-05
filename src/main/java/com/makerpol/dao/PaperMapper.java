package com.makerpol.dao;

import java.util.List;

import com.makerpol.entity.Paper;

public interface PaperMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);
    
    public List<Paper> searchAllPaper();
    
    public List<Paper> getPaperByName(String param);
    
    public List<Paper> getPaperList(String param);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated
     */
    int insert(Paper record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated
     */
    int insertSelective(Paper record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated
     */
    Paper selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Paper record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Paper record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Paper record);
}