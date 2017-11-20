<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-br">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Adiciona um livro</title>
</head>
<body>
	<h1>Adicione um livro na lista</h1>
    <form:errors path="livro.titulo" cssStyle="color:red"/>
	<form action="adicionaLivro">
		Titulo: <input type="text" name="titulo"><br><br>
		<input type="submit" value="Salvar">
	</form>
</body>
</html>
