package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

			conn = DriverManager.getConnection(
					sc.getInitParameter("url"), //JDBC URL( 드라이버 마다 형식이 다름 )
					sc.getInitParameter("username"),	// DBMS 사용자 아이디
					sc.getInitParameter("password"));	// DBMS 사용자 암호

			// 커넥션 객체로 부터  SQL을 던질 객체를 준비하자.
			stmt = conn.createStatement();

			//SQL을 던지는 객체를 사용하여 질의하자.
			rs = stmt.executeQuery(
					"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
							" FROM MEMBERS" +
					" ORDER BY MNO ASC");

			//서버에 가져온 데이터를 사용하여 HTML을 만들어서 웹 브라우저로 출력하자.
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원목록</title></head>");
			out.println("<body><h1>회원목록</h1>");

			out.println("<p><a href='add'>신규회원</a><p>");

			while(rs.next()) {

				out.println(
						rs.getInt("MNO") + "," +
								"<a href='update?no=" + rs.getInt("MNO")+"'>"+
								rs.getString("MNAME") +"</a>," +
								rs.getString("EMAIL") + "," + 
								rs.getDate("CRE_DATE") +
								"<a href='delete?no="+ rs.getInt("MNO")+ "'>삭제</a>"+"<br>"
						);
			}
			out.println("</body></html>");
		} catch (Exception e) {
			throw new ServletException(e);

		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		}

	}

}
