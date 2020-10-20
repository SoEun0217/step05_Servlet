<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>text.jsp 문서입니다.</h1>
<h3>session에 저장된 message : <%=session.getAttribute("message") %> <br>
application 저장된 addr :  <%=application.getAttribute("addr") %>
</h3>
</body>
</html>