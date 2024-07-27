package com.ProductDemoSprinBootMVC.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ProductDemoSprinBootMVC.Entity.Product;


@Component
public class ProductDBNormal {
	Connection con=null;
	
			public ProductDBNormal()
			{
		try {
			//con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/productdemo","root","roopa");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/productdemo","root","roopa");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	public void save(Product p)
	{
		
		String query="insert into products (name,type,place,warranty) values (?,?,?,?)";
		try {
			PreparedStatement st=con.prepareStatement(query);
			st.setString(1, p.getName());
			st.setString(2, p.getType());
			st.setString(3, p.getType());
			st.setInt(4, p.getWarranty());
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<>();
		String query="select * from  products";

			try {
				PreparedStatement st=con.prepareStatement(query);
				ResultSet set=st.executeQuery();
				while(set.next())
				{
					Product p=new Product();
		            p.setId(set.getInt("id"));          
		            p.setName(set.getString("name"));
		            p.setType(set.getString("type"));
		            p.setPlace(set.getString("place"));
		            p.setWarranty(set.getInt("warranty"));
//					p.setName(set.getString(1));
//					p.setType(set.getString(2));
//					p.setPlace(set.getString(3));
//					p.setWarranty(set.getInt(4));
					products.add(p);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return products;
	}
	public List<Product> getProductByName(String name) {
		// TODO Auto-generated method stub
	    String query = "SELECT name, type, place, warranty FROM products WHERE name = ?";
		List<Product> products = new ArrayList<>();
	

			try {
				PreparedStatement st=con.prepareStatement(query);
				st.setString(1, name);
				ResultSet set=st.executeQuery();
				while(set.next())
				{
					Product p=new Product();
		          
		            p.setName(set.getString("name"));
		            p.setType(set.getString("type"));
		            p.setPlace(set.getString("place"));
		            p.setWarranty(set.getInt("warranty"));
		            products.add(p);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return products;
	}
	
}
