<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="km">
<head>
<meta  charset="UTF-8"  />


<link href="style/index_style.css" rel="stylesheet" type="text/css" />
<link href="style/ruler.css" rel="stylesheet" type="text/css" />
<link href="style/right.css" rel="stylesheet" type="text/css" />

<title>Acer Group Home</title>
<link href="/Acer_Company/images/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon" />
<meta name="keywords" content="web store, free templates, website templates, CSS, HTML" />
<meta name="description" content="Web Store Theme - free CSS template provided by templatemo.com" />
<link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="css/ddsmoothmenu.css" />

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/ddsmoothmenu.js">

/***********************************************
* Smooth Navigational Menu- (c) Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>

<script type="text/javascript">

ddsmoothmenu.init({
	mainmenuid: "templatemo_menu", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu', //class added to menu's outer DIV
	//customtheme: ["#1c5a80", "#18374a"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
})

</script>

<link rel="stylesheet" type="text/css" href="css/styles.css" />
<script language="javascript" type="text/javascript" src="scripts/mootools-1.2.1-core.js"></script>
<script language="javascript" type="text/javascript" src="scripts/mootools-1.2-more.js"></script>
<script language="javascript" type="text/javascript" src="scripts/slideitmoo-1.1.js"></script>
<script language="javascript" type="text/javascript">
	window.addEvents({
		'domready': function(){
			/* thumbnails example , div containers */
			new SlideItMoo({
						overallContainer: 'SlideItMoo_outer',
						elementScrolled: 'SlideItMoo_inner',
						thumbsContainer: 'SlideItMoo_items',		
						itemsVisible: 5,
						elemsSlide: 2,
						duration: 200,
						itemsSelector: '.SlideItMoo_element',
						itemWidth: 171,
						showControls:1});
		},
		
	});

	function clearText(field)
	{
		if (field.defaultValue == field.value) field.value = '';
		else if (field.value == '') field.value = field.defaultValue;
	}
	$(document).ready(function(){
		$('#templatemo_main').cycle({
			play:	auto})});
</script>

</head>

<body id="home">

<div id="templatemo_wrapper">
	<div id="templatemo_header">
    	<div id="site_title"><h1><a href="#"></a></h1></div>
        
        <div id="header_right">
            <ul id="language">
                
                <li><a><img src="images/kh.jpg" alt="khmer" /></a></li>
            
            </ul>

         </div> <!-- END -->
    </div> <!-- END of header -->
    
    <div id="templatemo_menu" class="ddsmoothmenu">
 
        <br style="clear: left" />
    </div> <!-- end of templatemo_menu -->
    <center>


    		



<body>
<?php
include ('about.html');
?>

	<div align="center" id="mainWrapper">
    
    		<div id="header"> </div>


	<div style="float:right; width:470px;">
        <iframe src="//www.facebook.com/plugins/likebox.php?href=https%3A%2F%2Fwww.facebook.com%2FAngkorMeas&amp;width=470&amp;height=170&amp;colorscheme=light&amp;show_faces=true&amp;border_color&amp;stream=false&amp;header=false" scrolling="no" frameborder="1" style="border:none; overflow:hidden; width:470px; height:170px;" allowTransparency="true"></iframe>
    </div>
         

        </div> <!-- END of sidebar -->

        <div class="col col_16">
        	<h4>Social</h4>
            <ul class="footer_menu">
            	<li><a href="http://www.twitter.com/acer">Twitter</a></li>
                <li><a href="http://www.facebook.com/Acer">Facebook</a></li>
                <li><a href="http://www.youtube.com/user/acer">Youtube</a></li>
                <li><a href="http://www.plus.google.com/+Acer/posts">Google+</a></li> 
		  </ul>  
        </div>
        <div class="col col_13 no_margin_right">
        	<h4>About Us</h4>
            <p>Acer Inc., with its headquarters registered at 8F, No. 88, Section 1, Xin Tai 5th Road, Xizhi District, New Taipei City, 221, Taiwan, R.O.C. and its group of companies around the world, (including, among others, Gateway, eMacines and Packard Bell, a list of which is provided at <a href="http://www.acer-group.com/officelist.html," rel="nofollow"><strong>http://www.acer-group.com/officelist.html,</strong></a> and collectively “Acer”) respect the privacy of our customers and online visitors. This Privacy Policy is to inform you of our online information practices, our practices concerning the collection, storage, processing, transfer and use of personal information, and the choices available regarding the collection and use of your personal information via this website. <a href="http://validator.w3.org/check?uri=referer" rel="nofollow"><strong>XHTML</strong></a> &amp; .</p>
        </div>
        
        
		<center>
		<div id="footer" class="cleaner h40"></div>
			<div id="footer" > Copyright © 2013 ACER GROUP | <a href="http://www.iwebsitetemplate.com" target="_parent">Website </a> by <a href="#" target="_parent">Grouup IV</a></div>
		</center>
        
        <div class="cleaner h40"></div>
		<center>
		           <div id="footer"><?php echo " &reg;  IIC "; ?> </div>

		</center>
    </div> <!-- END of footer -->   
   
</div>

</body>
</html>