package com.makerpol.entity;

public class Nav {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_navs.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_navs.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_navs.icon
     *
     * @mbggenerated
     */
    private String icon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_navs.herf
     *
     * @mbggenerated
     */
    private String herf;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_navs.spread
     *
     * @mbggenerated
     */
    private Integer spread;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_navs.level
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_navs.id
     *
     * @return the value of tb_navs.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_navs.id
     *
     * @param id the value for tb_navs.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_navs.title
     *
     * @return the value of tb_navs.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_navs.title
     *
     * @param title the value for tb_navs.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_navs.icon
     *
     * @return the value of tb_navs.icon
     *
     * @mbggenerated
     */
    public String getIcon() {
        return icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_navs.icon
     *
     * @param icon the value for tb_navs.icon
     *
     * @mbggenerated
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_navs.herf
     *
     * @return the value of tb_navs.herf
     *
     * @mbggenerated
     */
    public String getHerf() {
        return herf;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_navs.herf
     *
     * @param herf the value for tb_navs.herf
     *
     * @mbggenerated
     */
    public void setHerf(String herf) {
        this.herf = herf;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_navs.spread
     *
     * @return the value of tb_navs.spread
     *
     * @mbggenerated
     */
    public Integer getSpread() {
        return spread;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_navs.spread
     *
     * @param spread the value for tb_navs.spread
     *
     * @mbggenerated
     */
    public void setSpread(Integer spread) {
        this.spread = spread;
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}