<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
	<jsp:useBean id="global" scope="session" class="tools.DirectoryBean"/>
	<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
<%
	String group = request.getParameter("group_list");

	if (user.getGroupList().contains(group))
	{
		%>
			<jsp:forward page="group.jsp?error=alreadyGroup"/>
		<%
	}
	else
	{
		user.getGroupList().add(group);
		global.updateUser(user);
		%>
			<jsp:forward page="group.jsp?info=addGroup"/>
		<%
	}
%>