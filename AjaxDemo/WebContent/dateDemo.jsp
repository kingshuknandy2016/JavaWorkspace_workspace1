<html>
<head>

<title>Ajax Example</title>

<script language="Javascript">
	function postRequest(strURL) {

		var xmlHttp;

		if (window.XMLHttpRequest) { // Mozilla, Safari, ...

			var xmlHttp = new XMLHttpRequest();

		} else if (window.ActiveXObject) { // IE

			var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

		}

		xmlHttp.open('POST', strURL, true);

		xmlHttp.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');

		xmlHttp.onreadystatechange = function() {

			if (xmlHttp.readyState == 4) {

				updatepage(xmlHttp.responseText);

			}

		}

		xmlHttp.send(strURL);

	}

	function updatepage(str) {

		document.getElementById("result").innerHTML =

		"<font color='red' size='5'>" + str
				+ "</font>_____________________$tag__";
		;

	}

	function showCurrentTime() {

		var rnd = Math.random();

		var url = "time.jsp?id=" + rnd;

		postRequest(url
</script>

</head>

<body>

	<h1 align="center">
		<font color="#000080">Ajax Example</font>
	</h1>

	<p>
		<font color="#000080">&nbsp;This very simple Ajax Example
			retrieves the current date and time from server and shows on the
			form. To view the current date and time click on the following
			button.</font>
	</p>

	<form name="f1">

		<p align="center">
			<font color="#000080">&nbsp;<input value="   Show Time   "
				type="button" onclick='JavaScript:showCurrentTime()' name="showdate"></font>
		</p>

		<div id="result" align="center"></div>

	</form>

	<div id=result></div>

</body>


</html>
