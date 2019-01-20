<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livro de Java, Android, Iphone, Ruby, PHP e muito mais - Casa do Código</title>

<c:url value="/resources/css" var="cssPath"></c:url>
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" >

</head> 
<body>
<nav class="navbar navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
        <a class="navbar-brand" href="${s:mvcUrl('HC#Home').build()}">Casa do Código</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="${s:mvcUrl('PC#lista').build()}">Lista de Produtos</a></li>
        <li><a href="${s:mvcUrl('PC#form').build()}">Cadastro de Produtos</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div>
</nav>



	<div class="container">
		<h1>Lista de Produtos</h1>
		<div>${sucesso}</div>
		<div>${falha}</div>
		<table class="table table-striped table-bordered table-hover">
			<tr>
				<th>Titulo</th>
				<th>Descrição</th>
				<th>Nº Páginas</th>
			</tr>
			<c:forEach items="${produtos}" var="produto">
			<tr>
				<!--com esse arg estamos passando o passando o id, este zero antes é padrao  -->
				<td><a href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build() }">${produto.titulo }</a></td>
				<td>${produto.descricao }</td>
				<td>${produto.paginas }</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>