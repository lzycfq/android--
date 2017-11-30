package cn.itcast.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
   /**
    * @see HttpServlet#HttpServlet()
    */
   public LoginServlet() {
       super();
       // TODO Auto-generated constructor stub
   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");//iso 8859 -1
		String password = request.getParameter("password");
		System.out.println("username:"+new String(username.getBytes("iso-8859-1"),"utf-8"));
		System.out.println("password:"+password);
		
		if("zhangsan".equals(username)&&"123".equals(password)){
			response.getOutputStream().write("µÇÂ¼³É¹¦".getBytes("utf-8"));
		}else{
			response.getOutputStream().write("µÇÂ¼Ê§°Ü".getBytes("utf-8"));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("post");
		doGet(req, resp);
	}	
}
