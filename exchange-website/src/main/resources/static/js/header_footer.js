 
var headerBox = '<div class="header" id="headerBox">' +
	'<div class="headerContent">' +
	'<div class="logo">' +
	'<img src="../img/logo.png" />' +
	'</div>' +
	'<div class="customViw">' +
	'<ul class="webMarkList" id="webMarkList" >' +
	'<li><span>信息披露</span>' +
	'<span class="icon-jiantouxaingxia"></span></li>' +
	'<li><span>会员服务</span>' +
	'<span class="icon-jiantouxaingxia "></span></li>' +
	'<li><span>监管规则</span>' +
	'<span class="icon-jiantouxaingxia "></span></li>' +
	'<li><span>平台数据</span>' +
	'<span class="icon-jiantouxaingxia "></span></li>' +
	'<li><span>关于我们</span>' +
	'<span class="icon-jiantouxaingxia "></span></li>' +
	'</ul>' +
	'</div>' +
	'<div class="loginSearch">' +
	'<a href="http://127.0.0.1:8020/exchange/exchange-website-admin/src/main/resources/static/html/login.html?__hbt=1536044880828"><span class="loginButton" id="loginBtn">登录</span></a>' +
	'<span class="header-search icon-Oval" id="searchBtn"></span>' +
	'</div>' +
	'</div>' +
	'</div>'
	
