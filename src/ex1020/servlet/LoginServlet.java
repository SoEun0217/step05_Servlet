package ex1020.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//등록하기..
public class LoginServlet extends HttpServlet {
	String dbId="soeun", dbPwd="1234";
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");//출력해주는 데이터를 한글인코딩
		request.setCharacterEncoding("UTF-8");//post방식 때문에 꼭 넣어준다.
		//전송되는 데이터 받기
		String userId=request.getParameter("userId");
		String userPwd=request.getParameter("userPwd");
		String userName=request.getParameter("userName");
		
		
		ServletContext application=request.getServletContext();
		//인증절차를 하고 일치하면 세션에 정보를 저장하고 LoginOk.jsp이동
		if(dbId.equals(userId)&&dbPwd.equals(userPwd)) {
			HttpSession session=request.getSession();
			session.setAttribute("sessionName", userName);
			session.setAttribute("creationTime", new Date().toLocaleString());
			
			//DB에서 데이터를 꺼내서 뷰쪽으로 전달해야한다...
			List<String> hobbys= Arrays.asList("등산","수영","낚시");
			request.setAttribute("hobbys",hobbys);
			//forward 방식을 이동하자!!!!
			request.getRequestDispatcher("session/LoginOk.jsp").forward(request, response);
			
			
			//이동 : request,response를 새롭게 생성헤서가겠다.
			//response.sendRedirect("session/LoginOk.jsp");//경로확인
		}else {
			PrintWriter out = response.getWriter();
		//틀리면 메시지 출력하고 뒤로가기 한다.
		out.println("<html>");
		out.println("<head><title>Servlet연습</title></head>");
		out.println("<body>");
		out.println("<script>");
		out.println("alert('"+userName+"님 정보를 확인하세요');");//이때 print로 하면 한줄로 되서 println이 낫다.
		out.println("history.back();");
		out.println("</script>");
		out.println("</body>");
		out.println("</html>");
		}
		
	}
	
}
