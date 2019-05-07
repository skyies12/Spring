<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.study.spring20.dto.BDto"%>
<%@page import="com.study.spring20.dao.BDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
   
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="resources/css/normalize.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
        <script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
         <script src="http://code.jquery.com/jquery.js"></script>
    <title>K.SW Project</title>
    <style>
        #wrap {
            width : 100%;
            height : 100vh;
            position: relative;
        }
        @charset "uft-8";
@import url('https://fonts.googleapis.com/css?family=Lato:700|Nanum+Gothic');

* {margin : 0; padding : 0; box-sizing : border-box;}
ul, ol, li {list-style-type : none;}
hr {display : none;}
h1 {margin : 0;}
a {text-decoration : none;}
button {border : none; background : none;}
img {border : none;}
body {font-family: 'Nanum Gothic', sans-serif}
.blind {position : absolute; top : -9999px; left : -9999px;}
.blind1 {display : none;}
.pc {display : none;}
/*font-family: 'Lato', sans-serif; */

/* header */

/* header end */

/* menu bar */
.nav-bar {
  position : fixed;
  top : 0;
  left : 0;
  width : 100%;
  height : 40px;
  background-color : rgba(222,50,48,0.9);
  z-index: 98;
  display: block;
}
 #lsbtn {
    position: absolute;
    right : 55%;
    top: 5px;
    z-index: 99;
     width : 150px;
} 
.btn.btn-outline-danger {
    	font-size : 12px;
    	color : #fff;
    }
.btn-outline-danger {
    width : 60px;
    height : 25px;
    padding : 0;
    border-color: #fff;
    font-size : 12px;
}
.btn-outline-danger a{
    font-size :12px;
    padding : 0;
    color: #fff;
    text-decoration: none;
}

.button-wrap {
    cursor: pointer;
    position: absolute;
    float: right;
    width : 25px;
    height : 25px;
     top : 10px;
    right : 25px;
    transform: rotate(0deg);
 }
 .btn-outline-success {
 	color : #d30923;
 	
 	border-color : #d30923;
 }
.btn-outline-danger.write:hover {
	color : #fff;
	background-color : #d30923;	
	border-color : #d30923;
}
#select {
	float : right;
}
#write {
	font-size : 14px;
}
.btn-outline-danger.write {
	color : #d30923;
	border : 1px solid #d30923;
}
 .badge-success {
	 width : 70px;
	 height : 30px;
	 line-height: 25px;
	 	
}
.bbbtn {
		background-color : #fff;
		color : #d30923;
		font-size : 16px;
		cursor : pointer;
		width : 78px;
		text-align: center;
		height : 38px;
		line-height : 38px;
		border-radius : 8%;
		margin : 0;
		border : none;
		border : 1px solid #d30923;
		transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
	}
	.bbbtn:hover {
		text-decoration: none;
		color : #fff;
		background-color : #d30923;
	}
	 button.btn.btn-secondary.check{
	 	background-color : #fff;
	 	color : #000;
	 	border: none;
	 }
	 button.btn.btn-secondary {
		background-color : #de3230;
		color : #fff;
	}
	.btn.btn-secondary {
		background-color : #fff;
	 	color : #000;
	 	border: none;
	}
	.btn.btn-secondary:hover, button.btn.btn-secondary.check:hover {
		background-color : #de3230;
		color : #fff;
	}
	  .btn-toolbar {
	  	display : block;
	  }
	  #title {
    color: #000;
}
.thead {
    background-color: #de3230;
    color: #fff;
}
#title:hover {
	text-decoration: none;
}
.button-wrap span {
    width :100%; height : 2px; background : #fff;
    position: absolute;
    opacity: 1;
    left: 0;
    transform: rotate(0deg);
    display: block;
}
.button-wrap span:nth-child(1) {
     position: absolute;
     top : 0px;
 }
.button-wrap span:nth-child(2) {
    position: absolute;
    top : 9px;
}
.button-wrap span:nth-child(3) {
    position: absolute;
    top : 18px;
}

.button-wrap.on {
   cursor: pointer;
    top : 10px;
    right : 25px;
    width : 25px;
    height : 25px;
}
.badge {
	padding: .2em .3em;
    font-size: 50%;
}
.button-wrap.on span:nth-child(1) {
    top : 9px;
  transform: rotate(135deg);
    }
