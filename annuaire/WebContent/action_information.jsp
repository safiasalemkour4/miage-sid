<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
	<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
	<jsp:setProperty name="user" property="*"/>
<%
	String cancel = request.getParameter("cancel");
	String oldPwd = request.getParameter("oldPwd");
	String newPwd = request.getParameter("newPwd");
	String confPwd = request.getParameter("confPwd");

	if (cancel != null) {
%>
	<jsp:forward page="personal_space.jsp"/>
<%
	} 
	else if ((user.getName() == "") || (user.getFirstname() == "") || (user.getEmail() == "")) 
	{
%>
	<jsp:forward page="information.jsp?error=empty"/>
<%
	} 
	else 
	{
		if ((oldPwd != "") || (newPwd != "") || (confPwd != "")) 
		{
			if ((oldPwd == "") || (newPwd == "") || (confPwd == "")) 
			{
				%>
					<jsp:forward page="information.jsp?error=pwdEmpty"/>
				<%
			} 
			else if (oldPwd.compareTo(user.getPassword()) != 0) 
			{
				%>
					<jsp:forward page="information.jsp?error=pwdFalse"/>
				<%
			} 
			else if (newPwd.compareTo(confPwd) != 0) 
			{
				%>
					<jsp:forward page="information.jsp?error=pwdConf"/>
				<%
			}
			else
				user.setPassword(newPwd);
		}

		//TODO insertion dans la base
		%>
			<jsp:forward page="information.jsp?info=save"/>
		<%
	}
%>