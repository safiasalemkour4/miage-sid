<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<script type="text/javascript">
	function test() 
	{
		var i=0;
		var valid = true;
		var mdpValid = true;
		var mdpSet = true;
		
		if ((document.getElementById("name").value == "") ||
			(document.getElementById("firstname").value == "") ||
			(document.getElementById("email").value == ""))	
			valid = false;
		
		if ((document.getElementById("oldpassword").value != "") ||
			(document.getElementById("newpassword").value != "") ||
			(document.getElementById("confirmpassword").value != ""))
		{
			if ((document.getElementById("oldpassword").value == "") ||
				(document.getElementById("newpassword").value == "") ||
				(document.getElementById("confirmpassword").value == ""))
				mdpSet = false;
			else
			{
				if (document.getElementById("newpassword").value != document.getElementById("confirmpassword").value)
					mdpValid = false;
			}		
		}
		
		if (valid)
			if (mdpSet)
			{
				if (mdpValid)
					document.getElementById("userForm").submit();
				else
					document.location.replace("information.jsp?error=pwdConf");
			}
			else
				document.location.replace("information.jsp?error=pwdEmpty");
		else
			document.location.replace("information.jsp?error=empty");
	}
</script> 
   
<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
<jsp:setProperty name="user" property="*"/>
<jsp:include page="header.jsp"/>

<p><strong>Infos personnelles</strong></p>
<form method="POST" action="action_information.jsp" id="userForm">
<table width="393" border="0">
  <tr>
    <td>Prénom :</td>
    <td><input type="text" name="firstname" id="firstname" value="<%=user.getFirstname() %>"/></td>
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
</form>
<p>
  <input type="button" name="ok" id="ok" value="Sauvegarder" onclick="javascript:test();"/>
  <input type="button" name="back" id="back" value="Retour" onclick="javascript:document.location.replace('personal_space.jsp');"/>
</p>


<jsp:include page="footer.jsp"/>