.button-wrap.on span:nth-child(2) {
   display: none;
}
.button-wrap.on span:nth-child(3) {
 top : 9px; 
 transform: rotate(-135deg);
}

/* menu bar end */
/* menu */
.menu {
    position: absolute;
    width : 100%;
    height : 300px;
    z-index: 1;
    display: none;
    top : 40px;
}
.menu li {
    text-align: center;
    font-size : 15px;
    font-family: 'Lato', sans-serif;
}
.menu li a {
   text-decoration: none;
   background-color: rgba(225,50,48,0.9);
   display: block;
   color: #fff;
    width : 100%;
    height : 60px;
    line-height: 60px;
}
.menu li:hover a {
   background-color: rgba(145,1,20,0.9);
}
/* menu end */


/* main slide */


   
/* main slide end */
/* header end */
.sns-wrap {
    display : none;
}
h2 {
	font-size : 14px;
	margin : 5px 0 10px 0;
}
.table td, .table th {
	padding : 0.65rem;
}
.table {
	font-size : 11px;
	text-align: center;
}
.btn.btn-secondary.check {
	font-size : 12px;
}
.btn.btn-secondary {
	font-size : 12px;
}
#wrap1 {
	display: flex;
	
}
.contents {
    width : 100%;
    height : 81.9%;
}
.container {
	margin : 50px auto;
}

/* footer */
footer {
    width : 100%;
    background-color : #d30923;
    text-align: center;
    position: absolute;
    bottom: 0;
}
footer::after { 
    content: ''; 
    display: table; 
    clear: both;
}
footer .ft-sns-wrap ul {
    display: inline-block;
}
footer .ft-sns-wrap ul li {
    float : left;
    margin : 17px 12px 0 12px;
}
footer .ft-sns-wrap img {
    width : 28px;
    height : 28px;
}
.copy-wrap p:nth-child(2) a {
    font-size : 10px;
    color : #fff;
    margin-bottom : 5px;
}
.copy-wrap span { 
    margin : 0 5px;
}
.copy-wrap p:nth-child(3) {
    font-size : 10px;
    line-height: 12px;
    color : #fff;
}
.copy-wrap img {
    width : 55px;
    height : 18px;
    margin : 8px 0;
}
#search {
	height: 30px;
    width: 40px;
    border: none;
    background-color: #de3230;
    color: #fff;
    border: 1px solid #de3230;
    margin-top: 1px;
    font-size : 12px;
}
#inputtext {
	height: 30px;
    border: none;
    padding: 0 3%;
    border: 1px solid #de3230;
    font-size : 12px;
}
#select {
	width : 246px;
}
#select select {
	height: 30px;
	font-size : 12px;
	border: 1px solid #de3230
}
/* footer end*/

/* sub1  */

@media screen and (min-width : 768px) {
/* menu bar */
.nav-bar {
  position : fixed;
  top : 0;
  left : 0;
  width : 100%;
  height : 100px;
  background-color : rgba(222,50,48,0.9);
  z-index: 98;
}
h2 {
	font-size : 18px;
	margin : 20px 0 20px 0;
}
 .table td, .table th {
	padding : 0.7rem;
}
.table {
	font-size : 14px;
	text-align: center;
}
.btn.btn-secondary.check {
	font-size : 14px;
}
.btn.btn-secondary {
	font-size : 14px;
} 
#select {
	float : right;
}
 .badge-success {
	 width : 70px;
	 height : 30px;
	 line-height: 25px;
	 	
}


	 button.btn.btn-secondary.check{
	 	background-color : #fff;
	 	color : #000;
	 	border: none;
	 }
	 button.btn.btn-secondary {
		background-color : #de3230;
		color : #fff;
	}
	.btn.btn-secondary {
		background-color : #fff;
	 	color : #000;
	 	border: none;
	}
	.btn.btn-secondary:hover, button.btn.btn-secondary.check:hover {
		background-color : #de3230;
		color : #fff;
	}
	  .btn-toolbar {
	  	display : block;
	  }
 #lsbtn {
    position: absolute;
    right : 65%;
    top: 28px;
    z-index: 99;
     width: 240px;
} 
.btn.btn-outline-danger {
    	font-size : 16px;
    	color : #fff;
    }   
