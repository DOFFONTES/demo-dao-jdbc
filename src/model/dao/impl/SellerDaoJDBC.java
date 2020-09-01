package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub	
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
				"select seller.*,department.Name as DepName "
				+ "from seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id "
				+ "where seller.Id = ?");
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Department dep = new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
				Seller sel = new Seller();
				
				sel.setId(rs.getInt("Id"));
				sel.setName(rs.getString("Name"));
				sel.setEmail(rs.getString("Email"));
				sel.setBirthDate(rs.getDate("BirthDate"));
				sel.setBaseSalary(rs.getDouble("BaseSalary"));
				sel.setDepartment(dep);
				 
				return sel;
			}
			
			return null;
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
