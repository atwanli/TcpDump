package com.baomidou.springmvc.model;
/**  
* @Title: User.java
* @Package com.baomidou.springmvc.mapper.system
* @Description: TODO()
* @author wwl 
* @date 2016年11月22日 上午9:24:14
* @version V1.0  
*/

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 系统用户表
 *
 */
@TableName("user")
public class User implements Serializable {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 用户ID */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 用户名 */
	private String name;

	/** 用户年龄 */
	@TableField(exist = false) 
	private Integer age;
	@TableField("age")
	private String sex;
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}


