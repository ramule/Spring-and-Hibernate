<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display user page</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="card">
	<div class="container-fliud">
		<div class="wrapper row">
			<div class="preview col-md-6">
				<div class="preview-pic tab-content">
				  <div class="tab-pane active" id="pic-1"><img src="http://placekitten.com/400/252" /></div>
				</div>
			</div>
			<div class="details col-md-6">
				<label><b>Name:</b></label>
				<h4>${user.username}</h4>
			<br/>
				<label><b>Email:</b></label>
				<h5>${user.email}</h5>
			<br/>
				<div class="action">
					<a class="add-to-cart btn btn-primary" href="${pageContext.request.contextPath}/order/buy" type="button">Buy</a>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>