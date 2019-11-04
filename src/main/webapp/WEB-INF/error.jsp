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

	<fmt:setLocale value = "${locale}"/>
	<fmt:setBundle basename = "local" var = "lang"/>
	<div align="center">	
		<table>
		<tr>
			<td>
				<fmt:message key = "key.error404" bundle = "${lang}" />
			</td>
		</tr>
		<tr>
			<td>
				<form action="servlet" method="post">
				<input type="hidden" name="action" value="goToBack" />
				<input type="submit" name="goToBack" value=<fmt:message key = "key.back" bundle = "${lang}"/> >
				</form>
			</td>				
		</tr>
					
		</table>		
	</table>
	</div>
		
</body>
</html>