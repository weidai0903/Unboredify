<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Hello App Engine</title>
    <link rel="stylesheet" href="/bootstrap.min.css">
    <style type="text/css">
    h1 { 
    padding: 0;
    margin-left: 200px !important;
    vertical-align: center;
}

html {
    height: 100%;
}

body {
    margin: auto;
    font-size:32px;
}

div {
    float: left;
    margin-top: 5px;
    margin-left: 15px;
}

ol, ul {
    list-style: none !important;
    clear: both !important;
}

li {
    clear: both !important;
}

a img {
   border: none
}

body {
   padding:10px;
   padding-bottom:40px;   /* Height of the footer */
}

#footer {
   position:absolute;
   bottom:0;
   width:100%;
}

#google {
    margin-top: 15px !important;
}
    </style>
  </head>

  <body>
	<center>
    <h1>Bored? Unboredify yourself made really easy</h1>
	<center>
	<h2>Simply refresh to see a new interesting schedule!</h2>

    <ul>
	<jsp:include page="/unboredify" />
	
	</ul>
	<center>
  </body>
      <div id="footer"><div id="google"><a href=""><img src="http://code.google.com/apis/maps/documentation/places/images/powered-by-google-on-white.png" /></a></div>
                     <div><a href=""><img src="http://developer.quova.com/public/Mashery/images/masherymade.png" /></a></div>
                     <div><a class="info" href="https://github.com/twitter/bootstrap"><h5>Bootstrap on GitHub</h5></a></div>
                     </div>
</html>
