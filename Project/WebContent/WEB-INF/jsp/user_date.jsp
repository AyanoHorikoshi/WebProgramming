<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang = "ja">
<head>
<link rel = "stylesheet" href = "css/bootstrap.min.css">
<link rel = "stylesheet" href = "css/user_date.css">
<meta charset="UTF-8">
<title>情報詳細参照</title>
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

	<div class="container" style="width:600px; height:700px;">
		<h1>ユーザ情報詳細参照</h1>
			<p style="text-align:left;"><span style="padding-right:215px;">ログインID</span>${user.loginId}</p>
			<p style="text-align:left;"><span style="padding-right:230px;">ユーザ名</span>${user.name}</p>
			<p style="text-align:left;"><span style="padding-right:230px;">生年月日</span>${user.birthDate}</p>
			<p style="text-align:left;"><span style="padding-right:230px;">登録日時</span>${user.createDate}</p>
			<p style="text-align:left;"><span style="padding-right:230px;">更新日時</span>${user.updateDate}</p>
		<p style="margin-top:80px;">
		<u><A HREF="UserListServlet">戻る</a></u>
		</p>
	</div>
</body>
</html>
