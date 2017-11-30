<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String fname=request.getParameter("fname");
	String comment=request.getParameter("comment");
	if(fname!=null&&comment!=null&&!fname.isEmpty()&&!comment.isEmpty()){
		out.println(String.format("{\"username\":\"%s\",\"msg\":\"%s\"}",fname,comment).toString());
	}
	else{
		out.println("{\"msg\":\"error\"}");
	}
%>