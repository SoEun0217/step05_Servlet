<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    //클라이언트쪽의 Cache기능 만료.
    response.setHeader("Cache-Control", "no-store");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=application.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$("[type=button]").click(function(){
		if(confirm("정말 로그아웃하시겠습니까?")){
			//세션의 정보를 지우기,같은페이지에서 로그아웃할 수없다.
			//동적코드이기 때문에 무조건 세션이 삭제가 된다.로그아웃페이지를 새로 만들어야함
			<%-- <%session.invalidate();%>--%>			
			//이동하기
			//location.href="../logout";
			location.href="<%=request.getContextPath()%>/logout";
			
		}
		
	});
		

})
</script>
</head>
<body>
<%
if(session.getAttribute("sessionName")==null){//인증여부 판단
	//새로고침해도 시간은 변하지 않음.처음 로그인한 시간만 나타내기때문에
	%>
	
	<script type="text/javascript">
	alert("인증하세요");
	location.href="LoginForm.html";
	</script>
	<% 
}else{
%>
<h3>
	[<%=session.getAttribute("sessionName")%>님 로그인중] <br>
	<br>
	<fieldset>
	  <legend>취미선택</legend>
	  <%
	  List<String> hobbys=(List<String>)request.getAttribute("hobbys");
	  for(String h:hobbys){
		  %>
		   <input type="checkbox" name="hobby" value="<%=h%>"><%=h %>
		  <%
	  }
	  %>
	  
	</fieldset>
	</h3>
	<button type="button">로그아웃</button>

<%
	
}


%>
</body>
</html>