<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1" style="width: 400px; border: 1px solid black">

	<tr>
		<th></th>
		<th>Nome</th>
		<th>Especialidade</th>
		<th>email</th>
		<th>Crm</th>
	</tr>
	<c:forEach var="medico" items="${medicos}">
		<tr>
			<td style="width: 10%; text-align: center"><input type="radio"
				id="${medico.key}" name="medico" value="${medico.key}" required></td>
			<td>${medico.value.nome}</td>
			<td>${medico.value.especialidade}</td>
			<td>${medico.value.email}</td>
			<td>${medico.value.crm}</td>
		</tr>
	</c:forEach>
                
</table>
<br/>
<br/>
<tr>
		<td><label for="data"> <fmt:message key="consultation.date" />
		</label></td>
		<td><input type="date" id="data" name="data" size="45" required
			value="" /></td>
                <td><label for="hora"> <fmt:message key="consultation.hour" />
		</label></td>
		<td><input type="number" id="data" name="hora" step="1" min="0" max="24" required onkeydown="return false"
			value="0" /></td>
                <td><label for="minutos"> <fmt:message key="consultation.minute" />
		</label></td>
		<td><input type="number" id="data" name="minuto" step="30" min="0" max="30" required onkeydown="return false"
			value="" /></td>
	</tr>
        <br/>
<br/>

<tr>
	<td colspan="2" align="center"><input type="submit"
		value="<fmt:message key="save.link" />" /></td>
</tr>