package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HttpSession session = req.getSession();
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		
		
		try {
			
			User loginUser = UserDao.authenticate(user);
			if(null !=loginUser && loginUser.getPassword().equals(password)) {
				session.setAttribute("user", loginUser);
				resp.sendRedirect("home.jsp");
			}else {
				out.print("Invalid/Empty credentials");
				resp.sendRedirect("login.jsp");
			}			
			
		} catch (Exception e) {
			System.out.println("Exception "+e);
			out.print("Invalid/Empty credentials");
			req.getRequestDispatcher("login.jsp").include(req, resp);
		}		
	}

}
