<html>
<head>
<script language="javascript">
	function postRequest(strURL) {
		var xmlHttp;

		if (window.XMLHttpRequest) { // For Mozilla, Safari, ...
			var xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) { // For Internet Explorer
			var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlHttp.open('POST', strURL, true);
		xmlHttp.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				alert("Response Text.."+xmlHttp.responseText);
				updatepage(xmlHttp.responseText);
			}
		}
		xmlHttp.send(strURL);
	}

	function updatepage(str) {
		alert("Recvied Arg.."+str);
		if (str.trim() == "yes") {
			alert("Welcome User");
		} else {
			alert("Invalid Login! Please try again!");
		}
	}

	function call_login() {
		var username = window.document.f1.username.value;
		var password = window.document.f1.password.value;
		var url = "LoginErrorMsg.jsp?username=" + username + "&password="
				+ password;
		alert(url);
		postRequest(url);
	}
</script>
</head>

<body>
	<Center>

		<form name="f1" onsubmit="return call_login();">
			<table border="0" bgcolor="#CCCCFF" cellspacing="1" cellpadding="3"
				width="287">
				<tr>
					<td align="left" colspan="2" width="275"><b><font size="5"
							color="#000080">Login</font></b></td>
				</tr>
				<tr>
					<td align="right" width="81"><b><font color="#000080">User
								Name:</font></b></td>
					<td width="184"><input type="text" name="username" id="user"
						size="20" value="" /></td>
				</tr>
				<tr>
					<td align="right" width="81"><b><font color="#000080">Password:</font></b></td>
					<td width="184"><input type="password" name="password"
						size="20" value="" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center" width="275"><input
						type="button" name="a1" value="Login" onclick="call_login()"></td>
				</tr>
			</table>
		</form>

	</center>
</body>
</html>

