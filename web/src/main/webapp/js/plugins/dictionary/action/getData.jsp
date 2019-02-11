<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@page import="java.util.*"%>
<%@include file="/base/pub2.jsp" %>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%
	String zdmc = myreq.toCn(request.getParameter("zdmc"));			//字典名称
	String cx_type =  myreq.toCn(request.getParameter("cx_type"));	//查询类型（编码、拼音、首字母、值）
	String is_like = myreq.toCn(request.getParameter("is_like"));	//是否模糊查询
	String code = myreq.toCn(request.getParameter("code"));			//查询值
	try{
		zdmc = java.net.URLDecoder.decode(zdmc,"utf-8");
		//zdmc = "";
		code = java.net.URLDecoder.decode(code,"utf-8");
		//code = "广西北海市公安局经济犯罪";
		String s_datasource = "";
		String t_owner = "";
		String t_name = "";
		String f_code = "";
		String f_codecn = "";
		String f_all_py = "";
		String f_szm = "";
		
		pageQueryBean pqb=new pageQueryBean();
		pqb.clearSQL();		
		pqb.addSQL(" select * from tc_common.t_zd_config where zdmc = '"+zdmc+"'");		
		pqb.openSqlNoPage();
		if(pqb.getRowSet().next()){
			s_datasource=myreq.formatStr(pqb.getRowSet().getString("t_datasource"));
			t_owner=myreq.formatStr(pqb.getRowSet().getString("t_owner"));
			t_name=myreq.formatStr(pqb.getRowSet().getString("t_table"));
			f_code=myreq.formatStr(pqb.getRowSet().getString("f_code"));
			f_codecn=myreq.formatStr(pqb.getRowSet().getString("f_codecn"));
			f_all_py=myreq.formatStr(pqb.getRowSet().getString("f_all_py"));
			f_szm=myreq.formatStr(pqb.getRowSet().getString("f_szm"));
		}
		if(StringUtils.isBlank(f_code)){
			f_code = "''";
		}
		if(StringUtils.isBlank(f_codecn)){
			f_codecn = "''";
		}
		if(StringUtils.isBlank(f_all_py)){
			f_all_py = "''";
		}
		if(StringUtils.isBlank(f_szm)){
			f_szm = "''";
		}
		pqb=new pageQueryBean();
		String strPage = myreq.toCn(request.getParameter("page"));
		String strPagesize = myreq.toCn(request.getParameter("pagesize"));
		String strTotalSize =  myreq.toCn(request.getParameter("totalSize"));
		int pageNo = Integer.parseInt((StringUtils.isBlank(strPage)||!StringUtils.isNumeric(strPage))?"1":strPage);
		int pageSize = Integer.parseInt((StringUtils.isBlank(strPagesize)||!StringUtils.isNumeric(strPagesize))?"10":strPagesize);
		int totalSize = Integer.parseInt((StringUtils.isBlank(strTotalSize)||!StringUtils.isNumeric(strTotalSize))?"-1":strTotalSize);
		pqb.setDataSource(s_datasource);
		pqb.setJNDI_BufferSession(session); 	  
		pqb.setTotalSize(totalSize);    						//查询到得记录数大小 
		pqb.setPageSize(pageSize);            				//页面大小 
		pqb.setCurrentPageno(pageNo);   						//设置当前页面  小
		pqb.setPackSize(pageSize);      						//设置数据包      
		pqb.clearSQL();
		
		pqb.addSQL(" select "+f_code+" code,"+f_codecn+" codecn,"+f_all_py+" all_py,"+f_szm+" szm from ");
		pqb.addSQL(" "+t_owner+"."+t_name+" where 1=1 ");
		
		if(StringUtils.isNotBlank(code)){
			pqb.addSQL("and( "+f_code+" like '"+code+"%'");
			pqb.addSQL("or "+f_codecn+" like '"+code+"%'");
			if(StringUtils.isNotBlank(f_all_py)&&!"''".equals(f_all_py)){
				pqb.addSQL("or "+f_all_py+" like '"+code+"%'");
			}
			if(StringUtils.isNotBlank(f_szm)&&!"''".equals(f_szm)){
				pqb.addSQL("or "+f_szm+" like '"+code+"%'");
			}
			pqb.addSQL(" )");
		}
		//out.print(pqb.getSQL());
		pqb.addSQL(" order by "+f_code);
		pqb.openSql(-6); 
		DataUtil.addPageData(request,pqb);
		out.print(DataUtil.successCode(request,""));

	}catch(Exception ex){
		out.print(DataUtil.errorCode("查询失败"+ex.getMessage()));
	}

%> 