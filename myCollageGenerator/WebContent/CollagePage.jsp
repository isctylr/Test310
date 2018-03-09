<%@ page import="java.util.*" %>
<%@ page import="com.BackEnd.*" %>
<%@ page import="javax.imageio.*" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.io.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
/* Constants */
String BackgroundColor = "white";
String TitleText = "Collage for topic ";
String TitleColor = "black";
String TitleSize = "24px";


HttpSession curr= request.getSession();
String query = (String)curr.getAttribute("query");
ArrayList<String> mylist= (ArrayList<String>) curr.getAttribute("queryList");
String base64bytes= (String)curr.getAttribute(query);

String gallery = "";
for(String item : mylist) {
	if(!item.equals(query)) {
		gallery += "<a href=\"CollageGenerator?SearchBox=" + item + "\">";
		gallery += "<div class=\"GalleryImage\">";
		gallery += "<img src=\"FileHost/" + item + "/output.png\">";
		gallery += "</div>";
		gallery += "</a>";
	}
}


String src= "data:image/png;base64,"+ base64bytes;



String InsufficientPhoto = "Insufficient number of images found";
String InsufficientPhotoSize = "18";

String SearchText = "Enter topic";
String SearchBoxColor = "white";
String SearchBoxTextColor = "lightgray"; // REPORT: Was not on class diagram (Also, same as search page, this is specifically for placeholder text?)
String SearchBoxOutlineColor = "darkgray";

String SearchButtonText = "Build Another Collage";
String SearchButtonColor = "darkgray";
String SearchButtonTextColor = "white";

String ExportButtonText = "Export Collage";
String ExportButtonTextColor = "white";
String ExportButtonColor = "darkgray";

int MinSizeW = 40;
int MaxSizeW = 70;
int MinSizeH = 35;
int MaxSizeH = 50;
int MinPixelH = 600;
int MinPixelW = 800;
float PhotoSize = 0.05f;
int RotMax = 45;
int RotMin = -45;

int MinImageCount = 30;
int PixelBorder = 3;


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Collage Page</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
		<style>
			body {
				background-color: <%=BackgroundColor %>;
			}
			img {
    				width: 100%;
    				height: 100%;
			}
			a {
				text-decoration: none;
				color: inherit;
			}
			#CollageContainer {
				display: flex;
				flex-direction: column;
				align-items:center;

			}
			#CollageImageContainer {
				min-width: 800px;
				min-height: 600px;
				width: 60vw;
				height: 45vh;
				padding-right: 20px;
				margin-left: 200px;
			}
			#GalleryContainer {
				display: flex;
				overflow-x: scroll;
				flex-wrap: no-wrap;
			}
			.GalleryImage {
				width: 250px;
				height: 150px;
				flex: 0 0 auto;
				padding-right: 10px;
			}
			#Title {
				color: <%=TitleColor %>;
				font-size: <%=TitleSize %>;
				width: 100%;
				display: flex;
				justify-content: center;
			}
			#ExportButton {
				width: 150px;
				height: 100px;
				color: <%=ExportButtonTextColor %>;
				background-color: <%=ExportButtonColor %>; 
				display: flex;
				justify-content: center;
				align-items: center;
			}
			#InputContainer {
				display: flex;
				padding: 10px;
			}
			#SearchInput {
				background-color: <%=SearchBoxColor %>;
				color: <%=SearchBoxTextColor %>;
				border: 1px <%=SearchBoxOutlineColor %> solid;
				height: 72px;
				width: 250px;
				box-sizing: border-box;
				
			}
			#SearchButton {
				color: <%=SearchButtonTextColor %>;
				background-color: <%=SearchButtonColor %>;
				height: 72px;
			}
			#CollageExportContainer {
				display: flex;
				align-items: center;
			}
		</style>
	</head>
	<body>
		<div id="Title">
			Collage for topic <%=query%>
		</div>
		<div id="CollageContainer">
			<div id="CollageExportContainer"> 
				<div id="CollageImageContainer">
					<img id="collageImage" src="<%="FileHost/"+ query+"/output.png"%>" />
				</div>
				<a id="download" href="<%="FileHost/"+ query+"/output.png"%>" download>
					<div id="ExportButton">
						 <%= ExportButtonText %>
					</div>
				</a> 
			</div>
			<div id="InputContainer">
				<form name="input"  method="GET" action="${pageContext.request.contextPath}/CollageGenerator">
					<input id="SearchInput" type="text" name="SearchBox" placeholder="<%= SearchText %>" required />
					<input id="SearchButton" type="submit" value="<%= SearchButtonText %>" />
				</form>
			</div>

		</div>

		<div id="GalleryContainer">
			<%=gallery%>
		</div>
	</body>
</html>