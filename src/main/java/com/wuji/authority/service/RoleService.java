/**
 *
 */
package com.wuji.authority.service;

import java.util.List;

import com.wuji.authority.model.Role;
import com.wuji.authority.model.User;
import com.wuji.authority.model.UserRole;
import com.wuji.basic.service.BaseService;

/**
 * @author Yayun
 *
 */
public interface RoleService extends BaseService<Role> {

	List<Role> findRoleByUserName(String userName);

	UserRole addRoleForUser(User user, Role role);
}
