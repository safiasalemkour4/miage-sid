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
			<a href="personal_space.jsp">
				<font size="5">
					<b>Annuler Résiliation</b>
				</font>					
			</a>
		</td>
		<td>
			<a href="action_resiliation.jsp">
				<font size="2">
					<b>Confirmer votre Résiliation</b>
				</font>
			</a>
		</td>
	</tr>
</table>

<jsp:include page="footer.jsp"/>