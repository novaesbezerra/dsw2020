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

		<% String contextPath = request.getContextPath().replace("/", ""); %>
		<div align="center">
			<h1>
				<fmt:message key="doctors.welcome" />
			</h1>
			<h2>
				<a href="/<%=contextPath%>/pacientes/"> 
			    	<fmt:message key="patients.entity" />
				</a>
				&nbsp;&nbsp;&nbsp;
			    <a href="${pageContext.request.contextPath}/logout.jsp">
					<fmt:message key="exit.link" />
				</a>
				<br/>
				<br/>
				<a href="/<%=contextPath%>/medicos/cadastro">
					<fmt:message key="doctors.create" />
				</a>
			</h2>
			<h3><fmt:message key="doctors.list" /></h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="doctor.ID" /></th>
					<th><fmt:message key="doctor.crm" /></th>
					<th><fmt:message key="doctor.name" /></th>
					<th><fmt:message key="doctor.specialty" /></th>
					<th><fmt:message key="doctor.email" /></th>
					<th><fmt:message key="actions.link" /></th>
				</tr>
				<c:forEach var="medico" items="${requestScope.listaMedicos}">
					<tr>
						<td>${medico.id}</td>
						<td>${medico.crm}</td>
						<td>${medico.nome}</td>
						<td>${medico.especialidade}</td>
						<td>${medico.email}</td>
						<td><a href="/<%= contextPath%>/medicos/edicao?id=${medico.id}">
								<fmt:message key="doctors.update" />
						</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
							href="/<%= contextPath%>/medicos/remocao?id=${medico.id}"
							onclick="return confirm('<fmt:message key="confirm.link" />');">
								<fmt:message key="doctors.delete" />
						</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</body>
</fmt:bundle>

</html>