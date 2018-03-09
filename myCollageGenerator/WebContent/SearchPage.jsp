<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
/* Constants */

String BackgroundColor = "lightgrey";

String SearchBoxText = "Enter topic";
String SearchBoxColor = "white";
String SearchBoxOutlineColor = "darkgray";
String SearchBoxTextColor = "lightgray";

String SearchButtonText = "Build Collage";
String SearchButtonColor = "darkgray";
String SearchButtonTextColor = "white";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Search Page</title>
		<style>
			body 
			{
				background: <%= BackgroundColor %>;
			}
			#SearchButton 
			{
				color: <%= SearchButtonTextColor %>;
				background: <%= SearchButtonColor %>;
				height: 72px;
				width: 235px;
				font-size: 26px;
			}
			
			#SearchBox
			{
				color: <%= SearchBoxTextColor %>;  /*This is for placeholder text, not real text. Email prof for clarification? Right now its all light. */ 
				border: 1px <%= SearchBoxOutlineColor %> solid;
				background: <%= SearchBoxColor %>;
				height: 72px;
				width: 391px;
				font-size: 26px;
				box-sizing: border-box;
				
			}
			#Spacer
			{
				width: 235px;
				height: 72px;
				visibility: none;
			}
			#SearchContainer 
			{
				display: flex;
				justify-content: center;
				align-items: center;
				flex-wrap: no-wrap;
				width: 100vw;
				height: 100vh;
			}
		</style>

	</head>
	<body>
		<form  name="input" method="GET" action="${pageContext.request.contextPath}/CollageGenerator">
			<div id="SearchContainer">
				<div id="Spacer"> </div>
				<input id="SearchBox" type="text" name="SearchBox" placeholder="<%= SearchBoxText %>" required />
				<input id="SearchButton" type="submit" value="<%= SearchButtonText %>"/>
			</div>
		</form>
		<script>
		function setCookie(key, value) {
			var expires = new Date();
			expires.setTime(expires.getTime() + (1 * 24 * 60 * 60 * 1000));
			document.cookie = key + '=' + value + ';expires=' + expires.toUTCString();
		}
		 document.getElementById("SearchButton").addEventListener("click", ()=>{
			 setCookie("initial",document.getElementById("SearchBox").value);
		 }); 		
		 </script>
	</body>
</html>
	
		