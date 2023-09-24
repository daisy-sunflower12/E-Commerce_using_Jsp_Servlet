package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PurchaseDao;
import com.model.User;
import com.model.UserPurchase;

@WebServlet("/orderNow")
public class OrderNow extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

		try {
			User auth = (User) req.getSession().getAttribute("user");

			if (auth != null) 
			{
				String pId = req.getParameter("id");
				int productId = Integer.parseInt(pId);

				String prodQty = req.getParameter("qty");
				int pQty = Integer.parseInt(prodQty);
				if (pQty <= 0) {
					pQty = 1;
				}				
				
				UserPurchase up = new UserPurchase();
				up.setId(productId);
				System.out.println(productId);
				up.setUserId(auth.getId());
				System.out.println(auth.getId());
				up.setTotalUnit(pQty);
				System.out.println(pQty);
				System.out.println(req.getParameter("purchaseFromUser"));
				String sId = req.getParameter("purchaseFromUser");
				int seller = Integer.parseInt(sId);
				System.out.println(seller);
				up.setPurchaseFromUser(seller);
				System.out.println(up.getPurchaseFromUser());
//				up.setPurchaseDt(up.getPurchaseDt());
//				System.out.println(up.getPurchaseDt());
				up.setPurchaseDt(formatter.format(date));
				System.out.println(formatter.format(date));
				boolean result = PurchaseDao.insertOrder(up);

				if (result) 
				{
					@SuppressWarnings("unchecked")
					ArrayList<UserPurchase> cart_list = (ArrayList<UserPurchase>) req.getSession()
							.getAttribute("cart-list");
					if (cart_list != null) 
					{
						for (UserPurchase c : cart_list) 
						{
							if (c.getId() == productId) 
							{
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
					}
					resp.sendRedirect("orders.jsp");
				} else {
					out.print("order failed");
				}

			} else {
				if (auth == null) {
					out.print("Auth failed");
					resp.sendRedirect("orders.jsp");
				}
			}
		} catch (Exception e) {
			out.print("order failed");
			System.out.println("Exception123 "+e);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
