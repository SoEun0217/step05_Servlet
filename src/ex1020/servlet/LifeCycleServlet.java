package ex1020.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *  Servlet작성
 *   1)반드시 public 클래스로 선언
 *   2) HttpServlet 상속
 *   3) 필요한 메소드 재정의
 *   
 *   
 *   4) 웹브라우져에서 동작(실행) 할 수 있도록 설정 필요하다.
 *      -web.xml에 설정 
 *      -@annotation 으로 설정
 * */
public class LifeCycleServlet extends HttpServlet {
	public LifeCycleServlet() {
		System.out.println("LifeCycleServlet의 생성자 호출...");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("LifeCycleServle의 init()호출됨");
	}
	
	/*
	 * @Override protected void service(HttpServletRequest arg0, HttpServletResponse
	 * arg1) throws ServletException, IOException {
	 * System.out.println("LifeCycleServlet의 service()호출됨"); }
	 */
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//문서의 첫줄에 현재 문서의 한글 인코딩 설정
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("LifeCycleServlet의 doGet()호출됨");//콘솔출력
		
		//브라우져에 출력을 하고싶다..
		//PrintWriter객체 만들기-중요!!
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Servlet연습</title></head>");
		out.println("<body>");
		out.println("<h1 style='color:red'>안녕 - Hello</h1>");//jsp파일의 contentType이 필요
		
		//request로 전달되는 데이터 받기
		//http://localhost:8000/step05_Servlet/life?id=hello&age=20 get방식으로데이터 넘겨보기
		String id=request.getParameter("id");
		String age=request.getParameter("age");
		out.println("id : "+id);
		out.println("age : "+age);
		out.println("<hr>");
		out.println("<a href='test.jsp'>이동하자</a>");//루트지정하지않는다.
		
		//session 필요-HttpSession - 여기서는 내장객체가 존재하지않으므로 따로 구해서 사용해야한다. 
		HttpSession session=request.getSession();
		session.setAttribute("message", "점심 뭐 먹지?");
		//application - ServletContext필요
		ServletContext application=request.getServletContext();
		application.setAttribute("addr", "서울시강동");
		
		//쿠키
		Cookie co=new Cookie("num","100");
		response.addCookie(co);//초기값은 세션이 만료될때까지만 유지되는 것이다.
		
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("LifeCycleServlet의 doPost()호출됨");
	}

	@Override
	public void destroy() {
		System.out.println("LifeCycleServlet의 destroy()호출됨");
	}

	
	

}
