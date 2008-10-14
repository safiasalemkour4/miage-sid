<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
	<jsp:useBean id="directory" scope="session" class="tools.DirectoryBean"/>
	<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
<%
	int idUser = directory.findUserLogin(user.getLogin());
	directory.getUserList().remove(idUser);
	
	user = null;
	session.removeAttribute("user");
%>
	<jsp:forward page="index.jsp?info=userErrase"/>