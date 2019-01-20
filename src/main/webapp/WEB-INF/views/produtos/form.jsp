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
       <!--  exibindo nome de usuario-->
        <ul class="nav navbar-nav navbar-right">
		  <li><a href="#"><security:authentication property="principal.username"/></a></li>
		  <li><a href="/casadocodigo/logout"/>logout</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div>
</nav>
	
	
	
	<!-- mvcUrl o PC significa Product Controller e o metodo  -->
	<form:form action="${s:mvcUrl('PC#grava').build() }" method="post" commandName="produto" enctype="multipart/form-data">
	<!-- CommandName= anota que estamos utilizando o bean produto, dessa forma nao precisamos definir produto.titulo  -->
		
		<div class="container">
		<h1>Cadasto de Produto</h1>
		<div class="form-group">
			<label>Titulo</label>
			<form:input path="titulo" cssClass="form-control"/> <!-- os dados ficam sabado caso de erro -->
			<form:errors path="titulo"/>
		</div>
		<div class="form-group">
			<label>Descrição</label>
			<form:textarea  path="descricao" cssClass="form-control"/>
			<form:errors path="descricao"/>
		</div>
		<div class="form-group">
			<label>Nº Paginas</label>
			<form:input type="text" path="paginas" cssClass="form-control"/>
			<form:errors path="paginas"/>
		</div>
		<div class="form-group">
			<label>Data de lançamento</label>
			<form:input path="dataLancamento" cssClass="form-control"/>
			<form:errors path="dataLancamento"/>
		</div>
		
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div class="form-group">
				<label>${tipoPreco}</label>
				<form:input type="text" path="precos[${status.index}].valor" cssClass="form-control"/>
				<form:input type="hidden" path="precos[${status.index}].tipo" value="${tipoPreco}"/>
			</div>
			
		</c:forEach>
		<div class="form-group">
			<label>Sumário</label>
			<input name="sumario" type="file" class="form-control"/>
		</div>
		
		<button type="submit" class="btn btn-primary">Cadastrar</button>
		</div>
	</form:form>
	
	

</body>
</html>