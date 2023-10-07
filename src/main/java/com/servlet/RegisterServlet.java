package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.model.User;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession sess = req.getSession();
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		User u = new User();
		
		u.setFirstName(req.getParameter("firstName"));
		u.setLastName(req.getParameter("lastName"));
		u.setEmail(req.getParameter("email"));
		u.setPassword(req.getParameter("password"));
		u.setDob( req.getParameter("dob"));
		
		String mobile = req.getParameter("mobile");
		int mob = Integer.parseInt(mobile);
		u.setMobile(mob);
		
		String balance = req.getParameter("balance");
		double d = Double.parseDouble(balance);
		u.setBalance(d);
		
		String role_id = req.getParameter("role_id");
		int i = Integer.parseInt(role_id);
		u.setRole_id(i);
		
		List<String> al = new ArrayList<String>();
		if(!u.getFirstName().equals(null) && u.getFirstName().matches("^[a-zA-Z]*$")) {
			System.out.println("FirstName is correct");
		}else {
			al.add("Invalid First name");	
		}
		if(!u.getLastName().equals(null) && u.getLastName().matches("^[a-zA-Z]*$")) {
			System.out.println("Last name is correct");
		}else {
			al.add("Invalid last name");
			
		}
		if(!u.getEmail().equals(null) && u.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			
			System.out.println("Email is correct");
		}else {
			al.add("Invalid email");
			
		}
		if(!u.getPassword().equals(null) && 
				u.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
			
			System.out.println("Password is correct");
		}else {
			al.add("Invalid password");
			
		}
		if(!u.getDob().equals(null) && 
				u.getDob().matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
			
			System.out.println("DOB is correct");
		}else {
			al.add("Invalid DOB");
			
		}
		if(u.getBalance() != 0) {
			
			System.out.println("Balance is correct");
		}else {
			al.add("Invalid balance");
			
		}
		if(u.getRole_id() != 0) {
			
			System.out.println("Role is correct");
		}else {
			al.add("Invalid user role");
			
		}
		if(al.size() > 0) {
			//out.print(al);
			sess.setAttribute("error_msg", al);
			
			req.getRequestDispatcher("register.jsp").include(req, resp);
			return;
		}else {
		
		try {
			int saveUser = UserDao.saveUser(u);
			
			if(saveUser >0 ) {
				out.print("Successfully Registered");
				sess.setAttribute("user", u);
				resp.sendRedirect("home.jsp");
			}else {
				out.print("Unable to Register");
				sess.setAttribute("user", u);
				req.getRequestDispatcher("register.jsp").include(req, resp);
			}
		} catch (Exception e) {
			System.out.println("Exception "+e);
		}
		}		
	}
}
