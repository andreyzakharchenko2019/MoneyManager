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
					<c:forEach items="${listCategory}" var="listCategory" >
						<tr>	
							<td>						
								<font color="green" face="Comic Sans MS">${listCategory.name_category}</font>
							</td>
							<td>
							</td>
						</tr>					
					</c:forEach>
						<tr>
						<form action="servlet" method="get">
						<input type="hidden" name="action" value="addCategory" />
						<td>	
							<input type="text" name="nameCategory" value=<fmt:message key = "key.nameCategory" bundle = "${lang}" />>
						</td>
						<td>						
							<input type="submit" name="addCategory" value=<fmt:message key = "key.addCategory" bundle = "${lang}"/> >
						</td>						
						</form>
														
						</tr>
					</table>
					
				
				
			
		
											
					
	</table>
	</div>
		
</body>
</html>