<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="tools.DirectoryMsg"%><html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link type="text/css" rel="stylesheet" href="style.css"/>
    <title>Welcome to the Geek directory</title>
</head>
<body>
    <div id="header">
        <img src="banner.png"/>
    </div>
    <div id="error">
    	<%
    		if (request.getParameter("error") != null)
    	    		{
    	%>
    	<%="<script language='JavaScript'> document.getElementById(\"error\").style.display = 'block'; </script>"%>
    	<%="erreur :<br>"+ new DirectoryMsg().error(request.getParameter("error"))%>
    	<%
    		}
    	%>
    </div>
    <div id="info">
    	<%
    		if (request.getParameter("info") != null)
    	    		{
    	%>
    	<%="<script language='JavaScript'> document.getElementById(\"info\").style.display = 'block'; </script>"%>
    	<%="Information :<br>"+ new DirectoryMsg().info(request.getParameter("info"))%>
    	<%
    		}
    	%>
    </div>
    <div id="content">
        <div>
        	<div>