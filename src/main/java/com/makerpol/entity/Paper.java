package com.makerpol.entity;

public class Paper {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_paper.id
     *
     * @mbggenerated
     */
    private Integer id;
    
    private Integer power;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_paper.paper_name
     *
     * @mbggenerated
     */
    private String paperName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_paper.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_paper.show
     *
     * @mbggenerated
     */
    private Integer show;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_paper.date
     *
     * @mbggenerated
     */
    private String date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_paper.author
     *
     * @mbggenerated
     */
    private String author;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_paper.presentersId
     *
     * @mbggenerated
     */
    private Integer presentersid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_paper.text
     *
     * @mbggenerated
     */
    private String text;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_paper.id
     *
     * @return the value of tb_paper.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_paper.id
     *
     * @param id the value for tb_paper.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_paper.paper_name
     *
     * @return the value of tb_paper.paper_name
     *
     * @mbggenerated
     */
    public String getPaperName() {
        return paperName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_paper.paper_name
     *
     * @param paperName the value for tb_paper.paper_name
     *
     * @mbggenerated
     */
    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_paper.status
     *
     * @return the value of tb_paper.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_paper.status
     *
     * @param status the value for tb_paper.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_paper.show
     *
     * @return the value of tb_paper.show
     *
     * @mbggenerated
     */
    public Integer getShow() {
        return show;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_paper.show
     *
     * @param show the value for tb_paper.show
     *
     * @mbggenerated
     */
    public void setShow(Integer show) {
        this.show = show;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_paper.date
     *
     * @return the value of tb_paper.date
     *
     * @mbggenerated
     */
    public String getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_paper.date
     *
     * @param date the value for tb_paper.date
     *
     * @mbggenerated
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_paper.author
     *
     * @return the value of tb_paper.author
     *
     * @mbggenerated
     */
    public String getAuthor() {
        return author;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_paper.author
     *
     * @param author the value for tb_paper.author
     *
     * @mbggenerated
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_paper.presentersId
     *
     * @return the value of tb_paper.presentersId
     *
     * @mbggenerated
     */
    public Integer getPresentersid() {
        return presentersid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_paper.presentersId
     *
     * @param presentersid the value for tb_paper.presentersId
     *
     * @mbggenerated
     */
    public void setPresentersid(Integer presentersid) {
        this.presentersid = presentersid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_paper.text
     *
     * @return the value of tb_paper.text
     *
     * @mbggenerated
     */
    public String getText() {
        return text;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_paper.text
     *
     * @param text the value for tb_paper.text
     *
     * @mbggenerated
     */
    public void setText(String text) {
        this.text = text;
    }
    
    public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}
}