package com.makerpol.entity;

public class User {
	
    private Integer id;			//Ψһ��ʶ  
    
    private String image;		//ͷ��
    
    private Integer grade;		//0 ������Ա  1����������  2���༭����  3����ͨ��Ա

    private String name;		//��¼��

    private String password;	//��¼����

    private String realname;	//��ʵ����

    private Integer sex;		//�Ա�

    private Long phone;			//�绰

    private String email;		//����

    private String birthday;	//����

    private Integer status;		//0������ʹ��  1����ֹʹ��  2��ɾ��״̬
    
    private Integer IDCard;		//���֤
    
    public Integer getIDCard() {
		return IDCard;
	}

	public void setIDCard(Integer iDCard) {
		IDCard = iDCard;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
}