.btn-outline-danger {
    width : 80px;
    height : 40px;
    padding : 0;
    border-color: #fff;
}
.btn-outline-danger a{
    font-size :16px;
    padding : 0;
    color: #fff;
    text-decoration: none;
}
.btn-outline-danger.write {
	font-size :16px;
	color : #d30923;
	border : 1px solid #d30923;
	line-height: 40px;
}
.container {
	margin : 100px auto;
}
.pc-button {
        display : none;
    }
.button-wrap {
    cursor: pointer;
    position: absolute;
    float: right;
    width : 50px;
    top : 30px;
    height : 50px;
    right : 50px;
    transform: rotate(0deg);
 }
.button-wrap span {
    width :100%; height : 4px; background : #fff;
    position: absolute;
    opacity: 1;
    left: 0;
    transform: rotate(0deg);
    display: block;
}
.button-wrap span:nth-child(1) {
     position: absolute;
     top : 0px;
 }
.button-wrap span:nth-child(2) {
    position: absolute;
    top : 18px;
}
.button-wrap span:nth-child(3) {
    position: absolute;
    top : 36px;
}

.button-wrap.on {
     cursor: pointer;
    top : 30px;
    right : 50px;
    width : 50px;
    height : 50px;
    transition: transform 0.3s;
}
.button-wrap.on span:nth-child(1) {
top : 18px;
  transform: rotate(135deg);
    }
.button-wrap.on span:nth-child(2) {
   display: none;
}
.button-wrap.on span:nth-child(3) {
 top : 18px; 
 transform: rotate(-135deg);
}

/* menu bar end */
/* menu */

.menu {
    position : absolute;;
    top : 100px;
    width : 100%;
    height : 350px;
    z-index: 1;
}
.menu li {
    text-align: center;
    font-size : 15px;
    font-family: 'Lato', sans-serif;
}
.menu li a {
   text-decoration: none;
   background-color: rgba(225,50,48,0.9);
   display: block;
   color: #fff;
    width : 100%;
    height : 70px;
    line-height: 70px;
}
.menu li:hover a {
   background-color: rgba(145,1,20,0.9);
}
/* menu end */

/* header end */
.sns-wrap {
    display : none;
}
    
 .contents {
        height : 84.5%;
        width : 100%;
    }
/* footer */
footer {
    width : 100%;
    background-color : #d30923;
    text-align: center;
    position: absolute;
    bottom: 0;
    
}
footer::after { 
    content: ''; 
    display: table; 
    clear: both;
}
footer .ft-sns-wrap ul {
    display: inline-block;
}
footer .ft-sns-wrap ul li {
    float : left;
    margin : 24px 12px 11px 12px;
}
footer .ft-sns-wrap img {
    width : 28px;
    height : 28px;
}
.copy-wrap p:nth-child(2) {
        
 }
.copy-wrap p:nth-child(2) a {
    font-size : 20px;
    color : #fff;
}
.copy-wrap span { 
    margin : 0 7px;
}
.copy-wrap p:nth-child(3) {
    margin-top : 16px;
    font-size : 15px;
    color : #fff;
}
.copy-wrap p:nth-child(3) br {
        display: none;
    }
.copy-wrap img {
    width : 110px;
    height : 36px;
    margin : 14px 0;
}
#search {
	height: 30px;
    width: 40px;
    border: none;
    background-color: #de3230;
    color: #fff;
    border: 1px solid #de3230;
    margin-top: 1px;
    font-size : 14px;
}
#inputtext {
	height: 30px;
    border: none;
    padding: 0 3%;
    border: 1px solid #de3230;
    font-size : 14px;
}
#select {
	width : 274px;
}
#select select {
	height: 30px;
	font-size : 14px;
}
    /* sub1  */

    /* sub1  end */
}


/* footer  end */

