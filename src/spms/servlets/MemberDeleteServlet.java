package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
	
		
		Connection conn = null;
		PreparedStatement pstmt= null;
		
		try {
			ServletContext sc = this.getServletContext();
			
			//JDBC 드라이버 등록 및 데이터베이스 연결
			Class.forName(sc.getInitParameter("driver")); 
			conn = (Connection) sc.getAttribute("Conn");
			
			pstmt = conn.prepareStatement(
					"DELETE FROM MEMBERS WHERE MNO="+ request.getParameter("no") );
			
			pstmt.executeUpdate();
			
			
			
			response.sendRedirect("list");
			
			
		}catch(Exception e) {
			throw new ServletException(); 
		}finally {
			try{ if(pstmt!=null) pstmt.close();} catch(Exception e) {throw new ServletException();}
			
		}
	}
}
