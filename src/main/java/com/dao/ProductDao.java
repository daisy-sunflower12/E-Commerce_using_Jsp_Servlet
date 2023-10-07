package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.Product;
import com.model.UserPurchase;

public class ProductDao {

	public static Connection getConnection() {

		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/prod_crud?&useSSL=false", "root", "sushet");
		} catch (Exception e) {
			System.out.println("Exceptio is " + e);
		}
		return con;
	}

	public static int createProduct(Product prod) {
		int rows = 0;

		try {
			Connection con = ProductDao.getConnection();
			PreparedStatement pst = con.prepareStatement(
					"insert into product(prod_name, prod_desc, prod_image, prod_sell_price, prod_cost_price, "
							+ "prod_stock_unit, user_created) value(?,?,?,?,?,?,?) ");

			pst.setString(1, prod.getProdName());
			pst.setString(2, prod.getProdDesc());
			pst.setString(3, prod.getProdImg());
			pst.setDouble(4, prod.getProdSellPrice());
			pst.setDouble(5, prod.getProdCostPrice());
			pst.setInt(6, prod.getStockUnit());
			pst.setInt(7, prod.getUserCreated());
			rows = pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}

		return rows;
	}

	public static int updateProduct(Product prod) {
		int rows = 0;

		try {
			Connection con = ProductDao.getConnection();
			PreparedStatement pst = con.prepareStatement(
					"update product set prod_desc=?, prod_image=?, prod_sell_price=?, prod_cost_price=?,"
							+ "prod_stock_unit=? where prod_id=?");

			pst.setString(1, prod.getProdDesc());
			pst.setString(2, prod.getProdImg());
			pst.setDouble(3, prod.getProdSellPrice());
			pst.setDouble(4, prod.getProdCostPrice());
			pst.setInt(5, prod.getStockUnit());
			pst.setInt(6, prod.getId());

			rows = pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}
		return rows;

	}

	public static int deleteProduct(int id) {
		int rows = 0;

		try {
			Connection con = ProductDao.getConnection();
			PreparedStatement pst = con.prepareStatement("delete from product where prod_id=?");
			pst.setInt(1, id);
			rows = pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}

		return rows;
	}

	public static List<Product> getAllProducts() {
		List<Product> list = new ArrayList<>();

		try {
			Connection con = ProductDao.getConnection();
			PreparedStatement pst = con.prepareStatement("Select * from product;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Product prod = new Product();
				prod.setId(rs.getInt("prod_id"));
				prod.setProdName(rs.getString("prod_name"));
				prod.setProdDesc(rs.getString("prod_desc"));
				prod.setProdImg(rs.getString("prod_image"));
				prod.setProdSellPrice(rs.getDouble("prod_sell_price"));
				prod.setProdCostPrice(rs.getDouble("prod_cost_price"));
				prod.setStockUnit(rs.getInt("prod_stock_unit"));
				prod.setUserCreated(rs.getInt("user_created"));
				list.add(prod);

			}
			con.close();
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}

		return list;

	}

	public static Product getProductById(int id) {
		Product p = new Product();

		try {
			Connection con = ProductDao.getConnection();
			PreparedStatement pst = con.prepareStatement("select * from product where prod_id=?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				p.setId(rs.getInt("prod_id"));
				// System.out.println(rs.getInt("prod_id"));
				p.setProdName(rs.getString("prod_name"));
				p.setProdDesc(rs.getString("prod_desc"));
				p.setProdImg(rs.getString("prod_image"));
				p.setProdSellPrice(rs.getDouble("prod_sell_price"));
				p.setProdCostPrice(rs.getDouble("prod_cost_price"));
				p.setStockUnit(rs.getInt("prod_stock_unit"));
				p.setUserCreated(rs.getInt("user_created"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Exception " + e);
		}
		return p;
	}

	public static double getCartPrice(ArrayList<UserPurchase> purchases) {

		double price = 0;

		try {
			Connection con = ProductDao.getConnection();
			if (purchases.size() > 0) {
				for (UserPurchase c : purchases) {
					PreparedStatement pst = con.prepareStatement("select prod_sell_price from product where prod_id=?");
					pst.setInt(1, c.getId());
					ResultSet rs = pst.executeQuery();
					
					while (rs.next()) {
						price = price + rs.getDouble("prod_sell_price") * c.getTotalUnit();
						c.setProdSellPrice(rs.getDouble("prod_sell_price"));
					}
					System.out.println("cart price " +price);
					System.out.println("Total Unit "+c.getTotalUnit());
				}
			}
		} catch (Exception e) {
			System.out.println("Exception "+e);
		}
		return price;
	}
	
	public static List<UserPurchase> getAllCartProducts(ArrayList<UserPurchase> purchases){
		List<UserPurchase> list = new ArrayList<>();
		
		try {
			Connection con = ProductDao.getConnection();
			if(purchases.size() > 0) {
				for(UserPurchase c : purchases) {
					PreparedStatement pst = con.prepareStatement("select * from product where prod_id=?");
					pst.setInt(1, c.getId());
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()) {
						UserPurchase up = new UserPurchase();
						up.setId(rs.getInt("prod_id"));
						System.out.println(up.getId());
						up.setProdName(rs.getString("prod_name"));
						System.out.println(up.getProdName());
						up.setTotalCostPrice((rs.getDouble("prod_cost_price")*c.getTotalUnit()));
						System.out.println(up.getTotalCostPrice());
						up.setTotalUnit(c.getTotalUnit());
						System.out.println(up.getTotalUnit());
						list.add(up);
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("Exception "+e);
		}
		
		return list;
		
	}
}
