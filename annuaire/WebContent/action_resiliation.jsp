<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
	<jsp:useBean id="global" scope="session" class="tools.Global"/>
	<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
<%
	int idUser = global.findUser(user.getLogin());
	global.getUserList().remove(idUser);
	
	user = null;
	session.removeAttribute("user");
	
%>
	<jsp:forward page="index.jsp?info=userErrase"/>