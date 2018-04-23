<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang = "ja">
<head>
<link rel = "stylesheet" href = "css/bootstrap.min.css">
<link rel = "stylesheet" href = "css/user_update.css">
<meta charset="UTF-8">
<title>情報更新</title>
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
		<h1>ユーザ情報更新</h1>

		<c:if test="${errMsg != null}" >
        <div class="alert alert-danger" role="alert" style ="text-align:center; color:red;">
          ${errMsg}
        </div>
    </c:if>

		<form action="UserUpdateServlet?id=${user.id}" method="post">
			<input type="hidden" name="id" value="${user.id}">
			<input type="hidden" name="loginId" value="${user.loginId}">
			<p style="text-align:left;">ログインID<span style="padding-right:100px;"></span>${user.loginId}</p>
			<p style="text-align:left;"><span>パスワード</span><input type="password"name = "password" style="margin-left:100px; width:185px;"></p>
			<p style="text-align:left;"><span>パスワード(確認)</span><input type="password"name = "passwordConfirm" style="margin-left:60px; width:185px;"></p>
			<p style="text-align:left;"><span>ユーザ名</span><input type="text" name="name" style="margin-left:120px; width:185px;" value="${user.name}"></p>
			<p style="text-align:left;"><span>生年月日</span><input type="date" name="birthDate" style="margin-left:120px; width:185px;" value="${user.birthDateStr}"></p>
			<input type = "submit" value = "更新" style="margin-left:210px;">
		</form>
		<p style="margin-top:80px;">
		<u><A HREF="UserListServlet">戻る</a></u>
		</p>
	</div>
</body>
</html>
