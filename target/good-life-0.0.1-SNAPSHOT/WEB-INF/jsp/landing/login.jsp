<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="../fragments/headTag.jsp"/>

<body onload='document.f.j_username.focus();'>
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div style="border: 1px solid black; width: 300px; padding-top: 10px;">
			<br /> <h3>Login with Username and Password</h3> <br /> <span
				style="color: red">${message}</span> <br />
	

	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Cause:
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>

	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>

		<table>
			<tr>
				<td>User Id:</td>
				<td><input type='text' name='j_username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' /></td>
				<BR/>
			</tr>
			<tr height='60' valign="middle">
				<td><input name="submit" type="submit" value="submit" /></td>
				<td align="right"><input name="reset" type="reset" /></td>
			</tr>
		</table>

	</form>
	</div>
	<center><a href="userSignUp">Signup for an Account (Requires an invitation code)</a></center>
	<center><a href="requestInvitationCode">Request a misplaced invite code</a></center>
	<center><a href="resetPwdStepOne">Forgot Password (Reset)</a></center>
</body>
<jsp:include page="../fragments/footer.jsp"/>
</html>