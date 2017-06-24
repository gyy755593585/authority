/**
 *
 */
package com.wuji.authority.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuji.authority.dao.RoleDao;
import com.wuji.authority.dao.RolePermitDao;
import com.wuji.authority.dao.UserRoleDao;
import com.wuji.authority.model.Role;
import com.wuji.authority.model.User;
import com.wuji.authority.model.UserRole;
import com.wuji.authority.service.RoleService;

/**
 * @author Yayun
 *
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private RolePermitDao rolePermitDao;

	/* (non-Javadoc)
	 * @see com.wuji.basic.service.BaseService#add(java.lang.Object)
	 */
	@Override
	public Role add(Role t) {
		return this.roleDao.add(t);
	}

	/* (non-Javadoc)
	 * @see com.wuji.basic.service.BaseService#update(java.lang.Object)
	 */
	@Override
	public void update(Role t) {
		this.roleDao.update(t);
	}

	/*
	 *  删除角色 同时删除角色和权限关联表,角色和用户关联表中的数据
	 */
	@Override
	public void delete(Long id) {
		this.rolePermitDao.deletByRoleId(id);
		this.userRoleDao.deleteByRoleId(id);
		this.roleDao.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.wuji.basic.service.BaseService#load(java.lang.Long)
	 */
	@Override
	public Role load(Long id) {
		return this.roleDao.load(id);
	}

	/* (non-Javadoc)
	 * @see com.wuji.basic.service.BaseService#findAll()
	 */
	@Override
	public List<Role> findAll() {
		return this.roleDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.wuji.authority.service.RoleService#findRoleByUserName(java.lang.String)
	 */
	@Override
	public List<Role> findRoleByUserName(String userName) {
		return this.userRoleDao.findRoleByUserName(userName);
	}

	/* (non-Javadoc)
	 * @see com.wuji.authority.service.RoleService#addRoleForUser(com.wuji.authority.model.User, com.wuji.authority.model.Role)
	 */
	@Override
	public UserRole addRoleForUser(User user, Role role) {
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		return this.userRoleDao.add(userRole);
	}

}
