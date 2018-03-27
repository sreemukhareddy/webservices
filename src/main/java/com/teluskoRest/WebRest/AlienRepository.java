package com.teluskoRest.WebRest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {

	//List<Alien> aliens;
	
	Connection conn=null;
	
	public AlienRepository() {
		
		String url="jdbc:mysql://localhost:3306/restdb";
		String username="root";
		String pass="123456";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url, username,pass);
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public List<Alien> getAliens(){
		
		List<Alien> aliens=new ArrayList<Alien>();
		String sql="select * from alien";
		
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				Alien a=new Alien();
				a.setName(rs.getString(1));
				a.setPoints(rs.getInt(2));
				aliens.add(a);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return aliens;
		
	}
	
	public Alien getAlien(String name) {
        String sql="select * from alien where name="+name;
		Alien alien=new Alien();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				alien.setName(rs.getString(1));
				alien.setPoints(rs.getInt(2));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return alien;
		
	}

	public void createAlien(Alien a) {

		 String sql="insert into alien values(?,?)";
			
			try {
				PreparedStatement st=conn.prepareStatement(sql);
				st.setString(1,a.getName());
				st.setInt(2, a.getPoints());
				st.executeUpdate();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
	}
	
	public void updateAlien(Alien a) {

		 String sql="update alien set points=? where name=?";
			
			try {
				PreparedStatement st=conn.prepareStatement(sql);
				st.setString(2,a.getName());
				st.setInt(1, a.getPoints());
				st.executeUpdate();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
	}
	
	public void deleteAlien(String name) {
		 String sql="delete from alien where name=?";
			
			try {
				PreparedStatement st=conn.prepareStatement(sql);
				st.setString(1, name);
				st.executeUpdate();
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}
}
