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
	<form action="servlet" method="get">
	<input type="hidden" name="action" value="addTransaction" />
	<table>
			<tr> 				
				<td><label for ="amountTransaction"><fmt:message key = "key.amount" bundle = "${lang}" /></label></td>				
				<td><input id ="amountTransaction" type="text" name="amountTransaction" required pattern="^[ 0-9]+$"></td>
				<td>				
					<select name="typeTransaction">
						<option value="0"><fmt:message key = "key.expense" bundle = "${lang}" /></option>
						<option value="1"><fmt:message key = "key.income" bundle = "${lang}" /></option>
					</select>
				</td>				
			</tr>
			<tr>
				<td><label><fmt:message key = "key.category" bundle = "${lang}" /></label></td>
				<td>
					<select name="category">
					<c:forEach items="${listCategory}" var="listCategory">
					<option value="${listCategory.id}">${listCategory.name_category}</option>
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td><label><fmt:message key = "key.wallet" bundle = "${lang}" /></label></td>
				<td>
				<select name="nameWallet">
					<c:forEach items="${listWallet}" var="listWallet">
					<option value="${listWallet.id}">${listWallet.name_wallet}</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td><label><fmt:message key = "key.date" bundle = "${lang}" /></label></td>
				<td><input type="date" name="dateParam"></td>
			</tr>			
			<tr>
				<td><label><fmt:message key = "key.description" bundle = "${lang}" /></label></td>
				<td><textarea name="comment" cols="20" rows="2"></textarea></td>
			</tr>
			<tr>
			<td></td><td><input type="submit" name="addTransaction" value=<fmt:message key = "key.transactionAdd" bundle = "${lang}"/> ></td>
			</tr>
					
					
				
			
					
	</form>				
	</table>
	</div>
		
</body>
</html>