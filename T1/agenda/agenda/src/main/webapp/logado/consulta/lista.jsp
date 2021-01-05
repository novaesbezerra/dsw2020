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
				<fmt:message key="consultations.welcome" />
			</h1>
			<h2>
				<a href="/<%=contextPath%>/consultas/cadastro"> 
					<fmt:message key="consultations.create" />
				</a> 
				&nbsp;&nbsp;&nbsp; 
				<a href="${pageContext.request.contextPath}/logout.jsp"> 
					<fmt:message key="exit.link" />
				</a>
			</h2>
			<br />
			<h3><fmt:message key="consultations.list" /></h3>
			<br />
		</div>

		<div align="center">
			<table border="1">
				<caption></caption>
				<tr>
					<th><fmt:message key="consultation.ID" /></th>
					<th><fmt:message key="consultation.date" /></th>
					<th><fmt:message key="consultation.doctor.name" /></th>
					<th><fmt:message key="consultation.doctor.specialty" /></th>
					<th><fmt:message key="consultation.doctor.email" /></th>
					<th><fmt:message key="consultation.user.name" /></th>
				</tr>
				<c:forEach var="consulta" items="${requestScope.listaConsultas}">
					<tr>
						<td>${consulta.id}</td>
						<td>${consulta.data}</td>
						<td>${consulta.medico.nome}</td>
						<td>${consulta.medico.especialidade}</td>
						<td>${consulta.medico.email}</td>
						<td>${consulta.paciente.nome}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>