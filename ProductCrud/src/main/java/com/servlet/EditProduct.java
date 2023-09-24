package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ProductDao;
import com.model.Product;

@WebServlet("/EditProduct")
public class EditProduct extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		Product pd = new Product();
		
		String id = req.getParameter("id");
		int pId = Integer.parseInt(id);
		pd.setId(pId);
		System.out.println("id" +pd.getId());
		
		pd.setProdName(req.getParameter("prodName"));
		System.out.println("Pro name"+pd.getProdName());
		
		pd.setProdDesc(req.getParameter("prodDesc"));
		System.out.println("desc"+pd.getProdDesc());
		
		pd.setProdImg(req.getParameter("prodImg"));
		System.out.println("image "+pd.getProdImg());
		
		String sellPrice = req.getParameter("prodSellPrice");
		double d = Double.parseDouble(sellPrice);
		pd.setProdSellPrice(d);
		System.out.println("sell"+pd.getProdSellPrice());
		
		String costPrice = req.getParameter("prodCostPrice");
		double pCostPrice = Double.parseDouble(costPrice);
		pd.setProdCostPrice(pCostPrice);
		System.out.println("cost"+pd.getProdCostPrice());
		
		String units = req.getParameter("stockUnit");
		int stock = Integer.parseInt(units);
		pd.setStockUnit(stock);
		System.out.println("stock"+pd.getStockUnit());
		
		String uCreated = req.getParameter("userCreated");
		int userCreate = Integer.parseInt(uCreated);
		pd.setUserCreated(userCreate);
		System.out.println("user create"+pd.getUserCreated());
		
		int product = ProductDao.updateProduct(pd);
		
		if(product > 0) {
			out.print("Product updated");
			resp.sendRedirect("ViewProduct");
		}else {
			out.print("Unable to update product");
			req.getRequestDispatcher("editProductForm.jsp").include(req, resp);
		}
	}

}
