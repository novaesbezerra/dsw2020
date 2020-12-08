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
				<fmt:message key="users.welcome" />
			</h1>
			<h2>
			   	    <a href="/<%=contextPath%>/medicos"> 
			    	<fmt:message key="doctors.entity" />
				</a> 
				&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/logout.jsp">
					<fmt:message key="exit.link" />
				</a>
				<br/>
				<br/>
				<a href="/<%=contextPath%>/pacientes/cadastro"> 
					<fmt:message key="users.create" />
				</a> 
			</h2>
			<h3><fmt:message key="users.list" /></h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="user.ID" /></th>
					<th><fmt:message key="user.email" /></th>
					<th><fmt:message key="user.password" /></th>
					<th><fmt:message key="user.name" /></th>
					<th><fmt:message key="user.role" /></th>
					<th><fmt:message key="actions.link" /></th>
				</tr>
				<c:forEach var="paciente" items="${requestScope.listaPacientes}">
					<tr>
						<td><c:out value="${paciente.id}" /></td>
						<td><c:out value="${paciente.email}" /></td>
						<td><c:out value="${paciente.senha}" /></td>
						<td><c:out value="${paciente.nome}" /></td>
			<%-- Comment --%>
			<%-- <td><c:out value="${paciente.papel}" /></td> --%>
						
						<td><a
							href="/<%= contextPath %>/pacientes/edicao?id=<c:out value='${paciente.id}' />">
								<fmt:message key="users.update" />
						</a> 
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a
									href="/<%= contextPath %>/pacientes/remocao?id=<c:out value='${paciente.id}' />"
									onclick="return confirm('<fmt:message key="confirm.link" />');">
									<fmt:message key="users.delete" />
								</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>