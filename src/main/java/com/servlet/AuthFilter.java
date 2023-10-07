package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.model.User;

@WebFilter("/AuthFilter")
public class AuthFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		/*HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("firstName");
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(name);
		
		UserDao ud = new UserDao();
		
		try {
			
			String validate = ud.authenticate(user);
			
			if(validate.equals("Buyer")) {
				System.out.println("Buyer's space");
				session.setAttribute("user", "Buyer");
				resp.sendRedirect("buyer.jsp");
			}else if(validate.equals("Seller")) {
				System.out.println("Seller's space");
				session.setAttribute("user", "Seller");
				resp.sendRedirect("seller.jsp");				 
			}else {
				out.print("Invalid credentials..try again!!!");
				RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
				dispatcher.include(request, response);
			}
			
		} catch (Exception e) {
			System.out.println("Exception "+e);
		}*/		
	}

}
