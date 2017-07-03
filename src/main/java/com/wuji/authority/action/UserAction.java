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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wuji.authority.model.Role;
import com.wuji.authority.model.User;
import com.wuji.authority.service.PermitService;
import com.wuji.authority.service.RoleService;
import com.wuji.authority.service.UserService;
import com.wuji.authority.util.SecurityUtil;
import com.wuji.authority.vo.Tree;
import com.wuji.basic.model.Pager;

/**
 * 用户管理Action
 *
 * @author Yayun
 *
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction implements ModelDriven<User> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermitService permitService;

	private User user = new User();

	private Long id;

	private List<Long> roleIds;

	public List<Long> getRoleIds() {
		return this.roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
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

	/**
	 * 页面跳转
	 *
	 * @return
	 */
	public String index() {
		return "index";
	}

	/**
	 * 页面跳转
	 *
	 * @return
	 */
	public String userAdd() {
		return "userAdd";
	}

	/**
	 * 页面跳转
	 *
	 * @return
	 */
	public String userEdit() {
		this.id = this.user.getId();
		try {
			this.user = this.userService.load(this.id);
			List<Role> lists = this.roleService.findRoleByUserName(this.user.getUserName());
			this.roleIds = new ArrayList<>();
			for (Role sysRole : lists) {
				this.logger.info(sysRole.getId().toString());
				this.roleIds.add(sysRole.getId());
			}
			this.request.setAttribute("roleIds", this.roleIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userEdit";
	}

	/**
	 * 用户添加
	 */
	public void add() {
		try {
			String salt = UUID.randomUUID().toString().replaceAll("-", "");
			this.user.setSalt(salt);
			this.user.setPassword(SecurityUtil.md5(salt, this.user.getPassword()));
			this.userService.add(this.user);
			for (Long roleId : this.roleIds) {
				Role sysRole = this.roleService.load(roleId);
				this.roleService.addRoleForUser(this.user, sysRole);
			}
		} catch (Exception e) {
			this.writeJson(this.renderError(e.getMessage()));
		}
		this.writeJson(this.renderSuccess("用户添加成功"));
	}

	/**
	 * 用户更新
	 */
	public void edit() {
		try {
			User sysUser = this.userService.load(this.user.getId());
			sysUser.setNickName(this.user.getNickName());
			sysUser.setStatus(this.user.getStatus());
			sysUser.setType(this.user.getType());
			if (StringUtils.isNotBlank(this.user.getPassword())) {
				String salt = UUID.randomUUID().toString().replaceAll("-", "");
				sysUser.setSalt(salt);
				sysUser.setPassword(SecurityUtil.md5(salt, this.user.getPassword()));
			}
			this.logger.info(this.roleIds.get(0).toString());
			this.userService.update(sysUser, this.roleIds);
			this.writeJson(this.renderSuccess("用户修改成功"));
		} catch (Exception e) {
			this.logger.error("修改用户失败", e);
			this.writeJson(this.renderError("用户修改失败!"));
		}
	}

	/**
	 * 用户删除
	 */
	public void delete() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		if (ids != null) {
			try {
				for (String id : ids.split(",")) {
					this.userService.delete(Long.parseLong(id));

				}
				this.writeJson(this.renderSuccess("用户删除成功"));
			} catch (Exception e) {
				this.writeJson(this.renderError("用户删除失败"));
				e.printStackTrace();
			}
		}
	}

	/**
	 * 改变用户状态 0为启用 1为停用
	 */
	public void changeStatus() {
		try {
			User sysUser = this.userService.load(this.user.getId());
			if (this.user.getStatus() == 0) {
				sysUser.setStatus(1);
			} else {
				sysUser.setStatus(0);
			}
			this.userService.update(sysUser);
			this.writeJson(this.renderSuccess("更改状态成功"));
		} catch (Exception e) {
			this.writeJson(this.renderError("更改状态失败"));
			e.printStackTrace();
		}

	}

	/**
	 * 用户分页列表
	 */
	public void getUserList() {
		Pager<User> pager = this.userService.findByPage();
		super.writeJson(pager);
	}

	/**
	 * 获取角色树 可多选
	 */
	public void getRoleTree() {
		try {
			List<Tree> tree = this.roleService.findAllTree();
			super.writeJson(tree);
		} catch (Exception e) {
			this.writeJson(this.renderError("获取角色失败"));
			e.printStackTrace();
		}
	}

}