@media screen and (min-width : 1280px) {
    @keyframes slideUp {
    0% {transform: translateY(100px); opacity: 0;}
}
    @keyframes slideUp-late {
    0% {transform: translateY(100px); opacity: 0;}
}
@keyframes slideRight {
    0% {transform: translateX(200px); opacity: 0;}
}
@keyframes slideLeft {
    0% {transform: translateX(-200px); opacity: 0;}
}
@keyframes fadeIn {
    0% {opacity: 0;}
    100% {opacity: 1;}
    }
@keyframes ani1 {
  0% {
      opacity : 0;
       -webkit-transform: translateX(300px);
      -ms-transform: translateX(300px);
      transform: translateX(300px);
    }
  100% {opacity : 1;}
}
.slideUp {animation: slideUp 1.5s;}
.slideUp-late {animation: slideUp-late 2s ;}
.slideRight {animation: slideRight 2s;}
.slideLeft {animation: slideLeft 2s;}
.fadeIn {animation: fadeIn 2s;}

    
    .blind1 {
        display: block;
    }
/* menu bar */
.nav-bar {
  position : fixed;
  top : 0;
  left : 0;
  height : 0;
  z-index: 99;
}
#lsbtn {
    position: absolute;
    right: 50px;
    top: 50px;
}
.btn:hover a{
        color: #fff;
    }
    .btn.btn-outline-danger {
    	font-size : 16px;
    	color : #de3230;
    }
.btn-outline-danger {
    width : 80px;
    height : 40px;
    padding : 0;
    border-color: #de3230;
}
.btn-outline-danger:hover a {
	color : fff;
}
.btn-outline-danger a{
	width : 80px;
	height : 40px;
    font-size :16px;
    color : #de3230;
}
.button-wrap {
    display: none;
 }
.pc-button {
    cursor: pointer;
    position: absolute;
    width : 50px;
    height : 50px;
    top: 71px;
    right : -100px;
    transform: rotate(0deg);
    display: block;
 }

.pc-button span {
    width :100%; height : 4px; background : #de3230;
    position: absolute;
    opacity: 1;
    left: 0;
    transform: rotate(0deg);
    transition: .25s ease-in-out;
    display: block;
}
 .pc-button span:nth-child(1) {
     position: absolute;
     top : 0px;
 }
.pc-button span:nth-child(2) {
    position: absolute;
    top : 18px;
}
.pc-button span:nth-child(3) {
    position: absolute;
    top : 36px;
}
.button-wrap-on {
     display: none;
 }
.pc-button.on {
   cursor: pointer;
    top :71px;
    right : -100px;
    width : 50px;
    height : 50px;
}
.pc-button.on span:nth-child(1) {
top : 18px;
  transform: rotate(135deg);
    }
 .pc-button.on span:nth-child(2) {
   display: none;
}
 .pc-button.on span:nth-child(3) {
 top : 18px; 
 transform: rotate(-135deg);
}
.pc-button.red span {
     background-color : #de3230;
 }
    

/* menu bar end */
/* menu */
.menu {
    position : absolute;
    top : 0;
    width : 400px;
    height : 100vh;
    left: -400px;
    display: inline-block;
    transition: left .3s ease-out;
}
.menu.visible {
  left: 0;
  transition: left .3s ease-out;
}
.menu li {
    text-align: center;
    font-size : 24px;
    font-family: 'Lato', sans-serif;
    height : 25vh;
}
.menu li a {
   text-decoration: none;
   background-color: rgba(225,50,48,0.9);
   display: block;
   color: #fff;
   height : 100%;
    line-height : 25vh;
}
.menu li:hover a {
   background-color: rgba(145,1,20,0.9);
} 
/* menu end */

/* header end */
.sns-wrap {
    display : block;
    position : fixed;
    bottom : 40px;
    right : 36px;
    z-index: 99;
}
.sns-wrap ul li img {
    margin-top : 20px;
    width : 40px; height : 40px;
 }
.sns-wrap ul li:nth-child(1) a {
    width : 40px; height : 40px;
    display: block;
    background : url(../../resources/image/facebook.png) no-repeat;
  }
.sns-wrap ul li:nth-child(1) a:hover {
    display: block;
    background : url(../../resources/image/facebook-on.png) no-repeat;
    background-size : 40px 40px;
  }
.sns-wrap ul li:nth-child(2) a {
    width : 40px; height : 40px;
    display: block;
    background : url(../../resources/image/instagram.png) no-repeat;
    margin-top : 20px;
  }
.sns-wrap ul li:nth-child(2) a:hover {
    display: block;
    background : url(../../resources/image/instagram-on.png) no-repeat;
    background-size : 40px 40px;
  }
  .sns-wrap ul li:nth-child(3) a {
    width : 40px; height : 40px;
    display: block;
    background : url(../../resources/image/twiter.png) no-repeat;
    margin-top : 20px;
  }
