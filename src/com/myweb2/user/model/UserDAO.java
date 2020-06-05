package com.myweb2.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.myweb2.util.JdbcUtil;

import javax.naming.InitialContext;


public class UserDAO {
	
	private static UserDAO instance = new UserDAO();
	

	private UserDAO() {
		
		try {
			
			InitialContext ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/oracle");
			
		} catch (Exception e) {
			System.out.println("클래스 로딩중 에러");
		}
		
	}

	public static UserDAO getInstance() {
		return instance;
	}

	
	private DataSource ds;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//아이디 중복검사
	public int checkId(String id) {
		
		int result = 0;
		
		String sql ="select * from users where id = ?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				result = 1;
			} else {
				result = 0;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return result;
	}
	
	//회원가입 처리
	public int insert(UserVO vo) {
		int result = 0;
		
		String sql = "insert into users(id, pw, name,phone1,phone2,phone3, email1,email2, address1,address2) values(?,?,?,?,?,?,?,?,?,?)";
		
		try {
			
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId() );
			pstmt.setString(2, vo.getPw() );
			pstmt.setString(3, vo.getName() );
			pstmt.setString(4, vo.getPhone1());
			pstmt.setString(5, vo.getPhone2());
			pstmt.setString(6, vo.getPhone3());
			pstmt.setString(7, vo.getEmail1() );
			pstmt.setString(8, vo.getEmail2() );
			pstmt.setString(9, vo.getAddress1() );
			pstmt.setString(10, vo.getAddress2() );
			
			result = pstmt.executeUpdate(); //성공시 1, 실패시 0반환
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
		
		return result;
	}
	
	
	//로그인 검증 메서드
	public int login(String id, String pw) {
		
		int result= 0;
		
		String sql = "select * from users where id = ? and pw = ?";
		
		try {
			
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				result = 1;
			} else { 
				result = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
			
		}
		return result;
	}
	

	public UserVO getInfo(String id) {
		
		UserVO vo = new UserVO();
		
		String sql = "select * from users where id = ?" ;
		
		try {
			
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
	
			if(rs.next()) {
				
				vo.setId( rs.getString("id") );
				vo.setPw(rs.getString("pw"));
				vo.setName( rs.getString("name") );
				vo.setPhone1(rs.getString("phone1"));
				vo.setPhone2(rs.getString("phone2"));
				vo.setPhone3(rs.getString("phone3"));
				vo.setEmail1( rs.getString("email1") );
				vo.setEmail2( rs.getString("email2") );
				vo.setAddress1( rs.getString("address1"));
				vo.setAddress2( rs.getString("address2"));
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	//회원정보 수정
	public int update(UserVO vo) {
		
		int result = 0;
		
		String sql = "update users set pw = ?, name = ?, email = ?, address = ? where id = ?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId() );
			pstmt.setString(2, vo.getPw() );
			pstmt.setString(3, vo.getName() );
			pstmt.setString(4, vo.getPhone1());
			pstmt.setString(5, vo.getPhone2());
			pstmt.setString(6, vo.getPhone3());
			pstmt.setString(7, vo.getEmail1() );
			pstmt.setString(8, vo.getEmail2() );
			pstmt.setString(9, vo.getAddress1() );
			pstmt.setString(10, vo.getAddress2() );
			
			result = pstmt.executeUpdate(); //성공시1 , 실패시 0
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		return result;
		
	}
	
	
	//회원탈퇴 메서드
	public int delete(String id ) {
		
		int result = 0;
		
		String sql = "delete from users where id = ?"; 
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
		
		return result;
	}
	

	
	
	
	
	
	
	
	
	
}
