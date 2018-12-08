<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livro de Java, Android, Iphone, Ruby, PHP e muito mais - Casa do Código</title>
</head>
<body>	
	
	<!-- mvcUrl o PC significa Product Controller e o metodo  -->
	<form:form action="${s:mvcUrl('PC#grava').build() }" method="post" commandName="produto">
	<!-- CommandName= anota que estamos utilizando o bean produto, dessa forma nao precisamos definir produto.titulo  -->
		<div>
			<label>Titulo</label>
			<form:input path="titulo"/> <!-- os dados ficam sabado caso de erro -->
			<form:errors path="titulo"/>
		</div>
		<div>
			<label>Descrição</label>
			<form:textarea rows="10" cols="20" path="descricao"/>
			<form:errors path="descricao"/>
		</div>
		<div>
			<label>Nº Paginas</label>
			<form:input type="text" path="paginas"/>
			<form:errors path="paginas"/>
		</div>
		<div>
			<label>Data de lançamento</label>
			<form:input path="dataLancamento"/>
			<form:errors path="dataLancamento"/>
		</div>
		
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco}</label>
				<form:input type="text" path="precos[${status.index}].valor"/>
				<form:input type="hidden" path="precos[${status.index}].tipo" value="${tipoPreco}"/>
			</div>
			
		</c:forEach>
		
		<button type="submit">Cadastrar</button>
		
	</form:form>

</body>
</html>