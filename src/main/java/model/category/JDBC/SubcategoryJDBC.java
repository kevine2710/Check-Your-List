package model.category.JDBC;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import database.ConnectionDB;
import model.category.Category;
import model.category.Subcategory;

/**
 * persistance of sub-category
 *
 */
public class SubcategoryJDBC extends Subcategory {
	
	/**
	 * constructor of SubcategoryJDBC
	 */
	public SubcategoryJDBC() {
		super();
	}
	
	/**
	 * constructor of SubcategoryJDBC
	 * @param sub-category
	 */
	public SubcategoryJDBC(Category sub) {
		super(sub);
	}
	
	/**
	 * constructor of SubcategoryJDBC
	 * @param name
	 * @param shortDescription
	 * @param detailedDescription
	 * @param category
	 */
	public SubcategoryJDBC(String name, String shortDescription, String detailedDescription, Category category){
		super(name, shortDescription, detailedDescription, category);	
	}
	
	/**
	 * constructor of SubcategoryJDBC
	 * @param name
	 */
	public SubcategoryJDBC(String name) {
		super(name);
	}
	
	/**
	 * get sub-category with its name
	 * @return sub-category
	 */
	public Subcategory readByName() {
		String sql = ("SELECT * FROM subCategory WHERE name = '" +  this.name + "'");
		Subcategory c = null;
		try {
			Statement stm = ConnectionDB.creetConnectionDB().getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if ( rs.next() ) {
				ResultSetMetaData resultMeta = rs.getMetaData();
				if (resultMeta.getTableName(1).equals("subCategory")) {
					this.name = (String) rs.getObject("name");
					this.shortDescription = (String) rs.getObject("shortDescription");
					this.detailedDescription = (String) rs.getObject("detailedDescription");
					this.category = (Category) rs.getObject("name_category");
					c = new SubcategoryJDBC(name, shortDescription, detailedDescription, category);
				}
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Subcategory)c;
	}
	
	/**
	 * save a sub-category on the DB
	 * @return true if the sub-category is saved else false
	 */
	public boolean save() {
		String sql = ("INSERT INTO subCategory VALUES ( '" +  this.name + "','" + this.shortDescription + "','" + 
				this.detailedDescription + "','" + this.category +"')");
		try {
			Statement stm = ConnectionDB.creetConnectionDB().getConn().createStatement();
			return stm.execute(sql);
		} catch (SQLException e) {}
		return false;
	}
	
	@Override
	public List<Subcategory> getAllSubcategories() {
		String sql = ("SELECT * FROM subcategory");
		List<Subcategory> list = new ArrayList<Subcategory>();
		try {
			Statement stm = ConnectionDB.creetConnectionDB().getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while ( rs.next() ) {
				Subcategory subcat = new SubcategoryJDBC();
				ResultSetMetaData resultMeta = rs.getMetaData();
				if (resultMeta.getTableName(1).equals("subcategory")) {
					subcat.setName((String) rs.getObject("name"));
					subcat.setDetailedDescription((String) rs.getObject("detailleddescription"));
					subcat.setShortDescription((String) rs.getObject("shortdescription"));
					list.add(subcat);
				}
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean subcategoryExist(String subcatName) {
			List<Subcategory> list = getAllSubcategories();
			Iterator<Subcategory> iterator = list.iterator();
			while (iterator.hasNext()) {
				if(iterator.next().toString().equals(subcatName)) {
					return true;
				}
			}	
			return false;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
