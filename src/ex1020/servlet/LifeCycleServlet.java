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
 *  Servlet�ۼ�
 *   1)�ݵ�� public Ŭ������ ����
 *   2) HttpServlet ���
 *   3) �ʿ��� �޼ҵ� ������
 *   
 *   
 *   4) ������������ ����(����) �� �� �ֵ��� ���� �ʿ��ϴ�.
 *      -web.xml�� ���� 
 *      -@annotation ���� ����
 * */
public class LifeCycleServlet extends HttpServlet {
	public LifeCycleServlet() {
		System.out.println("LifeCycleServlet�� ������ ȣ��...");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("LifeCycleServle�� init()ȣ���");
	}
	
	/*
	 * @Override protected void service(HttpServletRequest arg0, HttpServletResponse
	 * arg1) throws ServletException, IOException {
	 * System.out.println("LifeCycleServlet�� service()ȣ���"); }
	 */
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������ ù�ٿ� ���� ������ �ѱ� ���ڵ� ����
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("LifeCycleServlet�� doGet()ȣ���");//�ܼ����
		
		//�������� ����� �ϰ�ʹ�..
		//PrintWriter��ü �����-�߿�!!
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Servlet����</title></head>");
		out.println("<body>");
		out.println("<h1 style='color:red'>�ȳ� - Hello</h1>");//jsp������ contentType�� �ʿ�
		
		//request�� ���޵Ǵ� ������ �ޱ�
		//http://localhost:8000/step05_Servlet/life?id=hello&age=20 get������ε����� �Ѱܺ���
		String id=request.getParameter("id");
		String age=request.getParameter("age");
		out.println("id : "+id);
		out.println("age : "+age);
		out.println("<hr>");
		out.println("<a href='test.jsp'>�̵�����</a>");//��Ʈ���������ʴ´�.
		
		//session �ʿ�-HttpSession - ���⼭�� ���尴ü�� �������������Ƿ� ���� ���ؼ� ����ؾ��Ѵ�. 
		HttpSession session=request.getSession();
		session.setAttribute("message", "���� �� ����?");
		//application - ServletContext�ʿ�
		ServletContext application=request.getServletContext();
		application.setAttribute("addr", "����ð���");
		
		//��Ű
		Cookie co=new Cookie("num","100");
		response.addCookie(co);//�ʱⰪ�� ������ ����ɶ������� �����Ǵ� ���̴�.
		
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("LifeCycleServlet�� doPost()ȣ���");
	}

	@Override
	public void destroy() {
		System.out.println("LifeCycleServlet�� destroy()ȣ���");
	}

	
	

}
