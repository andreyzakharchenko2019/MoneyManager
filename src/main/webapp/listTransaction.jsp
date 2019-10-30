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
				<td><fmt:message key = "key.welcome" bundle = "${lang}" /> 
						<c:forEach items="${currentUser.name}" var="nameUser" >
							<b><c:out value="${nameUser}"></c:out>.</b>
						</c:forEach>
						<c:if test = "${userIsnTPremium == true}">
							<fmt:message key = "key.notPremium" bundle = "${lang}"/>
						</c:if>
					<br>
					<form action="servlet" method="get">
						<input type="hidden" name="action" value="logOut" />
						<input type="submit" name="logOut" value=<fmt:message key = "key.logOut" bundle = "${lang}"/> >
					</form>				
				</td>
				<td>
				<form action="servlet" method="get">
				<input type="hidden" name="action" value="walletList" />
				<input type="hidden" name="premiumUser" value="${currentUser.premium}" />
				<input type="submit" name="walletList" value=<fmt:message key = "key.myWallets" bundle = "${lang}"/> >
				</form>
				</td>
				
				<td>
				<form action="servlet" method="get">
				<input type="hidden" name="action" value="categoryList" />
				<input type="submit" name="categoryList" value=<fmt:message key = "key.myCategory" bundle = "${lang}"/> >
				</form>
				</td>
				<td>
				<form action="servlet" method="get">
				<input type="hidden" name="action" value="moveToTransactionAdd" />
				<input type="submit" name="moveToTransactionAdd" value=<fmt:message key = "key.transactionAdd" bundle = "${lang}"/> >
				</form>	
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<c:forEach items="${transactionAttribute}" var="transaction" >
						<table>
						<tr>	
						<td>
							<tr>								
								<td>
								<fmt:message key = "key.category" bundle = "${lang}"/>
								<font color="blue" face="Comic Sans MS">${transaction.categoryForLabel}</font>
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>
								<fmt:message key = "key.wallet" bundle = "${lang}"/>
								<font color="blue" face="Comic Sans MS">${transaction.walletForLabel}</font>
								</td>
								<td></td>
								<td></td>								
							</tr>
							<tr>
								<td>
								<fmt:message key = "key.date" bundle = "${lang}"/>
								<font color="blue" face="Comic Sans MS">${transaction.date}</font>
								</td>
								<td></td>
								<td></td>							
							</tr>
							<tr>
								<td>
								<fmt:message key = "key.description" bundle = "${lang}"/>
								<font color="blue" face="Comic Sans MS">${transaction.description}</font>
								</td>
								<td></td>
								<td></td>						
							</tr>
						</td>
						<td></td>
						<td>
							<c:if test = "${transaction.typeTransaction == 1}">
								<font color="green" face="Comic Sans MS">${transaction.price}</font>
							</c:if>
							<c:if test = "${transaction.typeTransaction == 0}">
								<font color="red" face="Comic Sans MS">- ${transaction.price}</font>
							</c:if>
						</td>
						<tr>
						<td><form action="servlet" method="get">
							<input type="hidden" name="action" value="deleteTransaction" />
							<input type="hidden" name="deleteTransaction" value="${transaction.id}" />
							<input type="submit" name="deleteTransaction" value=<fmt:message key = "key.deleteTransaction" bundle = "${lang}"/> >
						</form>
						</td>
						</tr>
						<tr>						
						</table>
					</c:forEach>					
				</td>
				
			</tr>
			
					
				
				
			
		
											
					
	</table>
	</div>
		
</body>
</html>