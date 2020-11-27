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
		<td><label for="titulo"> <fmt:message key="doctor.title" />
		</label></td>
		<td><input type="text" id="titulo" name="titulo" size="45"
			required value="${medico.titulo}" /></td>
	</tr>
	<tr>
		<td><label for="autor"> <fmt:message key="doctor.author" />
		</label></td>
		<td><input type="text" id="autor" name="autor" size="45" required
			value="${medico.autor}" /></td>
	</tr>
	<tr>
		<td><label for="especialidade"> <fmt:message
					key="doctor.specialty" />
		</label></td>
		<td><select name="especialidade">
				<c:forEach items="${especialidades}" var="especialidade">
					<option value="${especialidade.key}"
						${medico.especialidade.nome==especialidade.value ? 'selected' : '' }>
						${especialidade.value}</option>
				</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td><label for="ano"> <fmt:message key="doctor.year" />
		</label></td>
		<td><input type="number" id="ano" name="ano" size="5" required
			min="1500" value="${medico.ano}" /></td>
	</tr>
	<tr>
		<td><label for="preco"> <fmt:message key="doctor.price" />
		</label></td>
		<td><input type="number" id="preco" name="preco" required
			min="0.01" step="any" size="5" value="${medico.preco}" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="save.link" />" /></td>
	</tr>
</table>