package com.myweb2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb2.board.service.BoardService;
import com.myweb2.board.service.ContentServiceImpl;
import com.myweb2.board.service.DeleteServiceImpl;
import com.myweb2.board.service.GetListServiceImpl;
import com.myweb2.board.service.RegistServiceImpl;
import com.myweb2.board.service.UpdateServiceImpl;




@WebServlet("*.board")
public class BoardContorller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BoardContorller() {
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
		
		BoardService service = null;
		
		if(command.equals( "/board/bbs.board" )) {  
			
			service = new GetListServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("bbs.jsp").forward(request, response);
		
		} else if(command.equals("/board/write.board")) {
			
			request.getRequestDispatcher("bbs_write.jsp").forward(request, response);
		} else if(command.equals("/board/regist.board")) {
			
			service = new RegistServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("bbs.board");						
		
		} else if(command.equals("/board/content.board")) { 
			
			service = new ContentServiceImpl();
			service.execute(request, response);
			
		
			
			
			request.getRequestDispatcher("bbs_content.jsp").forward(request, response);
		} else if(command.equals("/board/modify.board")) { 
			
		
			service = new ContentServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("bbs_modify.jsp").forward(request, response);

		} else if(command.equals("/board/update.board")) {

			service = new UpdateServiceImpl();
			service.execute(request, response);
			
			System.out.println(request.getParameter("bno"));
			response.sendRedirect("content.board?bno=" + request.getParameter("bno"));
		
			
		} else if(command.equals("/board/delete.board")) { 
		
			service = new DeleteServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("bbs.board");
			
		} 
		
		
	}
	
	
}	
