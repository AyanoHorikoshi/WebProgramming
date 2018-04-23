<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang = "ja">
<head>
<link rel = "stylesheet" href = "css/index.css">
<link href="css/original/common.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
<script src="js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ログイン画面</title>

</head>
<body>
<nav class="navbar navbar-inverse">
         <div class="container">
             <div class="navbar-header">
           <a class="navbar-brand" href="index.html"></a>
           </div>
           </div>
           </nav>
<h1>ログイン画面</h1>

<c:if test="${errMsg != null}" >
        <div class="alert alert-danger" role="alert">
          ${errMsg}
        </div>
    </c:if>

	<div class="card card-container">
		<p id="profile-name" class="profile-name-card"></p>
		<form class="form-signin" action="LoginServlet" method="post">
	         <span id="reauth-email" class="reauth-email"></span>
			<p><span style="padding-right:30px;">ログインID</span>
			<input type = "text"name ="loginId"id="inputLoginId" class="form-control" style="width:205px;"></p>
		<p><span style="padding-right:30px;">パスワード</span>
		<input type = "password" name="password"  id="inputPassword" class="form-control" style="width:205px;"></p>
	<br>
		<button class="btn btn-lg btn-primary btn-block btn-signin" type = "submit">ログイン</button>
		</form>
		</div>

	</body>
	</html>