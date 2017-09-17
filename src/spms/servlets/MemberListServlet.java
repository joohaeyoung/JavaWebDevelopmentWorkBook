package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			ServletContext sc = this.getServletContext();

			//사용할 JDBC 드라이버를 등록하자.
			Class.forName( sc.getInitParameter("driver"));

			//드라이버를 사용하여 MySql 서버와 연결하자.

			conn = (Connection) sc.getAttribute("Conn");
			// 커넥션 객체로 부터  SQL을 던질 객체를 준비하자.
			stmt = conn.createStatement();

			//SQL을 던지는 객체를 사용하여 질의하자.
			rs = stmt.executeQuery(
					"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
							" FROM MEMBERS" +
					" ORDER BY MNO ASC");

			response.setContentType("text/html; charset=UTF-8");
			
			ArrayList<Member> members = new ArrayList<Member>();			
			while(rs.next()) {
				members.add(new Member().setNo(rs.getInt("MNO"))
										.setName(rs.getString("MNAME"))
										.setEmail(rs.getString("EMAIL"))
										.setCreateDate(rs.getDate("CRE_DATE"))			
						);
			}
			// requset에 회원 목록 데이터를 보관한다
			request.setAttribute("members",members);
			
			//JSP로 출력을 위임한다.
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberList.jsp");
			rd.include(request, response);
			
		} catch (Exception e) {
			throw new ServletException(e);

		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			
		}

	}

}
