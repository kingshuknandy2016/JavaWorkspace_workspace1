// Ajaxsc.js
// doSuggest

function doSuggest(word) {
	if (word.length > 0) {
		var request = null;
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}

		if (request) {
			var url = "Page1.jsp";
			url += "?suggestword=" + word;

			request.open("POST", url);
			request.onreadystatechange = function() {

				if (request.readyState == 4) {
					document.getElementById("theResults").innerHTML = request.responseText;
					document.getElementById("theResults").style.visibility = "visible";
				}

			}
			request.send(null);

		} else {
			alert("Your browser don't support AJax !");
		}
	} else {
		document.getElementById("theResults").innerHTML = "";
		document.getElementById("theResults").style.visibility = "hidden";
	}

}