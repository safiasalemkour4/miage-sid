<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jsp"/>

<p><strong>Inscription </strong></p>
<form method="POST" action="action_inscription.jsp">
<table width="393" border="0">
  <tr>
    <td>Prénom :</td>
    <td><input type="text" name="firstname" id="firstname" /></td>
  </tr>
  <tr>
    <td>Nom :
    </td>
    <td><input type="text" name="name" id="name" /></td>
  </tr>
  <tr>
    <td>Login :
    </td>
    <td><input type="text" name="login" id="login" /></td>
  </tr>
  <tr>
    <td>Mot de passe :</td>
    <td><input type="password" name="password" id="password"/></td>
  </tr>
  <tr>
    <td>Confirmation :</td>
    <td><input type="password" name="confirmpassword" id="confirmpassword" /></td>
  </tr>
  <tr>
    <td>Email :</td>
    <td><input type="text" name="email" id="email" /></td>
  </tr>
</table>
<p>
  <input type="submit" name="ok" id="ok" value="S'inscrire" />
  <input type="submit" name="cancel" id="cancel" value="Annuler" />
</p>
</form>

<jsp:include page="footer.jsp"/>