.sns-wrap ul li:nth-child(3) a:hover {
    display: block;
    background : url(../../resources/image/twiter-on.png) no-repeat;
    background-size : 40px 40px;
  }
  .sns-wrap ul li:nth-child(4) a {
    width : 40px; height : 40px;
    display: block;
    background : url(../../resources/image/youtube.png) no-repeat;
    margin-top : 20px;
  }
.sns-wrap ul li:nth-child(4) a:hover {
    display: block;
    background : url(../../resources/image/youtube-on.png) no-repeat;
    background-size : 40px 40px;
  }
    .contents {
        height : 87.6%;
        width : 100%;
    }
/* footer */
footer {
    width : 100%;
    height : 120px;
    background-color : #d30923;
    text-align: inherit;
    position: absolute;
    bottom: 0;
    padding : 30px 0;
}
footer .ft-sns-wrap ul {
    display: none;
}
.copy-wrap p:nth-child(2) {
    text-align: center;
 }
.copy-wrap p:nth-child(2) a {
    font-size : 20px;
    color : #fff;
}
.copy-wrap span { 
    margin : 0 7px;
}
.copy-wrap p:nth-child(3) {
    margin-top : 16px;
    font-size : 15px;
    color : #fff;
    text-align: center;
}
.copy-wrap p:nth-child(3) br {
        display: none;
    }
.copy-wrap img {
    width : 150px;
    height : 50px;
    position: absolute;
    top : 0;
    left : 20%;
    margin : 0;
    margin-top : 35px;
}
	#contents {
		padding-top : 15%;
	}
	#content {
		margin : 0 auto;
		width : 70%;
		text-align: center;
		background-color : red;
	}
.btn.btn-outline-danger:hover {
	color : #fff;
}

h2 {
	font-size : 20px;
	margin : 0 0 20px 0;
}
 .table td, .table th {
	padding : 0.8rem;
}
.table {
	font-size : 16px;
}
.btn.btn-secondary.check {
	font-size : 16px;
}
.btn.btn-secondary {
	font-size : 16px;
} 
table {
		text-align: center;
	}
	.thead {
		background-color : #de3230;
		color : #fff;
	}
	.btttd {
		text-align: left;
	}
	.navbar-light .navbar-brand {
		font-size : 30px;
		font-weight : bold;
		color : #de3230;
		margin-left : 50px;
	}
	.navbar-light .navbar-brand:hover {
		color : #de3230;
	}
	.navbar-nav {
		background-color : #f8f9fa!important;
		text-align: center;
	}
	.nav-link:hover {
		border-top : 3px solid #de3230;;
	}
	a {
		text-decoration: none;
		color : red;
	 }
	#title {
		color : #000;
	 }
	#write  {
		font-size : 16px;
	 }
	 .arrow {
	 	color : #000;
	 }
	 .no {
	 	font-size : 20px;
	 }
	 .badge-success {
	 	width : 70px;
	 	height : 30px;
	 	line-height: 25px;
	 	
	 }
	 button.btn.btn-secondary.check{
	 	background-color : #fff;
	 	color : #000;
	 	border: none;
	 }
	 button.btn.btn-secondary {
		background-color : #de3230;
		color : #fff;
	}
	.btn.btn-secondary {
		background-color : #fff;
	 	color : #000;
	 	border: none;
	}
	.btn.btn-secondary:hover, button.btn.btn-secondary.check:hover {
		background-color : #de3230;
		color : #fff;
	}
	  .btn-toolbar {
	  	display : block;
	  }
	nav {
		height : 60px;
		margin-bottom : 50px;
	}
	#btn {
		position : absolute;
		right : 50px;
	}
	#select {
		float : right;
	}
	#search {
	height: 30px;
    width: 40px;
    border: none;
    background-color: #de3230;
    color: #fff;
    border: 1px solid #de3230;
    margin-top: 1px;
    font-size : 14px;
}
#inputtext {
	height: 30px;
    border: none;
    padding: 0 3%;
    border: 1px solid #de3230;
    font-size : 14px;
}
#select {
	width : 274px;
}
#select select {
	height: 30px;
	font-size : 14px;
	border: 1px solid #de3230
}
    </style>
</head>
<body>

<% String bNum = request.getParameter("bNum");
String naver = (String)session.getAttribute("naver");
String selectNum = (String)session.getAttribute("selectNum");
String bNum1 = (String)session.getAttribute("bNum");
String face = (String)session.getAttribute("face");
String kakao = (String)session.getAttribute("kakao");
String google = (String)session.getAttribute("google");
String db = (String)session.getAttribute("db");
String num = (String)session.getAttribute("num");

