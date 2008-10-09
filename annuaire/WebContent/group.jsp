<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="global" scope="session" class="tools.Global"/>
<jsp:useBean id="user" scope="session" class="tools.UserBean"/>
<jsp:setProperty name="user" property="*"/>

<jsp:include page="header.jsp"/>

<p><strong>Gestion des groupes</strong></p>
<p>Groupe(s) actuel(s) :</p>
<ul type="square">
<%
	for (String group: user.getGroupList())
	{
		%>
		<li><%=group%></li>
		<%
	}
%>
	</ul>
<p>Nouveau groupe :</p>

<form id="userForm" action="action_group.jsp" method="POST">
 	<select name="group_list" id="group_list">
<%
	for (String group: global.getExistingGroupList())
	{
		%>
		<option><%=group%></option>
		<%
	}
%>  
  	</select>
 	<input type="submit" name="group_add" id="group_add" value="Adhérer" />
</form>
<input type="button" name="return" id="return" value="Retour" onclick="javascript:document.location.replace('personal_space.jsp');" />

<jsp:include page="footer.jsp"/>