<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center" title="Registration for New User"
		style="padding-top: 50px;">
		<div
			style="height: 350px; width: 200px; outline-style: double; outline-color:green; ">
			<div>
			<marquee style="font-style: oblique;color:red;">Welcome to Regstration page</marquee>
				<div>
					<form action="Controller" method="post">
				</div>
				<div align="center">
					First Name: <input type="text" name="fname" value="">
				</div>
				<br>
				<div align="center">
					Last Name: <input type="text" name="lname" value="">
				</div>
				<br>
				<div align="center">
					Email ID: <input type="text" name="email" value="">
				</div>
				<br>
				<div align="center">
					User ID: <input type="text" name="user" value="">
				</div>
				<br>
				<div align="center">
				
					Password: <input type="password" name="pass" value="">
				</div>
				<br>
				<div align="center">
					<input type="submit" name="submitt" value="Register">
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>