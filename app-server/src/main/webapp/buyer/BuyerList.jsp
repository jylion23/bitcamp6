<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>회원목록</title>
</head>
<body>
<h1>회원 목록(MVC + EL + JSTL)</h1>
<a href='form'>새회원</a><br>
<table border='1'>
<thead>
  <tr>
    <th>번호</th>
    <th>아이디</th>
    <th>이름</th>
    <th>닉네임</th>
    <th>이메일</th>
    <th>생일</th>
    <th>사진</th>
    <th>전화번호</th>
    <th>레벨</th>
    <th>상태</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${buyerList}" var="buyer">
<tr>
    <td>${buyer.member.number}</td>
 <td><a href='detail?no=${buyer.member.id}'>${buyer.member.id}</a></td> 
    <td>${buyer.member.name}</td> 
    <td>${buyer.member.nickname}</td> 
    <td>${buyer.member.email}</td> 
    <td>${buyer.member.birthday}</td> 
    <td>${buyer.member.photo}</td> 
    <td>${buyer.member.phoneNumber}</td> 
    <td>${buyer.member.level}</td> 
    <td>${buyer.member.active}</td> 
    <td>${buyer.member.registeredDate}</td>
</tr>
</c:forEach>

</tbody>
</table>
</body>
</html>








