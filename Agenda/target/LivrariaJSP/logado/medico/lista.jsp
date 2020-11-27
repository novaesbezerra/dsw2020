<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">

	<head>
<title><fmt:message key="page.title" /></title>
	</head>

	<body>

		<%
			String contextPath = request.getContextPath().replace("/", "");
		%>
		<div align="center">
			<h1>
				<fmt:message key="books.welcome" />
			</h1>
			<h2>
				<a href="/<%=contextPath%>/especialidades">
					<fmt:message key="publishers.entity" />
				</a>
				&nbsp;&nbsp;&nbsp;
				<a href="/<%=contextPath%>/pacientes"> 
					<fmt:message key="users.entity" />
				</a> 
				&nbsp;&nbsp;&nbsp;
			    <a href="${pageContext.request.contextPath}/logout.jsp">
					<fmt:message key="exit.link" />
				</a>
				<br/>
				<br/>
				<a href="/<%=contextPath%>/medicos/cadastro">
					<fmt:message key="books.create" />
				</a>
			</h2>
			<h3><fmt:message key="books.list" /></h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="book.ID" /></th>
					<th><fmt:message key="book.title" /></th>
					<th><fmt:message key="book.publisher" /></th>
					<th><fmt:message key="book.author" /></th>
					<th><fmt:message key="book.year" /></th>
					<th><fmt:message key="book.price" /></th>
					<th><fmt:message key="actions.link" /></th>
				</tr>
				<c:forEach var="medico" items="${requestScope.listaMedicos}">
					<tr>
						<td>${medico.id}</td>
						<td>${medico.titulo}</td>
						<td>${medico.especialidade.nome}</td>
						<td>${medico.autor}</td>
						<td>${medico.ano}</td>
						<td>${medico.preco}</td>
						<td><a href="/<%= contextPath%>/medicos/edicao?id=${medico.id}">
								<fmt:message key="books.update" />
						</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
							href="/<%= contextPath%>/medicos/remocao?id=${medico.id}"
							onclick="return confirm('<fmt:message key="confirm.link" />');">
								<fmt:message key="books.delete" />
						</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</body>
</fmt:bundle>

</html>