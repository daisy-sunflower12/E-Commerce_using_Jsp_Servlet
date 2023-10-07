package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dao.ProductDao;
import com.model.Product;

@WebServlet("/AddProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class AddProduct extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private final String SAVE_DIR = "files";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		final String SAVE_DIR="files";
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		Product pd = new Product();
		pd.setProdName(req.getParameter("prodName"));
		System.out.println("Pro name" + pd.getProdName());

		pd.setProdDesc(req.getParameter("prodDesc"));
		System.out.println("desc" + pd.getProdDesc());

		Part part = req.getPart("prodImg"); // Retrieves <input type="file" name="file">
		String image = part.getSubmittedFileName();
		String path = "D:/Workspace-2022/ProductCrud/src/main/webapp/"+SAVE_DIR+"/"+image;
		//String path = getServletContext().getRealPath("/"+"files"+File.separator+image);
		System.out.println(path);
		InputStream is = part.getInputStream();
        boolean succs = uploadFile(is, path);//
        if(succs) {
        	out.println("Uploaded file to this directory ");
        }else {
        	out.println("error");
        }

		pd.setProdImg(image);
		req.setAttribute("image", image);
		System.out.println("image "+image);

		String sellPrice = req.getParameter("prodSellPrice");
		double d = Double.parseDouble(sellPrice);
		pd.setProdSellPrice(d);
		System.out.println("sell" + pd.getProdSellPrice());

		String costPrice = req.getParameter("prodCostPrice");
		double pCostPrice = Double.parseDouble(costPrice);
		pd.setProdCostPrice(pCostPrice);
		System.out.println("cost" + pd.getProdCostPrice());

		String units = req.getParameter("stockUnit");
		int stock = Integer.parseInt(units);
		pd.setStockUnit(stock);
		System.out.println("stock" + pd.getStockUnit());

		String uCreated = req.getParameter("userCreated");
		int userCreate = Integer.parseInt(uCreated);
		pd.setUserCreated(userCreate);
		System.out.println("user create" + pd.getUserCreated());

		int product = ProductDao.createProduct(pd);

		if (product > 0) {
			out.print("Product created");
			resp.sendRedirect("addProduct.jsp");
		} else {
			out.print("Product not created");
			resp.sendRedirect("addProduct.jsp");
		}

	}

	private boolean uploadFile(InputStream is, String path) {
		boolean test = false;
		
		try {
			File targetFile = new File(path);
			Files.copy(is, targetFile.toPath());
			
//			byte[] byt = new byte[is.available()];
//			is.read();
//			FileOutputStream fos = new FileOutputStream(path);
//			fos.write(byt);
//			fos.flush();
//			fos.close();
			test = true;
		} catch (Exception e) {
			System.out.println("Exception "+e);
		}
		return test;
	}

}
