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

//����ϱ�..
public class LoginServlet extends HttpServlet {
	String dbId="soeun", dbPwd="1234";
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");//������ִ� �����͸� �ѱ����ڵ�
		request.setCharacterEncoding("UTF-8");//post��� ������ �� �־��ش�.
		//���۵Ǵ� ������ �ޱ�
		String userId=request.getParameter("userId");
		String userPwd=request.getParameter("userPwd");
		String userName=request.getParameter("userName");
		
		
		ServletContext application=request.getServletContext();
		//���������� �ϰ� ��ġ�ϸ� ���ǿ� ������ �����ϰ� LoginOk.jsp�̵�
		if(dbId.equals(userId)&&dbPwd.equals(userPwd)) {
			HttpSession session=request.getSession();
			session.setAttribute("sessionName", userName);
			session.setAttribute("creationTime", new Date().toLocaleString());
			
			//DB���� �����͸� ������ �������� �����ؾ��Ѵ�...
			List<String> hobbys= Arrays.asList("���","����","����");
			request.setAttribute("hobbys",hobbys);
			//forward ����� �̵�����!!!!
			request.getRequestDispatcher("session/LoginOk.jsp").forward(request, response);
			
			
			//�̵� : request,response�� ���Ӱ� �����켭���ڴ�.
			//response.sendRedirect("session/LoginOk.jsp");//���Ȯ��
		}else {
			PrintWriter out = response.getWriter();
		//Ʋ���� �޽��� ����ϰ� �ڷΰ��� �Ѵ�.
		out.println("<html>");
		out.println("<head><title>Servlet����</title></head>");
		out.println("<body>");
		out.println("<script>");
		out.println("alert('"+userName+"�� ������ Ȯ���ϼ���');");//�̶� print�� �ϸ� ���ٷ� �Ǽ� println�� ����.
		out.println("history.back();");
		out.println("</script>");
		out.println("</body>");
		out.println("</html>");
		}
		
	}
	
}
