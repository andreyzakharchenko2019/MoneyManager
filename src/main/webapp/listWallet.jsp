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
					<c:forEach items="${listWallet}" var="listWallet" >
						<tr>	
							<td>						
								<font color="green" face="Comic Sans MS">${listWallet.nameWallet}</font>
							</td>
							<td>						
								<font color="blue" face="Comic Sans MS">${listWallet.amount}</font>
							</td>
							<td>						
								<font color="blue" face="Comic Sans MS">${listWallet.currencyForLabel}</font>
							</td>
						</tr>					
					</c:forEach>
						<tr>
						<form action="servlet" method="get">
						<input type="hidden" name="action" value="addWallet" />
						<td>	
							<input type="text" name="nameWallet" value=<fmt:message key = "key.nameWallet" bundle = "${lang}" />>
						</td>
						<td>	
							<input type="text" id ="amountWallet" value="0" name="amountWallet" required pattern="^[ 0-9]+$" />
						</td>
						<td>	
							<select name="currency">
								<c:forEach items="${listCurrency}" var="listCurrency">
								<option value="${listCurrency.id}">${listCurrency.nameCurrency}</option>
								</c:forEach>
							</select>
						</td>
						
						<td>						
							<input type="submit" name="addWallet" value=<fmt:message key = "key.addWallet" bundle = "${lang}"/> >
						</td>						
						</form>
														
						</tr>
					</table>
					
				
				
			
		
											
					
	</table>
	</div>
		
</body>
</html>