<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
	<jsp:useBean id="directory" scope="session" class="tools.DirectoryBean"/>
	<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
	<jsp:setProperty name="user" property="*"/>
	
<% 
	String error = directory.addUser(user);
	if (error.compareTo("existingLog") == 0)
	{
		%>
			<jsp:forward page="inscription.jsp?error=existingLog"/>
		<%		
	}
	else if (error.compareTo("existingMail") == 0)
	{
		%>
			<jsp:forward page="inscription.jsp?error=existingMail"/>
		<%		
	}
	else
	{
%>
	<jsp:forward page="personal_space.jsp?info=inscr"/>
<%
	}
%>