var navComponent='<div class="selectContent" id="selectBox">'+
		'<div class=" sidebar-collapse">'+
			'<ul class="nav" id="side-menu" style="position: relative;margin-left: 9.5%;">'+
				'<div class="infoList infoListBox">'+
					'<ul class="nav nav-second-level">'+
						'<li>'+
							'<a href="">'+
								'<span class="nav-label">ACAE公告</span>'+
								'<span class="icon-jiantouyou"></span>'+
								'<span class="icon-jiantouxaingxia hidden"></span>'+
							'</a>'+
							'<ul class="nav nav-second-level">'+
								'<li><a class="J_menuItem" href="noticeBulletin.html">通知公告</a></li>'+
								'<li><a class="J_menuItem" href="supervisionBulletin.html">监管公告</a></li>'+
								'<li><a class="J_menuItem" href="stopSartBulletin.html">停复牌公告</a></li>'+
							'</ul>'+
						'</li>'+
						'<li>'+
							'<a href="">'+
								'<span class="nav-label">会员信息</span>'+
								'<span class="icon-jiantouyou"></span>'+
								'<span class="icon-jiantouxaingxia hidden"></span>'+
							'</a>'+
							'<ul class="nav nav-second-level">'+
								'<li><a class="J_menuItem" href="companyNews.html">公司新闻</a></li>'+
								'<li><a class="J_menuItem" href="companyNotice.html">公司公告</a></li>'+
								'<li><a class="J_menuItem" href="timeNotice.html">定期报告</a></li>'+
							'</ul>'+
						'</li>'+
						'<li>'+
							'<a href="">'+
								'<span class="nav-label">交易信息</span>'+
								'<span class="icon-jiantouyou"></span>'+
								'<span class="icon-jiantouxaingxia hidden"></span>'+
							'</a>'+
							'<ul class="nav nav-second-level">'+
								'<li><a class="J_menuItem" data-href="orderFaultReporting.html">股票交易信息</a></li>'+
								'<li><a class="J_menuItem" data-href="orderFaultReporting.html">债券交易信息</a></li>'+
							'</ul>'+
						'</li>'+
					'</ul>'+
				'</div>'+
				'<div class="infoList infoListBox">'+
					'<ul class="nav nav-second-level">'+
						'<li><a href="vipServiceWhyChoose.html"><span class="nav-label">为什么选择ACAE</span></a></li>'+
						'<li>'+
							'<a href="">'+
								'<span class="nav-label">规则指南</span>'+
								'<span class="icon-jiantouyou"></span>'+
								'<span class="icon-jiantouxaingxia hidden"></span>'+
							'</a>'+
							'<ul class="nav nav-second-level">'+
								'<li><a class="J_menuItem" href="vipRule.html">会员申请规则</a></li>'+
								'<li><a class="J_menuItem" data-href="orderFaultReporting.html">会员申请流程</a></li>'+
							'</ul>'+
						'</li>'+
						'<li>'+
							'<a href="">'+
								'<span class="nav-label">会员服务</span>'+
								'<span class="icon-jiantouyou"></span>'+
								'<span class="icon-jiantouxaingxia hidden"></span>'+
							'</a>'+
							'<ul class="nav nav-second-level">'+
								'<li><a class="J_menuItem" data-href="orderFaultReporting.html">披露和新闻服务</a></li>'+
								'<li><a class="J_menuItem" data-href="orderFaultReporting.html">实时交易数据</a></li>'+
								'<li><a class="J_menuItem" href="acai.html">ACAI</a></li>'+
							'</ul>'+
						'</li>'+
						'<li><a href="vipDir.html"><span class="nav-label">会员目录</span></a></li>'+
						'<li>'+
							'<a href="">'+
								'<span class="nav-label">ACAE保荐商</span>'+
								'<span class="icon-jiantouyou"></span>'+
								'<span class="icon-jiantouxaingxia hidden"></span>'+
							'</a>'+
							'<ul class="nav nav-second-level">'+
								'<li><a class="J_menuItem" href="existingSponsor.html">现有保荐商展示</a></li>'+
								'<li><a class="J_menuItem" href="applicationJoin.html">申请成为保荐商</a></li>'+
							'</ul>'+
						'</li>'+
						'<li>'+
							'<a href="">'+
								'<span class="nav-label">服务机构</span>'+
								'<span class="icon-jiantouyou"></span>'+
								'<span class="icon-jiantouxaingxia hidden"></span>'+
							'</a>'+
							'<ul class="nav nav-second-level">'+
								'<li><a class="J_menuItem" href="secretarialCompany.html">秘书公司</a></li>'+
								'<li><a class="J_menuItem" href="serviceAgencyAccreditation.html">国际评估机构</a></li>'+
								'<li><a class="J_menuItem" href="auditInstitutions.html">国际审计机构</a></li>'+
								'<li><a class="J_menuItem" href="serviceAgencyLawFirm.html">国际律师事务所</a></li>'+
								'<li><a class="J_menuItem" href="serviceAgencyFinancialinstitutions.html">国际金融机构</a></li>'+
								'<li><a class="J_menuItem" href="cooperation.html">申请合作</a></li>'+
							'</ul>'+
						'</li>'+
					'</ul>'+
				'</div>'+
				'<div class="infoList infoListBox">'+
					'<ul class="nav nav-second-level">'+
						'<li>'+
							'<a href="">'+
								'<span class="nav-label">法律</span>'+
								'<span class="icon-jiantouyou"></span>'+
								'<span class="icon-jiantouxaingxia hidden"></span>'+
							'</a>'+
							'<ul class="nav nav-second-level">'+
							'<li><a class="J_menuItem" data-href="orderFaultReporting.html">澳门地区金融体系法律制度</a></li>'+
							'</ul>'+
						'</li>'+
						'<li>'+
							'<a href="">'+
								'<span class="nav-label">规范性文件</span>'+
								'<span class="icon-jiantouyou"></span>'+
								'<span class="icon-jiantouxaingxia hidden"></span>'+
							'</a>'+
							'<ul class="nav nav-second-level">'+
								'<li><a class="J_menuItem" data-href="orderRefundManagement.html">ACAE范本文件清单</a></li>'+
							'</ul>'+
						'</li>'+
						'<li style="margin: 15px 5px;">'+
							'<a href="" style="padding: 0;padding-right: 15px;">'+
								'<span class="nav-label">ACAE平台规则</span>'+
				                '<span class="icon-jiantouyou" style="position: relative;left: 5px;"></span>'+
								'<span class="icon-jiantouxaingxia hidden" style="position: relative;left: 5px;"></span>'+
							'</a>'+
							'<ul class="nav nav-second-level">'+
								'<li><a class="J_menuItem" data-href="orderFaultReporting.html">申请规则</a></li>'+
								'<li><a class="J_menuItem" data-href="orderFaultReporting.html">披露监管规则</a></li>'+
								'<li><a class="J_menuItem" data-href="orderFaultReporting.html">停复牌规则</a></li>'+
							'</ul>'+
						'</li>'+
						'<li>'+
							'<a href=""><span class="nav-label">审核登记事项</span></a>'+
							'<ul class="nav nav-second-level"></ul>'+
						'</li>'+
					'</ul>'+
				'</div>'+
				'<div class="infoList infoListBox">'+
					'<ul class="nav nav-second-level">'+
						'<li>'+
							'<a href=""><span class="nav-label">平台总貌</span></a>'+
							'<ul class="nav nav-second-level"></ul>'+
						'</li>'+
						'<li>'+
							'<a href="">'+
								'<span class="nav-label">会员</span>'+
								'<span class="icon-jiantouyou"></span>'+
								'<span class="icon-jiantouxaingxia hidden"></span>'+
							'</a>'+
							'<ul class="nav nav-second-level">'+
								'<li><a class="J_menuItem" href="memberList.html">会员列表</a></li>'+
								'<li><a class="J_menuItem" href="vipChangeName.html">会员更名</a></li>'+
								'<li><a class="J_menuItem" href="stopTerminateVip.html">暂停/终止会员</a></li>'+
							'</ul>'+
						'</li>'+
						'<li>'+
							'<a href="">'+
								'<span class="nav-label">股票</span>'+
								'<span class="icon-jiantouyou"></span>'+
								'<span class="icon-jiantouxaingxia hidden"></span>'+
							'</a>'+
							'<ul class="nav nav-second-level">'+
								'<li><a class="J_menuItem" data-href="orderFaultReporting.html">股票列表</a></li>'+
								'<li><a class="J_menuItem" data-href="orderFaultReporting.html">行业统计</a></li>'+
							'</ul>'+
						'</li>'+
					'</ul>'+
				'</div>'+
				'<div class="infoList infoListBox">'+
					'<ul class="nav nav-second-level">'+
						'<li>'+
							'<a href="">'+
								'<span class="nav-label">ACAE介绍</span>'+
								'<span class="icon-jiantouyou"></span>'+
								'<span class="icon-jiantouxaingxia hidden"></span>'+
							'</a>'+
							'<ul class="nav nav-second-level">'+
								'<li><a class="J_menuItem" href="aboutUsAcaeIntroduce.html">ACAE简介</a></li>'+
								'<li><a class="J_menuItem" href="aboutUsOrganizationalStructure.html">组织架构</a></li>'+
							'</ul>'+
						'</li>'+
						'<li>'+
							'<a href="">'+
								'<span class="nav-label">ACAE动态</span>'+
								'<span class="icon-jiantouyou"></span>'+
								'<span class="icon-jiantouxaingxia hidden"></span>'+
							'</a>'+
							'<ul class="nav nav-second-level">'+
								'<li><a class="J_menuItem" href="aboutUsNewsOutline.html">要闻</a></li>'+
								'<li><a class="J_menuItem" href="aboutUsAcaeMemorabilia.html">ACAE大事记</a></li>'+
							'</ul>'+
						'</li>'+
						'<li><a class="J_menuItem" href="contactUs.html">联系我们</a></li>'+
					'</ul>'+
				'</div>'+
			'</ul>'+
		'</div>'+
	'</div>'
	
