<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-br">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Altera um livro</title>
</head>
<body>
	<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<h1>Altere os dados do livro</h1>
	<form action="alteraLivro">
		<input type="hidden" name="id" value="${livro.id}"> Titulo
		<input type="text" name="titulo" value="${livro.titulo}"><br>
		Lido <input type="checkbox" name="lido" value="true"
			${livro.lido ? 'checked' : ''}><br>
		Data de finalização <input type="text" name="dataFinalizacao"
			value="<fmt:formatDate value="${livro.dataFinalizacao.time}"
		 pattern="dd/MM/yyyy"/>"><br>
		 <input type="submit" value="Alterar">

	</form>
</body>
</html>