package com.myweb2.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb2.user.service.DeleteServiceImpl;
import com.myweb2.user.service.GetInfoServiceImpl;
import com.myweb2.user.service.JoinServiceImpl;
import com.myweb2.user.service.LoginServiceImpl;
import com.myweb2.user.service.UpdateServiceImpl;
import com.myweb2.user.service.UserService;

@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UserController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
		
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String command = uri.substring(path.length());
		
		System.out.println(command);
		
		UserService service = null;
		
		if(command.equals("/user/join.user")) {
			request.getRequestDispatcher("user_join.jsp").forward(request, response);
		}else if(command.equals("/user/joinForm.user")) {
			
				service = new JoinServiceImpl();
				int result = service.execute(request, response);
				
				if(result ==1) {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('이미 존재하는 회원입니다')");
					out.println("location.href='user_join.jsp' ");
					out.println("</script>");
				}else {
					response.sendRedirect("user_login.jsp");
				}
			}else if(command.equals("/user/login.user")) {
				request.getRequestDispatcher("user_login.jsp").forward(request, response);
			}else if(command.equals("/user/loginForm.user")) {
				
				service = new LoginServiceImpl();
				int result = service.execute(request, response);
				
				if(result ==1) {
					String id = request.getParameter("id");
					HttpSession session = request.getSession();
					session.setAttribute("user_id", id);
					response.sendRedirect("user_mypage.jsp");
					
				}else {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out =  response.getWriter();
					out.println("<script>");
					out.println("alert('아이디 비밀번호를 확인하세요')");
					out.println("location.href='login.user' ");
					out.println("</script>");
				}
				
			
			}else if(command.equals("/user/mypage.user")) {
				request.getRequestDispatcher("user_mypage.jsp").forward(request, response);
			}else if(command.equals("/user/update.user")) {
				
				service = new GetInfoServiceImpl();
				service.execute(request, response);
				
				request.getRequestDispatcher("user_mypageinfo.jsp").forward(request, response);
			}else if(command.equals("/user/updateForm.user")) {
				service = new UpdateServiceImpl();
				int result = service.execute(request, response);
				
				if(result ==1) {
					response.sendRedirect("mypage.user");
				}else { 
					
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('회원정보 수정에 실패했습니다')");
					out.println("location.href='user_mypage.jsp' ");
					out.println("</script>");
				}
				
			}else if(command.equals("/user/delete.user")) {
				request.getRequestDispatcher("user_deleteForm.user").forward(request, response);
			}else if(command.equals("/user/deleteForm.user")) {
				
				service = new DeleteServiceImpl();
				int result = service.execute(request, response);
				
				if(result == 1) {
					response.sendRedirect("../main.do");
				}else {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('비밀번호를 확인하세요')");
					out.println("location.href='user_delete.jsp' ");
					out.println("</script>");
				}
			}else if(command.equals("/user/logout.user")) {
				
				HttpSession session = request.getSession();
				session.invalidate();
				response.sendRedirect("../main.do");
			}
		}
}
