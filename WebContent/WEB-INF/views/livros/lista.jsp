<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-br">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Lista de Livros</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
</head>
<body>
	<h1>Lista dos livros</h1>
	<table border="1">
		<tr>
			<th>Id</th>
			<th>Titulo</th>
			<th>Lido?</th>
			<th>Data de leitura</th>
		</tr>
		<c:forEach items="${livros}" var="livro">
			<tr>
				<td>${livro.id}</td>
				<td>${livro.titulo}</td>
				<c:if test="${livro.lido eq false}">
					<td><a href="finalizaLivro?id=${livro.id}">
							Finalizar </a></td>
				</c:if>
				<c:if test="${livro.lido eq true}">
					<td>Lido</td>
				</c:if>
				<td><fmt:formatDate value="${livro.dataFinalizacao.time}"
						pattern="dd/MM/yyyy" /></td>
				<td><a href="removeLivro?id=${livro.id}">Remover</a></td>
				<td><a href="mostraFormulario?id=${livro.id}">Alterar</a></td>
			</tr>
		</c:forEach>
	</table>

	<br>
	<a href="novoLivro">Adicionar um livro</a>
</body>
</html>
