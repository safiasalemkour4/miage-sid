<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<script type="text/javascript">
	function test() 
	{
		var i=0;
		var valid = true;
		
		if ((document.getElementById("login").value == "") ||
			(document.getElementById("password").value == ""))
			valid = false;
		
		if (valid)
			document.getElementById("userForm").submit();
		else
			document.location.replace("connection.jsp?error=empty");
	}
</script>

<jsp:include page="header.jsp"/>

<p><strong>Identification</strong></p>
<form method="POST" action="action_connection.jsp" id="userForm">
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
</form>
<p>
  <input type="button" name="ok" id="ok" value="Se connecter" onclick="javascript:test();"/>
  <input type="button" name="cancel" id="cancel" value="Annuler" onclick="javascript:document.location.replace('index.html');"/>
</p>

<jsp:include page="footer.jsp"/>