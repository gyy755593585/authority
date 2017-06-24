 <%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div region="west" split="true" title="导航菜单" style="width: 200px;">
	<div id="aa" class="easyui-accordion"
		style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;">
		<div title="报文管理" data-options= "iconcls:'icon-add'" style="overflow: auto; padding: 10px;">
			<ul >
			
			<li>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						plain="true" href="javascript:void(0);"
						onclick="addTab('交易基本信息','/pages/trade/index.jsp','icon-add')">交易基本信息</a>
				</li>
			    <li>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						plain="true" href="javascript:void(0);"
						onclick="addTab('报文基本信息','/pages/message/index.jsp','icon-add')">报文基本信息</a>
				</li>
				<li>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						plain="true" href="javascript:void(0);"
						onclick="addTab('报文组','/pages/messageGroup/index.jsp','icon-add')">报文组</a>
				</li>
				<li>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						plain="true" href="javascript:void(0);"
						onclick="addTab('报文单元','/pages/messageElement/index.jsp','icon-add')">报文单元</a>
				</li>
				
			</ul>
		</div>
		<div title="交易流管理" 
			style="overflow: auto; padding: 10px;">
			<ul>
				
				<li>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						plain="true" href="javascript:void(0);"
						onclick="addTab('交易流','/pages/tradeFlow/index.jsp','icon-add')">交易流</a>
				</li>
				<li>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						plain="true" href="javascript:void(0);"
						onclick="addTab('交易关联性','/pages/messageElementLink/index.jsp','icon-add')">交易关联性</a>
				</li>

				

			</ul>
		</div>
		
		<div title="扩展工具" iconcls="icon-search"
			style="overflow: auto; padding: 10px;">
			<ul >
			<li>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						plain="true" href="javascript:void(0);"
						onclick="addTab('日志分析（停用）','/logAnalysis!FindALlTradeFlow','icon-add')">日志分析（停用）</a>
				</li>
				
				<li>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						plain="true" href="javascript:void(0);"
						onclick="addTab('SOP报文工具','/pages/SOP_tools/index.jsp','icon-add')">SOP报文工具</a>
				</li>
				<li>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						plain="true" href="javascript:void(0);"
						onclick="addTab('AE导入','/pages/AE_import/index.jsp','icon-add')">AE导入</a>
				</li>
				<li>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						plain="true" href="javascript:void(0);"
						onclick="addTab('密钥下载','/pages/download/index.jsp','icon-add')">密钥下载</a>
				</li>
				<li>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						plain="true" href="javascript:void(0);"
						onclick="addTab('测试统计','/pages/statistics/index.jsp','icon-add')">测试统计</a>
				</li>
				<li>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						plain="true" href="javascript:void(0);"
						onclick="addTab('测试记录','/pages/testInfo/index.jsp','icon-add')">测试记录</a>
				</li>
				<li>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						plain="true" href="javascript:void(0);"
						onclick="addTab('测试卡管理','/pages/account/index.jsp','icon-add')">测试卡管理</a>
				</li>
				
<!-- 				<li> -->
<!-- 					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" -->
<!-- 						plain="true" href="javascript:void(0);" -->
<!-- 						onclick="addTab('加密配置','/pages/config/encCfg.jsp','icon-add')">加密配置</a> -->
<!-- 				</li> -->
			</ul>
		</div>
		<div title="用户管理" iconcls="icon-search"
			style="overflow: auto; padding: 10px;">
			<ul >
			<li>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						plain="true" href="javascript:void(0);"
						onclick="addTab('用户管理','/pages/user/index.jsp','icon-add')">用户管理</a>
				</li>
			<li>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						plain="true" href="javascript:void(0);"
						onclick="addTab('角色管理','/pages/role/index.jsp','icon-add')">角色管理</a>
				</li>
			<li>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						plain="true" href="javascript:void(0);"
						onclick="addTab('权限管理','/pages/permit/index.jsp','icon-add')">权限管理</a>
				</li>
			</ul>
		</div>
	</div>
</div>