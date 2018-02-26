<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function foo() {
		alert("running1");
		var xmlHttp;
		if (window.XMLHttpRequest) { // Mozilla, Safari, ...
			var xmlHttp = new XMLHttpRequest();

		} else if (window.ActiveXObject) { // IE
			var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlHttp.open('GET', "ControllerMain", true);
		xmlHttp.send("ControllerMain");
     	

        alert("running2");
	}
</script>
</head>
<body onload="foo()">



</body>
</html>