String kname = (String)session.getAttribute("kname");
String name = (String)session.getAttribute("name");
String nname = (String)session.getAttribute("nname");
String fname = (String)session.getAttribute("fname");
String gname = (String)session.getAttribute("gname");
String bDate = (String)pageContext.getAttribute("date");

%>

     <div id="wrap">
       <!-- header -->
        <header>
          <div class="nav-wrap">
            <div class="nav-bar">
            <div id="lsbtn">
            	 <a id="gnbLogin" href="#" class="btn btn-outline-danger" style="display:none;">로그인</a>
             	<form id="form" action="logout" method="post">
             		<input class="btn btn-outline-danger" type="submit" value="로그아웃">
					<button class="btn btn-outline-danger" type="button" onclick="javascript:window.location='member'">멤버 관리</button>
				</form>
         </div>

            <!-- 메뉴 -->
              <button type="button" class="button-wrap">
                <span></span>
                <span></span>
                <span></span>
              </button>
            <div class="nav">
            <h2 class="blind">메뉴</h2>
                <div class="menu">
                    <ul>
                        <li><a href="list?bNum=0&num=4">공지사항</a></li>
                        <li><a href="list?bNum=1">자유게시판</a></li>
                        <li><a href="list?bNum=2">자료실</a></li>
                        <li><a href="chatlogin">채팅방</a></li>
                    </ul>
                    <button type="button" class="pc-button">
                        <span></span>
                        <span></span>
                        <span></span>
                    </button>
                </div>
            </div>
          </div>
          <div class="sns-wrap">
            <ul>
              <li><a href="#"></a></li>
              <li><a href="#"></a></li>
              <li><a href="#"></a></li>
              <li><a href="#"></a></li>
            </ul>
          </div>
            </div>
        </header>

        <!-- header end -->
        <div class="contents">
        	<div id="wrap1">
           		<div class="container">
           			<div id="title">
						<h2>멤버 관리</h2>
					</div>
           			<table class="table">
           				<thead class="thead">
							<tr>		
								<th scope="col"></th>
            					<th scope="col">아이디</th>
             					<th scope="col">이름</th>
              					<th scope="col">이메일</th>
              					<th scope="col">가입일</th>
              					<th scope="col">등급</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${memberlist }" var="dto">
								<tr>
									<td><input name="check" type="checkbox" onclick="doOpenCheck(this)"></td>
									<td>${dto.id}</td>
									<td>${dto.name}</td>
									<td>${dto.eMail}</td>
									<td>${dto.date}</td>
									<td>${dto.rating}</td>
								</tr>
							</c:forEach>
							<tr>
			<td colspan="6">
			<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
			<div class="btn-group mr-2" role="group" aria-label="First group">
				<!-- 처음 -->
				<c:choose>
					<c:when test="${(page.curPage - 1) < 1}">
						<button type="button" class="btn btn-secondary check">처음</button>
					</c:when>
					<c:otherwise>
						<a class="btn btn-secondary" href="member?page=1">처음</a>
					</c:otherwise>
				</c:choose>
				<!-- 이전 -->
				<c:choose>
					<c:when test="${(page.curPage - 1) < 1}">
					<button type="button" class="btn btn-secondary check">&lt; 이전</button>
					</c:when>
					<c:otherwise>
						<a class="btn btn-secondary" href="member?page=${page.curPage - 1}">&lt; 이전</a>
					</c:otherwise>
				</c:choose>
				</div>
				<!-- 개별 페이지 -->
				<div class="btn-group mr-2" role="group" aria-label="Second group">
				<c:forEach var="fEach" begin="${page.startPage }" end="${page.endPage }" step="1">
					<c:choose>
						<c:when test="${page.curPage == fEach }">
							<button type="button" class="btn btn-secondary">${fEach}</button>
						</c:when>
						
						<c:otherwise>
							<a class="btn btn-secondary" href="member?page=${fEach }">${fEach }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</div>
				
				<!-- 다음 -->
				  <div class="btn-group" role="group" aria-label="Third group">
				<c:choose>
					<c:when test="${(page.curPage + 1) > page.totalPage }">
						<button type="button" class="btn btn-secondary check">다음 &gt;</button>
					</c:when>
					<c:otherwise>
						<a class="btn btn-secondary" href="member?page=${page.curPage + 1}">다음 &gt;</a>
					</c:otherwise>
				</c:choose>
				
				<!-- 끝 -->
				<c:choose>
					<c:when test="${page.curPage == page.totalPage }">
						<button type="button" class="btn btn-secondary check">끝</button>
					</c:when>
					<c:otherwise>
						<a class="btn btn-secondary" href="member?page=${page.totalPage }">끝</a>
					</c:otherwise>
				</c:choose>
				</div>
				</div>
			</td>
		</tr>
							<tr>
								<td colspan="6">
									<input class="bbbtn" id="in" type="button" value="등업" >
									<input class="bbbtn" id="out" type="button" value="탈퇴" >
								</td>
							</tr>
						</tbody>
           			</table>
           		</div>
           	</div>
        </div>
        <!-- footer -->
        <footer>
          <div class="ft-sns-wrap">
              <ul class="clear">
                <li><a href="#"><img src="resources/image/facebook-on.png" alt="페이스북"></a></li>
                <li><a href="#"><img src="resources/image/instagram-on.png" alt="인스타그램"></a></li>
                <li><a href="#"><img src="resources/image/twiter-on.png" alt="트위터"></a></li>
                <li><a href="#"><img src="resources/image/youtube-on.png" alt="유튜브"></a></li>
              </ul>
            </div>
            <div class="copy-wrap">
             <h3 class="blind">회사정보</h3>
              <p><a href="#">이용약관<span>l</span>법적고지<span>l</span>개인정보처리방침</a></p>
              <p>Copyrightⓒ 2018 K.SW KOREA COMPANY.<br>ALL RIGHTS RESERVED</p>
            </div>
        </footer>
        </div>
        <div class="col-lg-12" id="ex3_Result1" ></div> 
		<div class="col-lg-12" id="ex3_Result2" ></div> 
        <!-- footer end -->
    <!-- home end -->
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="resources/js/script.js"></script> 
    <script src="resources/js/login.js"></script>
    <script>  
    function doOpenCheck(chk){
        var obj = document.getElementsByName("check");
        for(var i=0; i<obj.length; i++){
            if(obj[i] != chk){
                obj[i].checked = false;
            }
        }
    }

    $("#out").click(function(){ 
		
		var rowData = new Array();
		var tdArr = new Array();
		var checkbox = $("input[name=check]:checked");
		
		// 체크된 체크박스 값을 가져온다
		checkbox.each(function(i) {

			// checkbox.parent() : checkbox의 부모는 <td>이다.
			// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
			var tr = checkbox.parent().parent().eq(i);
			var td = tr.children();
			
			// 체크된 row의 모든 값을 배열에 담는다.
			rowData.push(tr.text());
			
			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var id = td.eq(1).text()
			var name = td.eq(2).text()+", ";
			var eMail = td.eq(3).text()+", ";
			var date = td.eq(4).text()+", ";
			var rating = td.eq(5).text();
			
			// 가져온 값을 배열에 담는다.
			tdArr.push(id);
			tdArr.push(name);
			tdArr.push(eMail);
			tdArr.push(date);
			tdArr.push(rating);
			
			console.log("no : " + id);
			console.log("no : " + rating);
			//console.log("userid : " + userid);
			//console.log("name : " + name);
			//console.log("email : " + email);
			var allData = {"id": id};
			
	        $.ajax({
	            // url : "/jsp19/lginOK.jsp",
	            url : "deleteOk",
	            type : "post",
	            dataType : "text",
	            data : allData,
	    		success : function(json) {
	    			//console.log(json);
	    			var result = JSON.parse(json);
	    			console.log(result);
	    			if(result.code == "success") {
	                   alert(result.desc)
	                   location.reload();
	               } else {
	                   alert(result.desc)
	               }
	            }
	    	}); 
		});	
	});
    
 $("#in").click(function(){ 
		
		var rowData = new Array();
		var tdArr = new Array();
		var checkbox = $("input[name=check]:checked");
		
		// 체크된 체크박스 값을 가져온다
		checkbox.each(function(i) {

			// checkbox.parent() : checkbox의 부모는 <td>이다.
			// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
			var tr = checkbox.parent().parent().eq(i);
			var td = tr.children();
			
			// 체크된 row의 모든 값을 배열에 담는다.
			rowData.push(tr.text());
			
			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var id = td.eq(1).text()
			var name = td.eq(2).text()+", ";
			var eMail = td.eq(3).text()+", ";
			var date = td.eq(4).text()+", ";
			var rating = td.eq(5).text();
			
			// 가져온 값을 배열에 담는다.
			tdArr.push(id);
			tdArr.push(rating);
			
			console.log("no : " + rating);
			//console.log("userid : " + userid);
			//console.log("name : " + name);
			//console.log("email : " + email);
			var allData = {"id": id, "rating" : rating};
			
			$.ajax({
	            // url : "/jsp19/lginOK.jsp",
	            url : "ratingmodifyOk",
	            type : "post",
	            dataType : "text",
	            data : allData,
	    		success : function(json) {
	    			//console.log(json);
	    			var result = JSON.parse(json);
	    			console.log(result);
	    			if(result.code == "success") {
	                   alert(result.desc)
	                   location.reload();
	               } else {
	                   alert(result.desc)
	               }
	            }
	    	});
		});	
	});

    	
    
    	$(document).ready(function() {	
    		$("#serchcheck").focus(function() {
    			if($("#serchcheck option:selected").val() == "0") {
					$("#bbcontent").css("display", "none");
					$("#bbname").css("display", "none");
					$("#select").css("display", "block");
				} else if($("#serchcheck option:selected").val() == "1") {
					$("#bbcontent").css("display", "block");
					$("#bbname").css("display", "none");
					$("#select").css("display", "none");
				} else if($("#serchcheck option:selected").val() == "2") {
					$("#bbcontent").css("display", "none");
					$("#bbname").css("display", "block");
					$("#select").css("display", "none");
				}
    		});
    	});
    	
    </script>
	<script>	
		var naverLogin = new naver.LoginWithNaverId(
			{

				clientId: "IEzLjz3gsY3kDLocAh4B",
				callbackUrl: "http://localhost:8081/project/login.jsp",
				isPopup: false,
			}
		);
		/* (4) 네아로 로그인 정보를 초기화하기 위하여 init을 호출 */
		naverLogin.init();
		
		/* (4-1) 임의의 링크를 설정해줄 필요가 있는 경우 */
		$("#gnbLogin").attr("href", naverLogin.generateAuthorizeUrl());

		/* (5) 현재 로그인 상태를 확인 */
		window.addEventListener('load', function () {
			naverLogin.getLoginStatus(function (status) {
				if (status) {
					/* (6) 로그인 상태가 "true" 인 경우 로그인 버튼을 없애고
					   사용자 정보를 출력합니다. */
					setLoginStatus();
				} else {
				}
			});
		});

		/* (6) 로그인 상태가 "true" 인 경우 로그인 버튼을 없애고
		   사용자 정보를 출력합니다. */
		function setLoginStatus() {
			console.log(naverLogin.user);
			var uid = naverLogin.user.getId();
			var profileImage = naverLogin.user.getProfileImage();
			var uName = naverLogin.user.getName();
			var nickName = naverLogin.user.getNickName();
			var eMail = naverLogin.user.getEmail();
			
			var allData = { "nname": uName, "num": 0};

		      $.ajax({
			        url : "/project/param.jsp",
			        type : "post",
			        dataType : "text",
			        data : allData,
					success : function(data) {
						$("#idwrap").css("display", "none");
						$("#pwwrap").css("display", "none");
						$("#join").css("display", "none");
						$("#login").css("display", "none");
						$("#kklogin").css("display", "none");
						$("#form").css("display", "none");
						$("#naverIdLogin_loginButton").html(
								'<br><br><img src="' + profileImage + 
								'" height=50 /> <p>' + uName + '님 반갑습니다.</p>');
						$("#gnbLogin").html("로그아웃");
						$("#gnbLogin").css("display", "inline-block");
						$("#gnbLogin").css("line-height", "40px");
						$("#gnbLogin").attr("href", "#");
						/* (7) 로그아웃 버튼을 설정하고 동작을 정의합니다. */
						$("#gnbLogin").click(function () {
							naverLogin.logout();
							location.href="login.jsp";
						});
					}
		      });
		}
		history.pushState(null, null, location.href);
	    window.onpopstate = function () {
	        history.go(1);
	};
	
	</script>
</body>
</html>