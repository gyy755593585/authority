/**
 *
 */
package com.wuji.authority.dao;

import java.util.List;

import com.wuji.authority.model.Permit;
import com.wuji.authority.model.RolePermit;
import com.wuji.basic.dao.IBaseDao;

/**
 * @author Yayun
 *
 */
public interface RolePermitDao extends IBaseDao<RolePermit> {

	/**
	 * 通过角色id查询权限
	 *
	 * @param roleId
	 * @return
	 */
	List<Permit> findPermitByRoleId(Long roleId);

	void deletByRoleId(Long roleId);

	void deletByPermitId(Long permitId);
}
