<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String uname=request.getParameter("uname");
	String password=request.getParameter("password");
	String mobile=request.getParameter("mobile");	
	if(uname!=null&&password!=null&&mobile!=null&&!uname.isEmpty()&&!password.isEmpty()&&!mobile.isEmpty()){
		out.println(String.format("{\"result\":\"ok\"}").toString());
	}
	else{
		out.println("{\"result\":\"error\"}");
	}
%>