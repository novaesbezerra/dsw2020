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
				<fmt:message key="admin.welcome" />
			</h1>
			<h2>Seja bem vindo, Admin!</h2>
			
			<h2> Selecione uma categoria abaixo para editar, ou 'logout' para sair: </h2>
			<h2>
			   	    <a href="/<%=contextPath%>/medicos/"> 
			    	<fmt:message key="doctors.entity" />
				</a> 
                                &nbsp;&nbsp;&nbsp;
				<a href="/<%=contextPath%>/pacientes/"> 
			    	<fmt:message key="pacients.entity" />
				</a> 
				&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/logout.jsp">
					<fmt:message key="exit.link" />
				</a>
				<br/>
				<br/>
				</h2>
			<br/>
		</div>
	</body>
</fmt:bundle>

</html>