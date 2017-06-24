/**
 *
 */
package com.wuji.authority.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuji.authority.dao.UserDao;
import com.wuji.authority.dao.UserRoleDao;
import com.wuji.authority.model.User;
import com.wuji.authority.service.UserService;
import com.wuji.basic.model.SystemException;

/**
 * @author Yayun
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	/* (non-Javadoc)
	 * @see com.wuji.basic.service.BaseService#add(java.lang.Object)
	 */
	@Override
	public User add(User user) {
		User existUser = this.userDao.findByUserName(user.getUserName());
		if (existUser != null) {
			throw new SystemException("用户名称已存在");
		}
		return this.userDao.add(user);
	}

	/* (non-Javadoc)
	 * @see com.wuji.basic.service.BaseService#update(java.lang.Object)
	 */
	@Override
	public void update(User user) {
		this.userDao.update(user);
	}

	/* (non-Javadoc)
	 * @see com.wuji.basic.service.BaseService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		this.userRoleDao.deleteByUserId(id);
		this.userDao.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.wuji.basic.service.BaseService#load(java.lang.Long)
	 */
	@Override
	public User load(Long id) {
		return this.userDao.load(id);
	}

	/* (non-Javadoc)
	 * @see com.wuji.basic.service.BaseService#findAll()
	 */
	@Override
	public List<User> findAll() {
		return this.userDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.wuji.authority.service.UserService#findByUserName(java.lang.String)
	 */
	@Override
	public User findByUserName(String userName) {
		return this.userDao.findByUserName(userName);
	}

}
