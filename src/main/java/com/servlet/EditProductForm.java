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

@WebServlet("/EditProductForm")
public class EditProductForm extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String id = req.getParameter("id");
		int prod_id = Integer.parseInt(id);		
		Product product = ProductDao.getProductById(prod_id);
		req.setAttribute("p", product);
		req.getRequestDispatcher("editProductForm.jsp").forward(req, resp);
	}

}
