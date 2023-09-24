package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
					"insert into purchase(prod_id, pur_by_user, total_unit, pur_dt, pur_from_user) values(?,?,?,?,?)");
			pst.setInt(1, model.getId());
			pst.setInt(2, model.getUserId());
			pst.setInt(3, model.getTotalUnit());
			Date ds = Date.valueOf(model.getPurchaseDt());
			pst.setDate(4, ds);
			pst.setInt(5, model.getPurchaseFromUser());
			System.out.println("Seller id in dao "+model.getPurchaseFromUser());
			pst.executeUpdate();
			result = true;
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
			pst.setInt(2, purchase.getProdId());
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

	public static List<UserPurchase> getAllPurchases(int id) {
		List<UserPurchase> list = new ArrayList<>();

		try {
			Connection con = PurchaseDao.getConnection();
			PreparedStatement pst = con.prepareStatement(
					"select purchase.* , user.balance, product.prod_cost_price, prod_name, sum(prod_cost_price*purchase.total_unit) as total_price \r\n"
					+ "from purchase left join product on purchase.prod_id = product.prod_id"
					+ "left join user on purchase.pur_by_user= user.u_id where pur_by_user=?"
					+ "group by pur_id, pur_dt, prod_id, total_unit, pur_by_user, pur_from_user, prod_cost_price,"
					+ "prod_name, prod_cost_price, balance");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				UserPurchase up = new UserPurchase();
				up.setPurchaseId(rs.getInt("pur_id"));
				up.setPurchaseDt(rs.getString("pur_dt"));
				up.setProdId(rs.getInt("prod_id"));
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
			System.out.println("Exception " + e);
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

}
