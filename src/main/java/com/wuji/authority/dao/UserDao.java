/**
 *
 */
package com.wuji.authority.dao;

import com.wuji.authority.model.User;
import com.wuji.basic.dao.IBaseDao;

/**
 * @author Yayun
 *
 */

public interface UserDao extends IBaseDao<User> {

	/**
	 * 通过用户名查User对象
	 *
	 * @param userName
	 * @return
	 */
	User findByUserName(String userName);

}
