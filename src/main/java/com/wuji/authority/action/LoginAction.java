/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.wuji.authority.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wuji.authority.model.User;
import com.wuji.authority.service.UserService;
import com.wuji.authority.shiro.UserRealm;

/**
 * @author Yayun
 *
 */
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginAction extends BaseAction implements ModelDriven<User> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1201335667207850091L;

	/**
	 *
	 */

	@Autowired
	private UserService userService;
	@Autowired
	private UserRealm userRealm;

	private User user = new User();

	private Long id;

	private List<Integer> roleIds;

	public List<Integer> getRoleIds() {
		return this.roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public User getModel() {
		return this.user;
	}

	public String login() {
		return "login";
	}

	public String index() {
		return "index";
	}

	/**
	 * 用户登录
	 *
	 * @return
	 */
	public String register() {
		this.logger.info("POST请求登录");
		this.logger.info(this.user.getUserName());
		if (StringUtils.isBlank(this.user.getUserName())) {
			throw new RuntimeException("用户名不能为空");
		}
		if (StringUtils.isBlank(this.user.getPassword())) {
			throw new RuntimeException("密码不能为空");
		}
		Subject user = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(this.user.getUserName(), this.user.getPassword());
		try {
			user.login(token);
			this.writeJson(this.renderSuccess());
		} catch (UnknownAccountException e) {
			this.writeJson(this.renderError("账号不存在！"));
			throw new RuntimeException("账号不存在！", e);
		} catch (DisabledAccountException e) {
			this.writeJson(this.renderError("账号未启用！"));
			throw new RuntimeException("账号未启用！", e);
		} catch (IncorrectCredentialsException e) {
			this.writeJson(this.renderError("账号或密码错误！"));
			throw new RuntimeException("密码错误！", e);
		} catch (Throwable e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		return "index";
	}

	public void logout() {
		this.logger.info("登出");
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		// this.httpSession.invalidate();
		this.userRealm.clearAllCache();
		this.login();
	}

	/**
	 * 页面跳转
	 *
	 * @return
	 */
	public String userInfo() {
		this.id = this.user.getId();
		this.logger.info(this.id.toString());
		try {
			User curUser = this.userService.load(this.id);
			this.user = curUser;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userInfo";
	}

	/**
	 * 用户更新
	 */
	public void editUserInfo() {
		try {
			User curUser = this.userService.load(this.user.getId());
			curUser.setNickName(this.user.getNickName());
			curUser.setPassword(null);
			if (StringUtils.isNotBlank(this.user.getPassword())) {
				curUser.setPassword(this.user.getPassword());
			}
			this.userService.update(curUser);
			this.writeJson(this.renderSuccess("用户修改成功"));
		} catch (Exception e) {
			this.logger.error("修改用户失败", e);
			this.writeJson(this.renderError("用户修改失败!"));
		}
	}
}
