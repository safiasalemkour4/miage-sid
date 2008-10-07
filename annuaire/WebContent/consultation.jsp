<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jsp"/>

<p><strong>Consultation</strong></p>
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
<p>Code dynamique ici !</p>

<jsp:include page="footer.jsp"/>