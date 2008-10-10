<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
	<jsp:useBean id="global" scope="session" class="tools.Global"/>
	<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
	<jsp:setProperty name="user" property="*"/>
	
<% 
	if (!global.addUser(user))
	{
%>
	<jsp:forward page="inscription.jsp?error=existingLog"/>
<%		
	}
	else
	{
%>
	<jsp:forward page="personal_space.jsp?info=inscr"/>
<%
	}
%>
