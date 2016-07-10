<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="Simplicity Itself" />

<title>IT Work Book</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
</link>
<script src="js/jquery-1.8.2.min.js" type="text/javascript"></script>
<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.0/themes/base/jquery-ui.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.0/jquery-ui.min.js"></script>
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}


</style>

<script language="Javascript">
	function refreshpage() {
		document.forms.form1.submit();
	}
</script>
<script src="js/dw_tooltip_c.js" type="text/javascript"></script>
<script type="text/javascript">
dw_Tooltip.defaultProps = {
    wrapFn: dw_Tooltip.wrapImageToWidth
}
dw_Tooltip.content_vars = {
     L1: {
        img: 'images/Barchart.jpg',
        w: 900,
        h: 750
    },
L2: {
    img: 'images/CriticalityBarchart.jpg',
    w: 900,
    h: 750
},
L3: {
    img: 'images/chart.jpg',
    w: 900,
    h: 750
},
L4: {
    img: 'images/slaChart.jpg',
    w: 900,
    h: 750
}
}

</script>
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
</link>


</head>
<body>
	
	<!-- {!begin layout} -->
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="container-fluid" style="float: left">

		<form id="form1">
			<a class="showTip L1"
					href="images/Barchart.jpg"><img
						src="images/Barchart.jpg" width="560" height="700" /></a>
			
			<!--<img src="images/Barchart.jpg" width="560" height="700" border="0" />
			    -->
		</form>

	</div>

	<div class="container-fluid" style="float: left">

		<form id="form1">
		
		
				<a
					href="images/CriticalityBarchart.jpg" class="showTip L2"><img
						src="images/CriticalityBarchart.jpg" width="560" height="700" /></a>
			
			<!-- <img src="images/CriticalityBarchart.jpg" width="560" height="700"
				border="0" />    -->
			
		</form>

	</div>

	<div class="container-fluid" style="float: left">

		
		<form id="form1">
		
		<a
					href="images/chart.jpg" class="showTip L3"><img
						src="images/chart.jpg" width="560" height="700" /></a>
		<!--	<img src="images/chart.jpg" width="560" height="700" border="0" />

			     -->
			    
		</form>

	</div>

	<div class="container-fluid" style="float: left">

		
		<form id="form1">
		<a
					href="images/slaChart.jpg" class="showTip L4"><img
						src="images/slaChart.jpg" width="560" height="700" /></a>
		
			<!-- <img src="images/slaChart.jpg" width="560" height="700" border="0" />

			     -->
		</form>

	</div>




</body>
</html>
