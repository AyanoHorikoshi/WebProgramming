<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang = "ja">
<head>
<link rel = "stylesheet" href = "css/bootstrap.min.css">
<link rel = "stylesheet" href = "css/user_delete.css">
<meta charset="UTF-8">
<title>削除確認</title>
</head>
<body>
	<ul class="nav justify-content-end navbar-dark bg-dark">
	  <li class="nav-item">
	    <div class="nav-link nav-name">${userInfo.name}さん</div>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link navi-red" HREF="LogoutServlet">ログアウト</a>
	  </li>
	</ul>

	<div class="container" style="width:500px; height:700px;">
		<h1>ユーザ削除確認</h1>
			<p>ログインID：${user.loginId}</p>
			<p>を本当に削除してもよろしいでしょうか。</p>
	<div style="padding-top:100px; text-align:center;">

	<input type="hidden" name="id" value="${user.id}">


		<div style="position:absolute; top:334px; left:770px;">
		<A HREF="UserListServlet"><input type = "submit" style="width:130px;" value ="キャンセル"></A></div>
		<form action="UserDeleteServlet?id=${user.id}" method="post">
		<span style="padding-left:250px;"><input type = "submit" value = "OK" style="width:130px"></span>
		</form>
		</div>
	</div>
</body>
</html>

