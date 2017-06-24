<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/commons/basejs.jsp" %>
		<script type="text/javascript" charset="utf-8">
var datagrid;
$(function() {
	datagrid = $('#datagrid')
			.datagrid(
					{
						url : 'sysPermitAction!getSysPermitList',
						iconCls : 'icon-save',
                        pagination : true,
			            pagePosition : 'bottom',
			            pageSize : 10,
			            pageList : [ 10, 20, 30, 40 ],
						fit : true,
						fitColumns : false,
						rownumbers : true,
						singleSelect : false,
						border : false,
						idField : 'id',
						frozenColumns : [ [ {
							title : '编号',
							field : 'id',
							width : 50,
							sortable : true,
							checkbox : true
						},{
							title : '名称',
							field : 'name',
							width : 100,
							sortable : true
						}, {
							title : '权限编号',
							field : 'permitCode',
							width : 250,
							sortable : true
						},{
							title : '角色类型',
							field : 'sysPermitType',
							width : 150,
							sortable : true,
							 formatter : function(value, row, index) {
				                    if(value == 'READ') {
				                        return "只读";
				                    }else if(value == 'WRITE') {
				                        return "可写";
				                    }
				                    return "未知类型";
				                }
						}] ],
						toolbar : [ {
							text : '增加',
							iconCls : 'icon-add',
							handler : function() {
								append();
							}
						}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								remove();
							}
						}, '-', {
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								edit();
							}
						}, '-' ],
						columns : [ [ {
							title : '操作',
							field : 'll',
							width : 100,
							formatter : function(value, rowData, rowIndex) {
								return '<span class="icon-search" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span><a href="javascript:void(0);" onclick="changeStatus(' + rowIndex + ');">改变状态</a>';
							}
						} ] ],
						onRowContextMenu : function(e, rowIndex, rowData) {
							e.preventDefault();
							$(this).datagrid('unselectAll');
							$(this).datagrid('selectRow', rowIndex);
							$('#menu').menu('show', {
								left : e.pageX,
								top : e.pageY
							});
						} 
					});
});
function edit() {
	var rows = datagrid.datagrid('getSelections');
	if (rows.length == 1) {
		var p = parent.dj
				.dialog( {
					title : '修改角色',
					href : '${pageContext.request.contextPath}/sysPermitAction!sysPermitEdit?id=' + rows[0].id,
					width : 600,
					height : 450,
					buttons : [ {
						text : '修改',
						handler : function() {
							var f = p.find('form');
							f
									.form(
											'submit',
											{
												url : '${pageContext.request.contextPath}/sysPermitAction!edit',
												success : function(d) {
													var json = $.parseJSON(d);
													if (json.success) {
														datagrid
																.datagrid('reload');
														p.dialog('close');
													}
													parent.dj.messagerShow( {
														msg : json.msg,
														title : '提示'
													});
												}
											});
						}
					} ],
					onLoad : function() {
						var f = p.find('form');
						f.form('load', {
							id : rows[0].id,
							name : rows[0].name,
							permitCode : rows[0].permitCode
							
						});

					}
				});
	} else if (rows.length > 1) {
		parent.dj.messagerAlert('提示', '同一时间只能编辑一条记录！', 'error');
	} else {
		parent.dj.messagerAlert('提示', '请选择要编辑的记录！', 'error');
	}
}
function append() {
	var p = parent.dj
			.dialog( {
				title : '新增角色',
				href : '${pageContext.request.contextPath}/sysPermitAction!sysPermitAdd',
				width : 600,
				height : 450,
				buttons : [ {
					text : '新增',
					handler : function() {
						var f = p.find('form');
						f.form( {
									url : '${pageContext.request.contextPath}/sysPermitAction!add',
									success : function(d) {
										var json = $.parseJSON(d);
										if (json.success) {
											datagrid.datagrid('reload');
											p.dialog('close');
										}
										parent.dj.messagerShow( {
											msg : json.msg,
											title : '提示'
										});
									}
								});
						f.submit();
					}
				} ]
			});
}
function remove() {
	var rows = datagrid.datagrid('getChecked');
	var ids = [];
	if (rows.length > 0) {
		parent.dj
				.messagerConfirm(
						'请确认',
						'您要删除当前所选项目？',
						function(r) {
							if (r) {
								for ( var i = 0; i < rows.length; i++) {
									ids.push(rows[i].id);
								}
								$
										.ajax( {
											url : '${pageContext.request.contextPath}/sysPermitAction!delete',
											data : {
												ids : ids.join(',')
											},
											dataType : 'json',
											success : function(d) {
												datagrid.datagrid('load');
												datagrid
														.datagrid('unselectAll');
												parent.dj.messagerShow( {
													title : '提示',
													msg : d.msg
												});
											}
										});
							}
						});
	} else {
		parent.dj.messagerAlert('提示', '请勾选要删除的记录！', 'error');
	}
}

function changeStatus(rowIndex) {
	var rows = datagrid.datagrid('getRows');
	var row = rows[rowIndex];
	var isAdmin =row.isAdmin;
	parent.dj
	.messagerConfirm(
			'请确认',
			isAdmin==0?'您要更改角色为管理员？':'您要更改角色为系统用户？',
			function(r) {
				if (r) {
					id=row.id;
					$.ajax( {
								url : '${pageContext.request.contextPath}/sysPermitAction!changeStatus',
								data : {
									id : id,
									isAdmin:isAdmin
								},
								dataType : 'json',
								success : function(d) {
									datagrid.datagrid('load');
									datagrid
											.datagrid('unselectAll');
									parent.dj.messagerShow( {
										title : '提示',
										msg : d.msg
									});
								}
							});
				}
			});
}


</script>
	</head>
	<body class="easyui-layout">

		<div data-options="region:'center',border:false"
			style="overflow: hidden;">
			<table id="datagrid"></table>
		</div>

		<div id="menu" class="easyui-menu"
			style="width: 120px; display: none;">
			<div onclick="append();" data-options="iconCls:'icon-add'">
				增加
			</div>
			<div onclick="remove();" data-options="iconCls:'icon-remove'">
				删除
			</div>
			<div onclick="edit();" data-options="iconCls:'icon-edit'">
				编辑
			</div>
		</div>
	</body>
</html>