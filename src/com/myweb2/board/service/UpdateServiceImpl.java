package com.myweb2.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb2.board.model.BoardDAO;



public class UpdateServiceImpl implements BoardService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bno = request.getParameter("bno");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println(bno);
		System.out.println(content);
		BoardDAO dao = BoardDAO.getInstance();
		dao.update(bno, title, content);
	}

	
}
