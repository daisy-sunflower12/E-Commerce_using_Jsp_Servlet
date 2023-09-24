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

@WebServlet("/quantityManage")
public class QtyManageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		resp.setContentType("text/html");
//		PrintWriter out = resp.getWriter();

		try {

			String action = req.getParameter("action");
			int pId = Integer.parseInt(req.getParameter("id"));

			@SuppressWarnings("unchecked")
			ArrayList<UserPurchase> cart_list = (ArrayList<UserPurchase>) req.getSession().getAttribute("cart-list");
			if (action != null && pId >= 1) {
				if (action.equals("inc")) {
					for (UserPurchase c : cart_list) {
						System.out.println(c.getProdId());
						System.out.println(pId);
						if (c.getId() == pId) {
							int qty = c.getTotalUnit();
							qty++;
							c.setTotalUnit(qty);
							resp.sendRedirect("cart.jsp");
						}
					}
				}

				if (action.equals("dec")) {
					for (UserPurchase c : cart_list) {
						if (c.getId() == pId && c.getTotalUnit() > 1) {
							int qty = c.getTotalUnit();
							qty--;
							c.setTotalUnit(qty);
						}
					}

					resp.sendRedirect("cart.jsp");
				}
			}else {
				resp.sendRedirect("cart.jsp");
			}
		}catch (Exception e) {
			System.out.println("Exception " + e);
		}
	}
}
