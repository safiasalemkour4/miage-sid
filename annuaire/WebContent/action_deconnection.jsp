<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
	<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
<%
	user = null;
	session.removeAttribute("user");
%>
<jsp:forward page="index.jsp"/>