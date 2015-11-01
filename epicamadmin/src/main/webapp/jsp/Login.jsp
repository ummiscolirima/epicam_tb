
<%@page import="java.util.ResourceBundle" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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

	<div id="mainPage">
		<div class="login-TopPanel">
			<center>
			<table cellspacing="0" cellpadding="0" class="login-TopPanel">
			<tr>
				<td align="left" style="vertical-align: middle;padding:5px;" width="20%">
					<img src="../imogepicamadmin/images/logoImogene.png">
				</td>
				<td align="right" style="vertical-align: middle;padding:5px;" width="20%">
					<img src="../imogepicamadmin/images/logoEPICAM.png">
				</td>
			</tr>
		</table>
		</center>
		</div>

	<!-- Login Box -->
	<div id="leftcolumn">
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
						<div class="imogene-LoginMessage"></div>
						<div class="imogene-Login">
							<div class="imogene-LoginPanel">
								<%	if (locale != null) { %>
								<form action="../j_spring_security_check?locale=<%=locale%>" method="post">
								<%	} else { %>
								<form action="../j_spring_security_check" method="post">
								<%	} %>
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
	
	<!-- 	for Logos -->

		<table style="text-align: center; height: 281px; width: 25%;" cellpadding="2" cellspacing="2">
			<tbody>
				<tr>
					<td>
						<img style="width: 150px; height: 130px;" id="minsanteLogo" alt="MINSANTE logo" src="../imogepicamadmin/images/logoMINSANTE.png">
					</td>
					<td>
						<img style="width: 150px; height: 130px;" id="logoPNLT" alt="PNLT logo" src="../imogepicamadmin/images/logoPNLT.png">
					</td>
				</tr>
				<tr>
					<td>
						<img style="width: 150px; height: 130px;" id="uy1Logo" alt="UY1 logo" src="../imogepicamadmin/images/logoUY1.png">
					</td>
					<td>
						<img style="width: 150px; height: 130px;" id="cpcLogo" alt="CPC logo" src="../imogepicamadmin/images/logoCPC.png">
					</td>
				</tr>
				<tr>
					<td>
						<img style="width: 150px; height: 130px;" id="MEDESlogo" alt="MEDES logo" src="../imogepicamadmin/images/logoMedes.png">
					</td>
					<td>
						<img style="width: 150px; height: 130px;" id="prodesoLogo" alt="PRODESO logo" src="../imogepicamadmin/images/logoProdeso.png"><br>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		
		<div id="mainContent" align="center">
				<img id="imageTB" alt="Image sur la TB" src="../imogepicamadmin/images/tuberculose.png"><br>
		</div>	
		
		<div id="footerPage">
			<p align="center">
				EPICAM Version 1.0 <br /> 
				Copyright &#64 2014 Electronic surveillance system.
			</p>
		</div>
	</div>

</body>

</html>
