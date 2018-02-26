<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OUTPUT</title>
<style type="text/css">
   td{
   height: 50px;
   width: 100px
   
   }
</style>
</head>
<body>

<div align="center">
		<table border="8px" width="500px" height="50px">
			<thead>
				<tr>
					<td colspan="2" align="center">Results</td>
				</tr>

			</thead>
			<tbody>

				
				<tr>
					<td>Company Name</td>
					<td>
					<% Object st1=request.getAttribute("Cname");
                       out.println(st1);
                     %>
					
					</td>

				</tr>
				<tr>
					<td>Phone No</td>
					<td>
					<% Object st2=request.getAttribute("Pno");
                       out.println(st2);
                     %>
					
					</td>

				</tr>




			</tbody>

		</table>


	</div>

</body>
</html>