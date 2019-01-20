<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livro de Java, Android, Iphone, Ruby, PHP e muito mais - Casa do Código</title>


<c:url value="/resources/css" var="cssPath"></c:url>
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" >

<style type="text/css">
	body{
		padding-bottom: 60px;
	}
</style>

</head>
<body>	

	<form:form servletRelativeAction="/login" method="post">
		
		<div class="container">
		<h1>Login Casa do Código</h1>
		<div class="form-group">
			<label>Login</label>
			<input type="text" name="username" class="form-control"/>
		</div>
		<div class="form-group">
			<label>Senha</label>
			<input type="password" name="password" class="form-control"/>
		</div>
		
		<button type="submit" class="btn btn-primary">Logar</button>
		</div>
	</form:form>
	
	

</body>
</html>