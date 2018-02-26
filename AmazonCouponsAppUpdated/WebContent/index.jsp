<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function foo(){
	
	var isOpera = !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0;
	var isFirefox = typeof InstallTrigger !== 'undefined';   
	var isSafari = Object.prototype.toString.call(window.HTMLElement).indexOf('Constructor') > 0;
	var isChrome = !!window.chrome && !isOpera;             
	var isIE = false || !!document.documentMode;   
	var output;


	 if(isOpera){
		output="Opera";
	}else if(isFirefox){
		output="Firefox";
	}else if(isSafari){
		output="Safari";
	}
	else if(isChrome){
		output="Chrome";
	}else if(isIE){
		output="IE";
	}
	 
	var xhttp;
	if (window.XMLHttpRequest) {
	    xhttp = new XMLHttpRequest();
	    } else {
	    // code for IE6, IE5
	    xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xhttp.open("POST", "MainController?pass="+output, true);
	xhttp.send("MainController");

}

</script>
</head>
<body  onload="foo()">
<center><h1>All coupons will be clipped</h1></center>

</body>
</html>