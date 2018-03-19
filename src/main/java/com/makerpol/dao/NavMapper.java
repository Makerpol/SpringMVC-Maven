package com.makerpol.dao;

import java.util.List;

import com.makerpol.entity.Nav;

public interface NavMapper {
    
	List<Nav> getNavsByType(Integer type);
	
	List<Nav> getAll();
	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_navs
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_navs
     *
     * @mbggenerated
     */
    int insert(Nav record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_navs
     *
     * @mbggenerated
     */
    int insertSelective(Nav record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_navs
     *
     * @mbggenerated
     */
    Nav selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_navs
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Nav record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_navs
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Nav record);
}