<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	function test() 
	{
		var i=0;
		var valid = true;
		var mdpValid = true;
		
		while ((i<6) && (valid))
		{
			if (document.getElementById("userForm").elements[i].value == "")
				valid = false;
			i++;
		}
		
		if (document.getElementById("password").value != document.getElementById("confirmpassword").value)
			mdpValid = false;
		
		if (valid)
		{
			if (mdpValid)
				document.getElementById("userForm").submit();
			else
				document.location.replace("inscription.jsp?error=pwdConf");
		}
		else
			document.location.replace("inscription.jsp?error=empty");
	}
</script>
	
<jsp:include page="header.jsp"/>

<p><strong>Inscription </strong></p>
<form id="userForm" action="action_inscription.jsp" method="post">
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
</form>
<p>
  <input type="button" name="ok" id="ok" value="S'inscrire" onclick="javascript:test();"/>
  <input type="button" name="cancel" id="cancel" value="Annuler" onclick="javascript:document.location.replace('index.jsp');"/>
</p>
<jsp:include page="footer.jsp"/>