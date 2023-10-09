package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ProductDao;
import com.dao.PurchaseDao;
import com.dao.UserDao;
import com.model.Product;
import com.model.User;
import com.model.UserPurchase;

@WebServlet("/cancelOrder")
public class CancelOrder extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			User auth = (User) req.getSession().getAttribute("user");
			System.out.println("Auth " + auth);

			if (auth != null) {
				String id = req.getParameter("id");
				int pId = Integer.parseInt(id);
				System.out.println("Purchase Id: " + pId);

				UserPurchase purchaseById = PurchaseDao.getPurchaseById(pId);
				purchaseById.getId();
				System.out.println("Product from purchase: " + purchaseById.getId());
				Product prodById = ProductDao.getProductById(purchaseById.getId());
				System.out.println("Prod : " + prodById);

				int seller = prodById.getUserCreated();
				System.out.println("Seller Id: " + seller);

				User u = new User();
				u.setId(seller);

				User userById = UserDao.getUserById(seller);
				System.out.println("User: " + userById);

				System.out.println("Seller Bal: "+userById.getBalance());
				userById.setBalance(
						userById.getBalance() - (purchaseById.getTotalUnit() * prodById.getProdSellPrice()));
				System.out.println("Seller Bal after cancel: " + userById.getBalance());
				
				System.out.println("Buyer balance: "+auth.getBalance());
				
				//BUYER
				auth.setBalance(auth.getBalance() + (purchaseById.getTotalUnit() * prodById.getProdSellPrice()));
				System.out.println("Buyer bal after cancel: "+auth.getBalance());
				
			if (id != null) {
				PurchaseDao.cancelOrder(pId);
				PurchaseDao.updateSellerBal(userById);
				PurchaseDao.updateBuyerBal(auth);
			}
			resp.sendRedirect("orders.jsp");

			}
		} catch (Exception e) {
			System.out.println("Exception " + e);
		}
	}
}
