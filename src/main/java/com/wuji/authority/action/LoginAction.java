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

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wuji.authority.model.Permit;
import com.wuji.authority.model.Role;
import com.wuji.authority.model.User;
import com.wuji.authority.service.PermitService;
import com.wuji.authority.service.RoleService;
import com.wuji.authority.service.UserService;
import com.wuji.authority.util.SecurityUtil;
import com.wuji.authority.vo.ActivityUser;

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
	private RoleService roleService;

	@Autowired
	private PermitService permitService;

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
		User loginUser = this.userService.findByUserName(this.user.getUserName());
		if (loginUser == null) {
			this.writeJson(this.renderError("账号不存在！"));
			throw new RuntimeException("账号不存在！");
		} else {
			try {
				if (!SecurityUtil.md5(loginUser.getSalt(), this.user.getPassword()).equals(loginUser.getPassword())) {
					this.writeJson(this.renderError("账号或密码错误！"));
					throw new RuntimeException("账号或密码错误！");
				} else if (loginUser.getStatus() == 1) {
					this.writeJson(this.renderError("账号未启用！"));
					throw new RuntimeException("账号未启用！");
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		// 放入Session中用户对象
		ActivityUser activityUser = new ActivityUser(this.user.getUserName());
		activityUser.setName(loginUser.getNickName());
		List<Role> roleList = this.roleService.findRoleByUserName(this.user.getUserName());
		// 用户所有角色名
		Set<String> roles = new HashSet<>();
		// 用户允许被访问的URL
		Set<String> permits = new HashSet<>();
		for (Role role : roleList) {
			// 判断角色是否为admin
			if (role.getType() == 1) {
				activityUser.setAdmin(true);
				// break;
			}
			// 根据角色获取用户系统和对应的权限
			List<Permit> permitList = this.permitService.findPermitByRoleId(role.getId());
			for (Permit systemPermit2 : permitList) {
				String permitCode = systemPermit2.getPermitCode();
				this.logger.info(permitCode);
				permits.add(permitCode);

			}
			roles.add(role.getRoleName());
		}
		if (activityUser.isAdmin()) {

		}
		activityUser.setPermitCodes(permits);
		activityUser.setRoles(roles);
		// activityUser.setRolePermit(systemPermit);
		this.session.put("activityUser", activityUser);
		this.writeJson(this.renderSuccess());
		return "index";
	}

	public void logout() {
		this.httpSession.invalidate();
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
			if (StringUtils.isNotBlank(this.user.getPassword())) {
				String salt = UUID.randomUUID().toString().replaceAll("-", "");
				curUser.setSalt(salt);
				curUser.setPassword(SecurityUtil.md5(salt, this.user.getPassword()));
			}
			this.userService.update(curUser);
			this.writeJson(this.renderSuccess("用户修改成功"));
		} catch (Exception e) {
			this.logger.error("修改用户失败", e);
			this.writeJson(this.renderError("用户修改失败!"));
		}
	}
}
