<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">

    <head>
        <title><fmt:message key="doctors.list" /></title>
    </head>
    <body>
        <div align="center">
            <table border="1">
                <tr>
                    <th><fmt:message key="doctor.ID" /></th>
                    <th><fmt:message key="doctor.crm" /></th>
                    <th><fmt:message key="doctor.name" /></th>
                    <th><fmt:message key="doctor.specialty" /></th>
                    <th><fmt:message key="doctor.email" /></th>
                </tr>
                <c:forEach var="medico" items="${requestScope.listaMedicos}">
                    <tr>
                        <td>${medico.id}</td>
                        <td>${medico.crm}</td>
                        <td>${medico.nome}</td>
                        <td>${medico.especialidade}</td>
                        <td>${medico.email}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </body>
</fmt:bundle>

</html>