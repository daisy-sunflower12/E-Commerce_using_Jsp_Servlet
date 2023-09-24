package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PurchaseDao;
import com.model.User;
import com.model.UserPurchase;

@WebServlet("/checkOutCart")
public class CheckOutOrders extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ArrayList<UserPurchase> cart_List = (ArrayList<UserPurchase>) req.getSession().getAttribute("cart-list");
		User auth = (User) req.getSession().getAttribute("user");
		try {
			if (cart_List != null && auth != null) {
				for (UserPurchase c : cart_List) {
					UserPurchase up = new UserPurchase();
					up.setPurchaseId(c.getPurchaseId());
					up.setUserId(auth.getId());
					up.setTotalUnit(c.getTotalUnit());
					up.setPurchaseDt(c.getPurchaseDt());

					boolean result = PurchaseDao.insertOrder(up);
					if (!result)
						break;
				}

				cart_List.clear();
				resp.sendRedirect("buyer.jsp");
			}

			else {
				if (auth == null) {
					resp.sendRedirect("login.jsp");
				}
				resp.sendRedirect("cart.jsp");
			}
		} catch (Exception e) {
			System.out.println("Exception " + e);
		}
	}
}