var footerBox = '<div class="footer" id="footerBox">' +
	'<div class="logoImg">' +
	'<img src="../img/logo.png" style="margin-top: 0.25rem;margin-bottom: 0.25rem;height: 0.3rem;width: 1.1rem;" />' +
	'</div>' +
	'<div class="searchBox">' +
	'<div class="searchParent">' +
	'<input type="text" placeholder="搜索" name="search" id="search" autocomplete="off" value="" />' +
	'<span class="icon-Oval"></span>' +
	'</div>' +
	'</div>' +
	'<div class="shareIcon">' +
	'<span class="icon-twitter markStyle">' +
	'<span class="path1"></span><span class="path2"></span>' +
	'</span>' +
	'<span class="icon-WeChat markStyle">' +
	'<span class="path1"></span><span class="path2"></span><span class="path3"></span>' +
	'</span>' +
	'<span class="icon-linked-in markStyle">' +
	'<span class="path1"></span><span class="path2"></span>' +
	'</span>' +
	'<span class="icon icon-Facebook markStyle">' +
	'<span class="path1"></span><span class="path2"></span>' +
	'</span>' +
	'</div>' +
	'<div class="infoText">' +
	'<span><a href="#headerBox">信息披露</a></span>' +
	'<span><a href="#headerBox">会员服务</a></span>' +
	'<span><a href="#headerBox">监管规则</a></span>' +
	'<span><a href="#headerBox">平台数据</a></span>' +
	'<span><a href="#headerBox">关于我们</a></span>' +
//	'<span>意见反馈</span>' +
	'</div>' +
	'<div class="aboutText">' +
	'2018 ACAE Market Group Inc.服务条款链接条款商标隐私声明风险警告支持的浏览器' +
	'</div>' +
	'</div>'