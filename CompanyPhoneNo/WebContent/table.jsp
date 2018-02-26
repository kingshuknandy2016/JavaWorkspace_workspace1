<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Results</title>
<!-- Bring to you by http://www.CSSTableGenerator.com -->
		<link rel="stylesheet" href="table.css" type="text/css"/>	
</head>
<body>
<div class="CSS_Table_Example" style="width:600px;height:150px;" align="justify">
			<table >
				<tr> 
                   <td colspan="2" align="right">Results</td>
				</tr>
				<tr>
					<td >
						Company Name
					</td>
					<td>
						<% Object st1=request.getAttribute("Cname");
                       out.println(st1);
                     %>
					</td>

				</tr>
				<tr>
					<td >
						Phone No
					</td>
					<td>
						<% Object st2=request.getAttribute("Pno");
                       out.println(st2);
                     %>
					</td>

				</tr>
				
			</table>
		</div>

</body>
</html>