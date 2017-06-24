<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	
</script>
<div style="padding: 5px;">
	<form method="post">
		<input name="id" type="hidden" />
		<table class="tableForm">
		<tr>
				<td>角色名称</td>
				<td><input id="roleName"  type ="text"  name="roleName" value="roleName"  class="easyui-validatebox" placeholder="请输入登录名称" validType="length[0,19]" invalidMessage="超过了19个字符" data-options="required:true,missingMessage:'请填写登录名称'" style="width:98%;" /></td>
				<td><input type="hidden" name="Id" value=${id} /></td>
			</tr>
			
			<tr>
				<td>角色类型</td>
				<td>
                        <select name="isAdmin" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="1">系统管理员</option>
                            <option value="0" selected="selected">用户</option>
                        </select>
                    </td>
			</tr>
			<tr>
				<td>所属系统</td>
                 <td><select id="systemCode" name="systemCode" style="width: 140px; height: 29px;">
                 <c:forEach items="${userSystems}" var="system">
								<c:choose >
								<c:when test="${ system.systemCode eq systemCode}">
								<option value="${ system.systemCode}" selected="selected" >${ system.systemName}</option>
								</c:when>
								<c:otherwise>
								<option value="${ system.systemCode}" >${ system.systemName}</option>
								</c:otherwise>
								</c:choose>
							</c:forEach>
                 </select></td>
			</tr>
		</table>
	</form>
</div>