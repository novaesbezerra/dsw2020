<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table border="1">
	<caption>
		<c:choose>
			<c:when test="${paciente != null}">
				<fmt:message key="users.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="users.create" />
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${paciente != null}">
		<input type="hidden" name="id" value="<c:out value='${paciente.id}' />" />
	</c:if>
	<tr>
		<td><label for="nome"><fmt:message key="user.name" />
		</label></td>
		<td><input type="text" name="nome" size="45" required
			value="<c:out value='${paciente.nome}' />" /></td>
	</tr>
	<tr>
		<td><label for="email"><fmt:message key="user.email" />
		</label></td>
		<td><input type="text" name="email" size="60" required
			value="<c:out value='${paciente.email}' />" /></td>
	</tr>
	<tr>
		<td><label for="senha"><fmt:message key="user.password" />
		</label></td>
		<td><input type="text" name="senha" size="20" required
			value="<c:out value='${paciente.senha}' />" /></td>
	</tr>
	<tr>
		<td><label for="cpf"><fmt:message key="user.cpf" />
		</label></td>
		<td><input type="text" name="cpf" size="20" required
			value="<c:out value='${paciente.senha}' />" /></td>
	</tr>
	<tr>
		<td><label for="telefone"><fmt:message key="user.telefone" />
		</label></td>
		<td><input type="text" name="telefone" size="20" required
			value="<c:out value='${paciente.senha}' />" /></td>
	</tr>
	<tr>
		<td><label for="sexo"><fmt:message key="user.sexo" />
		</label></td>
		<td><input type="text" name="sexo" size="20" required
			value="<c:out value='${paciente.senha}' />" /></td>
	</tr>
	<tr>
		<td><label for="nascimento"><fmt:message key="user.nascimento" />
		</label></td>
		<td><input type="text" name="nascimento" size="20" required
			value="<c:out value='${paciente.senha}' />" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="save.link" />" /></td>
	</tr>
</table>