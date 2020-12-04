<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1" style="width: 400px; border: 1px solid black">

	<tr>
		<th></th>
		<th>Título</th>
		<th>Especialidade</th>
		<th>Autor</th>
		<th>Ano</th>
		<th>Preço</th>
	</tr>
	<c:forEach var="medico" items="${medicos}">
		<tr>
			<td style="width: 10%; text-align: center"><input type="radio"
				id="${medico.key}" name="medico" value="${medico.key}" required></td>
			<td>${medico.value.titulo}</td>
			<td>${medico.value.especialidade}</td>
			<td>${medico.value.autor}</td>
			<td>${medico.value.ano}</td>
			<td>${medico.value.preco}</td>
		</tr>
	</c:forEach>
</table>
<br/>
<br/>

<tr>
	<td colspan="2" align="center"><input type="submit"
		value="<fmt:message key="save.link" />" /></td>
</tr>