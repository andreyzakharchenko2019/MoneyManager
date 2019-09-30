<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Money Manager</title>
</head>
<body>
	<div align="center">
	
		<form action="servlet" method="post" >	
		<input type="hidden" name="action" value="changeLanguage" />
			<input type="submit" name="clRU" value="Русский">
			<input type="submit" name "clEN" value="English">
		</form>
		
		<h1>Money Manager</h1>
		
		<fmt:setLocale value = "${locale}"/>
		<fmt:setBundle basename = "local" var = "lang"/>

	<form action="servlet" method="get" >
	<input type="hidden" name="action" value="login" />
	
		<table>
			<tr> 
				<td><label for ="userName"><fmt:message key = "key.email" bundle = "${lang}" /></label></td>				
				<td><input id ="userName" type="email" name="login" required></td>
			</tr>	
			<tr> 
				<td><label for ="userPassword"><fmt:message key="key.password" bundle = "${lang}" /></label></td>				
				<td><input id ="userPassword" type="password" name="password" required></td>				
			</tr>		
	</table>	
	
	<input type="submit" name="signup" value=<fmt:message key = "key.login" bundle = "${lang}" />></td>
	</form>
	
	<br>
	
	<c:if test = "${exception == 101}">
		<fmt:message key = "key.incorrectloginorpassword" bundle = "${lang}"/>
	</c:if>	
	
	</div>	
</body>
</html>