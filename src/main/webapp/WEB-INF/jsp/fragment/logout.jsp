
<c:if test="${pageContext.request.remoteUser ne null}">
	<div class="pull-right">
		<h4>
			Hello <b><c:out value="${pageContext.request.remoteUser}" /></b>
		</h4>

		<form action="/logout" method="post">
			<input class="btn btn-primary" type="submit" value="Sign Out" /> <input
				type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
</c:if>