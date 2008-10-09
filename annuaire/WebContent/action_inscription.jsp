<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
	<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
	<jsp:setProperty name="user" property="*"/>
	<jsp:forward page="personal_space.jsp?info=inscr"/>
