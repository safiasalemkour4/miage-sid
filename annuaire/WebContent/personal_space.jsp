<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
<jsp:setProperty name="user" property="*"/>

<jsp:include page="header.jsp"/>

<table align="center" cellpadding="15">
	<tr>
		<td align="center" colspan="3">
			<b>Welcome to your Directory, <%= user.getFirstname() %></b>
		<td>
	<tr>
	<tr align="center">
		<td>
			<b><a href="information.jsp">Mes infos persos</a></b>
		</td>
		<td>
			<b><a href="group.jsp">Mes groupes</a></b>
		</td>
		<td>
			<b><a href="resiliation.jsp">Résiliation</a></b>
		</td>
	</tr>
		<tr>
		<td align="center" colspan="3">
			<b><a href="action_deconnection.jsp">Deconnection</a></b>
		<td>
	<tr>
</table>

<jsp:include page="footer.jsp"/>