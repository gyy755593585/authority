/**
 *
 */
package com.wuji.authority.service;

import com.wuji.authority.model.User;
import com.wuji.basic.service.BaseService;

/**
 * @author Yayun
 *
 */
public interface UserService extends BaseService<User> {

	/**
	 * 通过用户名查User对象
	 *
	 * @param userName
	 * @return
	 */
	User findByUserName(String userName);

}
