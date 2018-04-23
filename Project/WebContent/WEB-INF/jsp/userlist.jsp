<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang = "ja">
<head>
<link rel = "stylesheet" href = "css/bootstrap.min.css">
<link rel = "stylesheet" href = "css/userlist.css">
<meta charset="UTF-8">
<title>ユーザ一覧</title>
</head>
<body>

<ul class="nav justify-content-end navbar-dark bg-dark">
  <li class="nav-item">
    <div class="nav-link nav-name">${userInfo.name}さん</div>
  </li>
  <li class="nav-item">
    <a class="nav-link navi-red" HREF="LogoutServlet" class="navbar-link logout-link">ログアウト</a>
  </li>

</ul>


<div class="container" style="position: relative;">

<h1>ユーザ一覧</h1>

<p style="text-align: right;">
<u><A HREF="NewUserServlet">新規登録</a></u>
</p>

<form action="UserListServlet" method="post" style="padding-bottom: 15px; margin-bottom: 30px; width: 60%; margin:30px auto;">
  <div class="form-group row" style="margin-bottom: 15px;">

  <label for="inputPassword" class="col-sm-2 col-form-label">ログインID</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="inputPassword" name="loginId">
    </div>
    </div>
    <div class="form-group row">
    <label for="inputPassword" class="col-sm-2 col-form-label">ユーザ名</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="inputPassword" name="name">
    </div>

  </div>

<div class="form-inline">
  <div class="form-group mb-2">

    <input type="text" readonly class="form-control-plaintext" id="staticEmail2" value="生年月日">
  </div>
  <div class="form-group mx-sm-3 mb-2">
    <label for="inputPassword2" class="sr-only">Password</label>
    <input type="date" class="form-control" id="inputPassword2" name="birthDate">　　～　　<input type="date" class="form-control" id="inputPassword2" name="birthDate2">
  </div>
  </div>
  <p style="text-align: right;"><input type = "submit" value = "検索" style="width: 100px; position: absolute; right: 16px; top: 280px;"></p>
  </form>




<div  style="border-top: 5px solid #c0c0c0; padding-top:30px;">
<table class="table table-bordered">
  <thead>
    <tr style="background-color: gray">
      <th scope="col">ログインID</th>
      <th scope="col">ユーザ名</th>
      <th scope="col">生年月日</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach var="user" items="${userList}" >
    <tr>
      <th scope="row">${user.loginId}</th>
      <td>${user.name}</td>
      <td>${user.birthDate}</td>
      <td style="width: 350px;">

      <A HREF="UserDetailServlet?id=${user.id}"><button type="button" class="btn btn-primary">詳細</button></a>
      <c:if test="${userInfo.name == '管理者' || userInfo.name == user.name}">
      <A HREF="UserUpdateServlet?id=${user.id}"><button type="button" class="btn btn-success">更新</button></a></c:if>
      <c:if test="${userInfo.name == '管理者'}">
      <A HREF="UserDeleteServlet?id=${user.id}"><button type="button" class="btn btn-danger">削除</button></a></c:if>
      </td>
    </tr>
    </c:forEach>
  </tbody>
</table>
</div>
</div>

</body>
</html>
