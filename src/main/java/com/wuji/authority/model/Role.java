/**
 *
 */
package com.wuji.authority.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Yayun 用户角色
 */
@Entity
@Table(name = "a_role")
public class Role extends BaseModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8110983716043633722L;

	@Column(name = "role_name")
	private String roleName;

	/**
	 * 0为普通用户 1为管理员
	 */
	private int type;

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
