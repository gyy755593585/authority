/**
 *
 */
package com.wuji.authority.dao.impl;

import org.springframework.stereotype.Repository;

import com.wuji.authority.dao.RoleDao;
import com.wuji.authority.model.Role;
import com.wuji.basic.dao.BaseDao;

/**
 * @author Yayun
 *
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDao<Role> implements RoleDao {

	@Override
	public Role add(Role t) {
		super.setCreateInfo(t);
		return super.add(t);
	}

	@Override
	public void update(Role t) {
		super.setEditInfo(t);
		super.update(t);
	}
}
