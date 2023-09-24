package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.UserPurchase;

@WebServlet("/RemoveFromCart")
public class RemoveFromCart extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		try {
			String productId = req.getParameter("id");
			
			if(productId != null) {
				
				
				ArrayList<UserPurchase> cart_list = (ArrayList<UserPurchase>)req.getSession().getAttribute("cart-list");
				if(cart_list != null) {
					for(UserPurchase c: cart_list) {
						if(c.getProdId() == Integer.parseInt(productId)) {
							cart_list.remove(cart_list.indexOf(c));
							break;
						}
					}
				}
				
				resp.sendRedirect("cart.jsp");
			}else {
				resp.sendRedirect("cart.jsp");
			}
						
		} catch (Exception e) {
			System.out.println("Exception "+e);
		}
	}

}
