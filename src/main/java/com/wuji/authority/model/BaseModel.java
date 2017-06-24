package com.wuji.authority.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.GenericGenerator;

import com.wuji.authority.util.DateUtil;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseModel {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	@Column(name = "create_time")
	private Timestamp createTime;

	@Column(name = "edit_time")
	private Timestamp editTime;

	@Column(name = "create_user")
	private String createUser;

	@Column(name = "edit_user")
	private String editUser;

	public BaseModel() {
		this.createTime = this.editTime = DateUtil.getNowFull();
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getEditTime() {
		return this.editTime;
	}

	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getEditUser() {
		return this.editUser;
	}

	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
