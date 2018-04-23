<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang = "ja">
<head>
<link rel = "stylesheet" href = "css/bootstrap.min.css">
<link rel = "stylesheet" href = "css/new_user.css">
<meta charset="UTF-8">
<title>新規登録</title>
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
		<h1>ユーザ新規登録</h1>

		<c:if test="${errMsg != null}" >
        <div class="alert alert-danger" role="alert" style ="text-align:center; color:red;">
          ${errMsg}
        </div>
    </c:if>

		<form action="NewUserServlet" method="post">
			<p style="text-align:center;"><span>ログインID</span><input type="text" value="${loginId}" name = "loginId" style="margin-left:100px; width:185px;"></p>
			<p style="text-align:center;"><span>パスワード</span><input type="password" name = "password" style="margin-left:100px; width:185px;"></p>
			<p style="text-align:center;"><span>パスワード(確認)</span><input type="password" name = "passwordConfirm" style="margin-left:60px; width:185px;"></p>
			<p style="text-align:center;"><span>ユーザ名</span><input type="text" value="${name}" name="name" style="margin-left:120px; width:185px;"></p>
			<p style="text-align:center;"><span>生年月日</span><input type="date" value="${birthDate}" name="birthDate" style="margin-left:120px; width:185px;"></p>
			<input type = "submit" value = "登録" style="margin-left:210px;">
		</form>
		<p style="margin-top:80px;">
		<A HREF="UserListServlet">戻る</a>
		</p>
	</div>
</body>
</html>

