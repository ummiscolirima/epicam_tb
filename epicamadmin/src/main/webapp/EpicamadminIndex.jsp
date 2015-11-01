<!DOCTYPE html>

<html>

  <head> 
  	<meta http-equiv="X-UA-Compatible" content="IE=9">
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="-1"/>
    
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="gwt:property" content="locale=fr">
	
	<link rel="icon" href="imogepicamadmin/images/logo_16x16.png" type="image/png">  
	<link type="text/css" rel="stylesheet" href="Epicamadmin.css">
	<link type="text/css" rel="stylesheet" href="imogepicamadmin/gwt/standard/standard.css">	
	<link type="text/css" rel="stylesheet" href="epicamStyle.css">
	<script src="imogepicamadmin/OpenLayers.js"></script>

    <title>EPICAM</title>

	<style type="text/css">
       
        #loading {
            border: 1px solid #ccc;
            position: absolute;
            left: 45%;
            top: 40%;
            padding: 2px;
            z-index: 20001;
            height: auto;
        }

        #loading a {
            color: #225588;
        }

        #loading .loadingIndicator {
            background: white;
            font: bold 13px tahoma, arial, helvetica;
            padding: 10px;
            margin: 0;
            height: auto;
            color: #444;
        }

        #loadingMsg {
            font: normal 10px arial, tahoma, sans-serif;
        }
    </style>
    
  </head>

  <body>    
    <iframe src="javascript:''" id="__gwt_historyFrame" style="position:absolute;width:0;height:0;border:0"></iframe>
	<div id="loadingWrapper">
		<div id="loading">
			<div class="loadingIndicator">
				<!--<img src="images/pieces/48/cube_green.gif" width="32" height="32" style="margin-right:8px;float:left;vertical-align:top;"/>SmartGWT<br/>-->
				<img src="images/loading_small.gif" width="16" height="16"
					style="margin-right: 8px; float: left; vertical-align: top;" />EPICAM<br/> <span id="loadingMsg">Loading ...</span>
			</div>
		</div>
	</div>
	<div id="root"></div>
	<script type="text/javascript" language="javascript" src="imogepicamadmin/imogepicamadmin.nocache.js"></script>
  </body>
  
</html>

