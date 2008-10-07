<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
<jsp:setProperty name="user" property="*"/>
<jsp:include page="header.jsp"/>

<table align="center" cellspacing="10" cellpadding="10">
	<tr>
		<td align="center" colspan="3">
			<b>Welcome to your Directory, <%= user.getFirstname() %></b>
		<td>
	<tr>
	<tr align="center">
		<td>
			<b><a href="information.jsp">Mes informations personnelles</a></b>
		</td>
		<td>
			<b><a href="group.jsp">Mes groupes</a></b>
		</td>
		<td>
			<b><a href="resilation">Résiliation</a></b>
		</td>
	</tr>
</table>

<jsp:include page="footer.jsp"/>