
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

package com.wuji.authority.dao;

import java.util.List;

import com.wuji.authority.model.Permit;
import com.wuji.basic.dao.IBaseDao;
import com.wuji.basic.model.Pager;

/**
 * @author Yayun
 *
 */
public interface PermitDao extends IBaseDao<Permit> {
	/**
	 *
	 * @return 分页permit对象
	 */
	Pager<Permit> findByPage();

	/**
	 * 判断是否有子节点
	 *
	 * @param id
	 * @return
	 */
	boolean hasChild(Long id);

	/**
	 * 通过pid
	 * 
	 * @param id
	 * @return
	 */
	List<Permit> findByPid(Long id);
}
