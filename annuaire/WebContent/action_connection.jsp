<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
	<jsp:useBean id="global" scope="session" class="tools.DirectoryBean"/>
	<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
	<jsp:setProperty name="user" property="*"/>
<%
	int idUser = global.findUserLogin(user.getLogin());

	if (idUser == -1)//TODO test Mdp
	{
%>
			<jsp:forward page="connection.jsp?error=logFalse"/>
		<%
	}
	else
	{
		if (!global.getUserList().get(idUser).verifyPwd(user.getPassword()))
		{
			%>
				<jsp:forward page="connection.jsp?error=logFalse"/>
			<%
		}
		else
		{
			user.setFirstname(global.getUserList().get(idUser).getFirstname());
			user.setName(global.getUserList().get(idUser).getName());
			user.setEmail(global.getUserList().get(idUser).getEmail());
			user.setGroupList(global.getUserList().get(idUser).getGroupList());
			%>
				<jsp:forward page="personal_space.jsp"/>
			<%
		}
	}
%>