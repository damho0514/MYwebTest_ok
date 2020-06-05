package com.myweb2.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb2.board.model.BoardDAO;
import com.myweb2.board.model.BoardVO;



public class ContentServiceImpl implements BoardService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		String bno = request.getParameter("bno");
				
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO vo = dao.getContent(bno);
//		System.out.println(vo.getBno());
		//화면에 글정보를 넘겨주기 위해서 request강제저장
		request.setAttribute("vo", vo);
	}

}
