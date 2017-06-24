/**
 *
 */
package com.wuji.authority.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Yayun 权限
 *
 */
@Entity
@Table(name = "a_permit")
public class Permit extends BaseModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5275465678502631871L;

	/**
	 *
	 */

	@Column(name = "permit_name")
	private String permitName;

	@Column(name = "permit_code")
	private String permitCode;

	@OneToOne
	@JoinColumn(name = "pid")
	private Permit parentPermit;

	public String getPermitName() {
		return this.permitName;
	}

	public void setPermitName(String permitName) {
		this.permitName = permitName;
	}

	public String getPermitCode() {
		return this.permitCode;
	}

	public void setPermitCode(String permitCode) {
		this.permitCode = permitCode;
	}

}
