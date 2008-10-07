<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jsp"/>

<p><strong>Identification</strong></p>
<form method="POST" action="action_connection.jsp">
<table width="393" border="0">
  <tr>
    <td width="124">Login :</td>
    <td width="253">
      <input type="text" name="login" id="login" />
    </td>
  </tr>
  <tr>
    <td>Mot de passse :</td>
    <td>
      <input type="password" name="password" id="password" />
    </td>
  </tr>
</table>
<p>
  <input type="submit" name="ok" id="ok" value="Se connecter" />
  <input type="submit" name="cancel" id="cancel" value="Annuler" />
</p>
</form>

<jsp:include page="footer.jsp"/>