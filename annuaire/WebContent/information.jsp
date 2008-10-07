<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
<jsp:setProperty name="user" property="*"/>
<jsp:include page="header.jsp"/>

<p><strong>Infos personnelles</strong></p>
<form method="POST" action="action_information.jsp">
<table width="393" border="0">
  <tr>
    <td>Prénom :</td>
    <td><input type="text" name="surname" id="surname" value="<%=user.getFirstname() %>"/></td>
  </tr>
  <tr>
    <td>Nom :
    </td>
    <td><input type="text" name="name" id="name"  value="<%=user.getName() %>"/></td>
  </tr>
  <tr>
    <td>Login :
    </td>
    <td><input type="text" name="login" id="login" disabled="disabled"  value="<%=user.getLogin() %>"/></td>
  </tr>
    <tr>
    <td>Ancien mot de passe :</td>
    <td><input type="password" name="oldpassword" id="oldpassword"/></td>
  </tr>
  <tr>
    <td>Nouveau mot de passe :</td>
    <td><input type="password" name="newpassword" id="newpassword"/></td>
  </tr>
  <tr>
    <td>Confirmation :</td>
    <td><input type="password" name="confirmpassword" id="confirmpassword"/></td>
  </tr>
  <tr>
    <td>Email :</td>
    <td><input type="text" name="email" id="email" value="<%=user.getEmail() %>"/></td>
  </tr>
</table>
<p>
  <input type="submit" name="ok" id="ok" value="Sauvegarder" />
  <input type="submit" name="cancel" id="cancel" value="Annuler" />
</p>
</form>

<jsp:include page="footer.jsp"/>