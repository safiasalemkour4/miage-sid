<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
	<jsp:useBean id="directory" scope="session" class="tools.DirectoryBean"/>
	<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
	<jsp:setProperty name="user" property="*"/>
<%
	int idUser = directory.login(user.getLogin(),user.getPassword());

	if (idUser == -1)//TODO test Mdp
	{
		%>
			<jsp:forward page="connection.jsp?error=logFalse"/>
		<%
	}
	else
	{
		user.setFirstname(directory.getUserList().get(idUser).getFirstname());
		user.setName(directory.getUserList().get(idUser).getName());
		user.setEmail(directory.getUserList().get(idUser).getEmail());
		user.setGroupList(directory.getUserList().get(idUser).getGroupList());
		%>
			<jsp:forward page="personal_space.jsp"/>
		<%
	}
%>