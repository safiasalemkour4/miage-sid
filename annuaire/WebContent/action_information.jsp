<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
	<jsp:useBean id="global" scope="session" class="tools.DirectoryBean"/>
	<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
	<jsp:setProperty name="user" property="*"/>
<%
	String oldPwd = request.getParameter("oldpassword");
	String newPwd = request.getParameter("newpassword");
	String confPwd = request.getParameter("confpassword");
	
	if (oldPwd != "")
	{
		if (oldPwd.compareTo(user.getPassword()) != 0) 
		{
			%>
				<jsp:forward page="information.jsp?error=pwdFalse"/>
			<%
		} 
		else
			user.setPassword(newPwd);
	}
		global.updateUser(user);
	%>
		<jsp:forward page="information.jsp?info=save"/>