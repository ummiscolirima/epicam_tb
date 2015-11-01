
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.ResourceBundle" %>


<html>

<head>

<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<link rel="icon" href="../imogepicamadmin/images/logo_16x16.png" type="image/png">
<link type="text/css" rel="stylesheet" href="../Epicamadmin.css">

<title>EPICAM</title>

</head>


<body>
<%
	ResourceBundle rb = null;
	String locale = request.getParameter("locale");
	if (locale==null)
		rb = ResourceBundle.getBundle("org.imogene.web.client.i18n.ImogConstants", request.getLocale());
	else {
		try {
			rb = ResourceBundle.getBundle("org.imogene.web.client.i18n.ImogConstants_" + locale);
		}
		catch (Exception e){
			rb = ResourceBundle.getBundle("org.imogene.web.client.i18n.ImogConstants", request.getLocale());
		}
	}	
%>

	<!-- Top Banner -->
	<table cellspacing="0" cellpadding="0" class="login-TopPanel">
		<tr>
			<td align="left" style="vertical-align: middle;" width="100px">
				<img src="../imogepicamadmin/images/logo_32x32.png">
			</td>
			<td align="center" style="vertical-align: middle;">
				<div class="login-TopPanel-Title">EPICAM</div>
			</td>
		</tr>
	</table>


	<!-- Login Box -->
	<center>
		<table cellspacing="0" cellpadding="0" class="login-centerPanel">
			<tr>	
				<!-- 	
				<td align="right" style="vertical-align: middle;" width="100px">
					<img src="../imogepicamadmin/images/logo_web.png" style="opacity:0.6; filter:alpha(opacity=60)">
				</td>
				-->		
				<td>
					<div>
						<div class="imogene-LoginMessage"><%=rb.getString("login_wrong_id")%></div>
						<div class="imogene-Login">
							<div class="imogene-LoginPanel">
								<%
									if (locale != null) {
								%>
								<form action="../j_spring_security_check?locale=<%=locale%>" method="post">
								<%
									} else {
								%>
								<form action="../j_spring_security_check" method="post">
								<%
									}
								%>
									<div class="imogene-Login-FieldPanel">
										<div class="imogene-LoginBox"><%=rb.getString("login_login")%></div>
										<input class="imogene-LoginText" type="text" id="j_username" name="j_username">
									</div>
									
									<div class="imogene-Login-FieldPanel">
										<div class="imogene-LoginBox"><%=rb.getString("login_password")%></div>
										<input class="imogene-LoginText" type="password" id="j_password" name="j_password">
									</div>
									
									<button class="Login-Button" type="submit"><%=rb.getString("login_button")%></button>
								</form>
							</div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<!-- 
				<td>
				</td>
				-->
				<td align="right" style="vertical-align: top;">
					<table cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td class="imogene-Login-locale" align="left" style="vertical-align: top;">
									<div>
										<a href="?locale=fr">Francais</a>
									</div>
								</td>
								<td class="imogene-Login-locale" align="left" style="vertical-align: top;">
									<div>
										<a href="?locale=en">English</a>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
	</center>

</body>

</html>
