<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
	<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
	<jsp:setProperty name="user" property="*"/>
<%

	String cancel = request.getParameter("cancel");
	String confPwd = request.getParameter("confirmpassword");

	if (cancel != null)
	{
%>
	<jsp:forward page="index.html"/>
<%
	}
	else if ((user.getName() == "") || (user.getFirstname() == "") || (user.getLogin() == "") || (user.getPassword() == "") || (confPwd == "") || (user.getEmail() == ""))
	{
%>
	<jsp:forward page="inscription.jsp?error=empty"/>
<%
	}
	else if (user.getPassword().compareTo(confPwd) != 0)
	{
%>
	<jsp:forward page="inscription.jsp?error=pwdConf"/>
<%
	}
	else
	{
%>
	<jsp:forward page="personal_space.jsp?info=inscr"/>
<%
	}
%>