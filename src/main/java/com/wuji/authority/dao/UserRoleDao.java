/**
 *
 */
package com.wuji.authority.dao;

import java.util.List;

import com.wuji.authority.model.Role;
import com.wuji.authority.model.UserRole;
import com.wuji.basic.dao.IBaseDao;

/**
 * @author Yayun
 *
 */
public interface UserRoleDao extends IBaseDao<UserRole> {

	List<Role> findRoleByUserName(String userName);

	void deleteByUserId(Long userId);

	void deleteByRoleId(Long roleId);

}
