<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
<jsp:setProperty name="user" property="*"/>

<jsp:include page="header.jsp"/>

<table align="center" cellspacing="10" cellpadding="10">
	<tr>
		<td align="center" colspan="2">
			<b>Thanks to have used us Directory, <%= user.getFirstname() %></b>
		<td>
	<tr>
	<tr align="center">
		<td>
			<font color="red">
			<b><a href="action_resiliation.jsp">Confirmer votre Résiliation</a></b>
			</font>
		</td>
		<td>
			<font size="4" color="green">
			<b><a href="personal_space.jsp">Annuler Résiliation</a></b>
			</font>
		</td>
	</tr>
</table>

<jsp:include page="footer.jsp"/>