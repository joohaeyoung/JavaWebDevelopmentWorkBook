package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		RequestDispatcher rd = request.getRequestDispatcher(
				"/member/MemberForm.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("name"));

		try {
			ServletContext sc = this.getServletContext();
			Member member = new Member();
			member.setName(request.getParameter("name"))
			      .setEmail(request.getParameter("email"))
			      .setPassword(request.getParameter("password"));
			

			
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			memberDao.insert(member);
			response.sendRedirect("list");
			
		} catch (Exception e) {
		
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
			
		} 
	}
}
