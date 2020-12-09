<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${medico != null}">
				<fmt:message key="doctors.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="doctors.create" />
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${medico != null}">
		<input type="hidden" name="id" value="${medico.id}" />
	</c:if>
	<tr>
		<td><label for="crm"> <fmt:message key="doctor.crm" />
		</label></td>
		<td><input type="number" id="crm" name="crm" size="45"
			required value="${medico.crm}" /></td>
	</tr>
	<tr>
		<td><label for="nome"> <fmt:message key="doctor.name" />
		</label></td>
		<td><input type="text" id="nome" name="nome" size="45" required
			value="${medico.nome}" /></td>
	</tr>
	<tr>
		<td><label for="especialidade"> <fmt:message key="doctor.specialty" />
		</label></td>
		<td><input type="text" id="especialidade" name="especialidade" size="45" required
			value="${medico.especialidade}" /></td>
	</tr>
	<tr>
		<td><label for="email"> <fmt:message key="doctor.email" />
		</label></td>
		<td><input type="text" id="email" name="email" size="45" required
			value="${medico.email}" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="save.link" />" /></td>
	</tr>
</table>