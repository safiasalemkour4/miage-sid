<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
	<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
	<jsp:setProperty name="user" property="*"/>
<%
	String cancel = request.getParameter("cancel");

	if (cancel != null)
	{
%>
	<jsp:forward page="index.html"/>
<%
	}
	else if (user.getLogin() == "")
	{
%>
	<jsp:forward page="connection.jsp?error=noLogin"/>
<%
	}
	else if (user.getPassword() == "")
	{
%>
	<jsp:forward page="connection.jsp?error=noMdp"/>
<%
	}
	else 
	{
		//Recupere Mdp du User
		if (false)//TODO test Mdp
		{
			%>
				<jsp:forward page="connection.jsp?error=logFalse"/>
			<%
		}
		else
		{
			//TODO Recuperation des données User  
		%>
			<jsp:forward page="personal_space.jsp?"/>
<%
		}
	}
%>