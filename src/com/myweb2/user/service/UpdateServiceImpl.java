package com.myweb2.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb2.user.model.UserDAO;
import com.myweb2.user.model.UserVO;

public class UpdateServiceImpl implements UserService{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		UserVO vo = new UserVO(id, pw, name, phone1, phone2, phone3,  email1,email2, address1, address2);
		
		UserDAO dao = UserDAO.getInstance();
		
		int result = dao.update(vo);
		return result;
	}

}
