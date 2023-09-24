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

import com.model.UserPurchase;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out = resp.getWriter()) {
			out.print("add to cart servlet");

			List<UserPurchase> cartlist = new ArrayList<>();

			String id = req.getParameter("id");
			int pId = Integer.parseInt(id);
			System.out.println(pId);
			
			String sId = req.getParameter("sellerId");
			int seller = Integer.parseInt(sId);
			System.out.println(seller);
			UserPurchase up = new UserPurchase();
			up.setId(pId);
			System.out.println(up.getId());
			up.setTotalUnit(1);
			up.setPurchaseFromUser(seller);

			HttpSession sess = req.getSession();

			List<UserPurchase> cart_list = (List<UserPurchase>) sess.getAttribute("cart-list");
			if (cart_list == null) {
				cartlist.add(up);
				sess.setAttribute("cart-list", cartlist);
				resp.sendRedirect("buyer.jsp");
			} else {
				cartlist = cart_list;

				boolean exist = false;
				for (UserPurchase c : cart_list) {
					if (c.getId() == pId) {
						exist = true;
						out.println(
								"<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart.jsp'>GO to Cart Page</a></h3>");
					}
				}

				if (!exist) {
					cartlist.add(up);
					resp.sendRedirect("buyer.jsp");
				}

			}
		}

	}
}
