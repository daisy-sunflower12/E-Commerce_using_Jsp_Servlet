package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.Product;
import com.model.User;
import com.model.UserPurchase;

public class PurchaseDao {

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

	public static boolean insertOrder(UserPurchase model) {
		boolean result = false;
		try {
			Connection con = PurchaseDao.getConnection();
			PreparedStatement pst = con.prepareStatement(
					"insert into purchase(pur_dt, prod_id, total_unit, pur_by_user, pur_from_user) values(?,?,?,?,?)");

			Date ds = Date.valueOf(model.getPurchaseDt());
			pst.setDate(1, ds);
			System.out.println("Pur Dt: " + model.getPurchaseDt());
			pst.setInt(2, model.getId());
			System.out.println("Product Id: " + model.getId());
			pst.setInt(3, model.getTotalUnit());
			System.out.println("Qty: " + model.getTotalUnit());
			pst.setInt(4, model.getUserId());
			System.out.println("User id: " + model.getUserId());
			pst.setInt(5, model.getPurchaseFromUser());
			System.out.println("Seller id in dao " + model.getPurchaseFromUser());
			pst.executeUpdate();
			result = true;
			pst.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Exception e " + e.getMessage());
		}
		return result;
	}

	public static int addToCart(UserPurchase purchase) {
		int rows = 0;

		try {
			Connection con = PurchaseDao.getConnection();
			PreparedStatement pst = con.prepareStatement(
					"insert into purchase(purchase_dt, prod_id, total_unit, pur_by_user, pur_from_user)values(?,?,?,?,?)");
			pst.setString(1, purchase.getPurchaseDt());
			pst.setInt(2, purchase.getId());
			pst.setInt(3, purchase.getTotalUnit());
			pst.setInt(4, purchase.getPurchaseByUser());
			pst.setInt(5, purchase.getPurchaseFromUser());

			rows = pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println("Exception22 " + e);
		}

		return rows;
	}
	
	public static UserPurchase getPurchaseById(int id) {
		
		UserPurchase up = new UserPurchase();
			try {
				
				Connection con = UserDao.getConnection();
				PreparedStatement pst = con.prepareStatement("Select * from purchase where pur_id=?");
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					up.setId(rs.getInt("pur_id"));
					up.setPurchaseDt(rs.getString("pur_dt"));
					up.setId(rs.getInt("prod_id"));
					up.setTotalUnit(rs.getInt("total_unit"));
					up.setPurchaseByUser(rs.getInt("pur_by_user"));
					up.setPurchaseFromUser(rs.getInt("pur_from_user"));
				}
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return up;
			
		}

	public static List<UserPurchase> getAllPurchases(int id) {
		List<UserPurchase> list = new ArrayList<>();

		try {
			Connection con = PurchaseDao.getConnection();
			PreparedStatement pst = con
					.prepareStatement("SELECT purchase.*, user.balance, product.prod_cost_price, prod_name,"
							+ "SUM(prod_cost_price * purchase.total_unit) AS total_price FROM purchase "
							+ "LEFT JOIN product ON purchase.prod_id = product.prod_id "
							+ "LEFT JOIN user ON purchase.pur_by_user = user.u_id "
							+ "WHERE pur_by_user=? GROUP BY pur_id, pur_dt, prod_id, total_unit, pur_by_user, pur_from_user, "
							+ "prod_cost_price, prod_name, balance");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				UserPurchase up = new UserPurchase();
				up.setPurchaseId(rs.getInt("pur_id"));
				up.setPurchaseDt(rs.getString("pur_dt"));
				up.setId(rs.getInt("prod_id"));
				up.setProdCostPrice(rs.getDouble("prod_cost_price"));
				up.setTotalUnit(rs.getInt("total_unit"));
				up.setPurchaseByUser(rs.getInt("pur_by_user"));
				up.setPurchaseFromUser(rs.getInt("pur_from_user"));
				up.setProdName(rs.getString("prod_name"));
				up.setTotalCostPrice(rs.getInt("total_price"));
				double uBal = rs.getDouble("balance");
				up.setUserBalance(uBal);
				list.add(up);
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Exception in list " + e);
		}

		return list;
	}

	public static void cancelOrder(int id) {

		try {
			Connection con = PurchaseDao.getConnection();
			PreparedStatement pst = con.prepareStatement("delete from purchase where pur_id=?");
			pst.setInt(1, id);
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println("Exception " + e);
		}
	}
	
	public static Product updateProduct(Product product) {
		
		try {
			Connection con = ProductDao.getConnection();
			PreparedStatement pst = con.prepareStatement("update product set prod_stock_unit = ?1 where prod_id = ?2");
			pst.setInt(1, product.getStockUnit());
			System.out.println("Qty: "+product.getStockUnit());
			pst.setInt(2, product.getId());
			System.out.println("Id: "+product.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
		
	}

	public static User updateSellerBal(User user) {
		
		try {
			Connection con = UserDao.getConnection();
			PreparedStatement pst = con.prepareStatement("update user set balance = ? where u_id = ?");
			pst.setDouble(1, user.getBalance());
			System.out.println("Qty: "+user.getBalance());
			pst.setInt(2, user.getId());
			System.out.println("Id: "+user.getId());
			pst.executeUpdate();
			con.close();
			System.out.println("User "+user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
		
	}
	
	public static User updateBuyerBal(User user) {
		
		try {
			Connection con = UserDao.getConnection();
			PreparedStatement pst = con.prepareStatement("update user set balance = ? where u_id = ?");
			pst.setDouble(1, user.getBalance());
			System.out.println("Qty: "+user.getBalance());
			System.out.println("Hello Github");
			pst.setInt(2, user.getId());
			System.out.println("Id: "+user.getId());
			pst.executeUpdate();
			pst.close();
			con.close();
			System.out.println("User "+user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
		
	}
	
}
