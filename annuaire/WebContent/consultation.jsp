<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id="global" scope="session" class="tools.DirectoryBean"/>

<jsp:include page="header.jsp"/>

<p><strong>Consultation</strong></p>
<table id="table">
	<tr align="center">
		<td>
			<b>Login</b>
		</td>
		<td>
			<b>Pwd</b>
		</td>
		<td>
			<b>Nom</b>
		</td>
		<td>
			<b>Prénom</b>
		</td>
		<td>
			<b>E-Mail</b>
		</td>
		<td>
			<b>Groupe(s)</b>
		</td>
	</tr>
	<%=global.printUsers() %>
</table>
<!-- 
<p><strong>Rechercher</strong> :</p>
<form method="POST" action="personalSpace.jsp">
<table width="460" border="0">
  <tr>
    <td width="88">Prénom :</td>
    <td width="194">
      <input type="text" name="consultation_surname" id="consultation_surname" />
    </td>
    <td width="164"> ET / OU</td>
  </tr>
  <tr>
    <td>Nom :</td>
    <td>
      <input type="text" name="consultation_name" id="consultation_name" />
    </td>
    <td>ET / OU</td>
  </tr>
  <tr>
    <td>Login :</td>
    <td>
      <input type="text" name="consultation_login" id="consultation_login" />
    </td>
    <td>ET / OU</td>
  </tr>
  <tr>
    <td>Groupe :</td>
    <td>&nbsp;</td>
    <td>ET / OU</td>
  </tr>
</table>
</form>
<p><strong>Résultat :</strong></p>
<table width="200" border="0">
  <tr>
    <td>Prénom</td>
    <td>Nom</td>
    <td>Login</td>
    <td>Groupe(s)</td>
  </tr>
</table>
 -->
<p> <input type="button" name="back" id="back" value="Retour" onclick="javascript:document.location.replace('index.jsp');"/></p>

<jsp:include page="footer.jsp"/>