<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
            .main{
                width: 300px;
                height: 200px;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                text-align: center;
                border: 2px solid;
            }
            form{
                width: 100%;
                position: relative;
                display: block;
                margin: 20px auto;
            }
            input{
                margin: 10px 0;
            }

        </style>
<body>
		
		
		<div class="main">
            <form method="POST" action="${pageContext.request.contextPath}/SellerController?action=uploade" enctype="multipart/form-data" >
                <input type="file" name="file" />
                <input type="submit" value="Upload"/>
            </form>
            <div class="downlaod">
                <span>1. File Name: Chart.png <a href="DownloadServlet?filename=29.jpg">Download</a></span>
            </div>
        </div>
		
</body>
</html>