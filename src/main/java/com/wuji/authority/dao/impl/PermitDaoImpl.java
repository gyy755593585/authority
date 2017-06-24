/**
 *
 */
package com.wuji.authority.dao.impl;

import org.springframework.stereotype.Repository;

import com.wuji.authority.dao.PermitDao;
import com.wuji.authority.model.Permit;
import com.wuji.basic.dao.BaseDao;

/**
 * @author Yayun
 *
 */
@Repository("permitDao")
public class PermitDaoImpl extends BaseDao<Permit> implements PermitDao {

	@Override
	public Permit add(Permit t) {
		super.setCreateInfo(t);
		return super.add(t);
	}

	@Override
	public void update(Permit t) {
		super.setEditInfo(t);
		super.update(t);
	}
}
