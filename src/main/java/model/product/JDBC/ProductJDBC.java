package model.product.JDBC;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionDB;
import model.category.Subcategory;
import model.category.JDBC.SubcategoryJDBC;
import model.person.Seller;
import model.person.JDBC.SellerJDBC;
import model.product.Product;

/**
 * persistance of product
 *
 */
public class ProductJDBC extends Product {
	
	/**
	 * constructor of productJDBC
	 */
	public ProductJDBC() {
		super();
	}
	
	/**
	 * constructor of productJDBC
	 * @param name
	 * @param seller
	 * @param subCategory
	 */
	public ProductJDBC(String name, Seller seller, Subcategory subCategory){
		super(name,seller,subCategory);
	}
	
	/**
	 * constructor of productJDBC
	 * @param id
	 * @param name
	 * @param seller
	 * @param subCategory
	 */
	public ProductJDBC(int id,String name, Seller seller, Subcategory subCategory){
		super(id,name,seller,subCategory);
	}
	
	/**
	 * constructor of productJDBC
	 * @param name
	 * @param seller
	 * @param price
	 * @param quantity
	 * @param subCategory
	 */
	public ProductJDBC(String name, Seller seller, float price, int quantity, Subcategory subCategory) {
		super(name,seller,price,quantity,subCategory);
	}
	
	/**
	 * constructor of productJDBC
	 * @param sub
	 */
	public ProductJDBC(Subcategory sub){
		super(sub);
	}

	@Override
	public String toString() {
		return "ProductJDBC [name=" + name + "]";
	}

	/*CREATE TABLE product
	(
	  id serial NOT NULL,
	  name character,
	  id_sell serial NOT NULL,
	  name_subcategory character,
	  [...]
	 */
	@Override
	public boolean save(){
		if (!checkProductExist()){
			String sql = ("INSERT INTO  product (name,pseudo,price,quantity,name_subcategory)VALUES ("
					+ "'" +  this.name + "','"
					+ this.seller.getPseudo() + "','"
					+ this.price + "','" 
					+ this.quantity + "','" 
					+ this.subCategory.getName() + "')");
			try {
				Statement stm = ConnectionDB.creetConnectionDB().getConn().createStatement();
				stm.execute(sql);
				return true;
			} catch (SQLException e) 
				{e.printStackTrace();}
		}
		System.out.println("produit exist");
		return false;
		
	}
	
	@Override
	public boolean delete(){
		// r�cup�rer l'id du produit en fonction du nom et du seller
		// int idProd = readByName();
		String sql = ("delete from product where id="+ this.idProd +"");
		try {
			Statement stm = ConnectionDB.creetConnectionDB().getConn().createStatement();
			return stm.execute(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//r�cup�re l'id d'un produit en fonction du nom et du vendeur
	@Override
	public Product readByNameAndSeller(){
		//si le nom ou le seller vide alors Erreur   <--------------------------
		String sql = ("SELECT * FROM product WHERE name  = '" +  this.name + "' AND pseudo = '" +this.seller.getPseudo() +"'");
		Product u = null;
		SellerJDBC sellerJDBC;
		SubcategoryJDBC subCategoryJDBC;
		try {
			Statement stm = ConnectionDB.creetConnectionDB().getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if ( rs.next() ) {
				ResultSetMetaData resultMeta = rs.getMetaData();
				if (resultMeta.getTableName(1).equals("product")) {
					this.idProd = (int) rs.getObject("id");	
					this.name = (String) rs.getObject("name");	
					String pseudo = (String) rs.getObject("pseudo");
					sellerJDBC = new SellerJDBC(pseudo);			
					this.seller = sellerJDBC.readByPseudo();
					this.price = (float) rs.getObject("price");
					this.quantity = (int) rs.getObject("quantity");
					String nomCategory  = (String) rs.getObject("name_subcategory");
					subCategoryJDBC = new SubcategoryJDBC(nomCategory);
					this.subCategory = subCategoryJDBC.readByName();
					u = new ProductJDBC(this.idProd,this.name,this.seller,this.subCategory);
				}
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Product)u;
	}
	
	@Override
	public List<Product> getAllProductFromSubCategory(){
		//si le nom ou le seller vide alors Erreur   <--------------------------
		List<Product> list = new ArrayList<Product>();
		String sql = ("SELECT * FROM product WHERE name_subcategory  = '" +  this.subCategory.getName()+"'");
		SellerJDBC sellerJDBC;
		SubcategoryJDBC subCategoryJDBC;
		Product prod = null;
		try {
			Statement stm = ConnectionDB.creetConnectionDB().getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while( rs.next() ) {	
				prod = new ProductJDBC();
				prod.setIdProd((int) rs.getObject("id"));	
				prod.setName((String) rs.getObject("name"));	
				String pseudo = (String) rs.getObject("pseudo");
				sellerJDBC = new SellerJDBC(pseudo);			
				prod.setSeller(sellerJDBC.readByPseudo());
				prod.setPrice(rs.getFloat("price"));
				prod.setQuantity((int) rs.getObject("quantity"));
				prod.setSubCategory(this.subCategory);
				list.add(prod);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<Product> getAllProductFromSubCategoryAndNoPresentTask(){
		//si le nom ou le seller vide alors Erreur   <--------------------------
		List<Product> list = new ArrayList<Product>();
		String sql = ("SELECT * FROM product p WHERE name_subcategory  = '" +  this.subCategory.getName()+"' AND NOT EXISTS ("+
				"SELECT 1 FROM lineRessource l WHERE name_subcategory  = '" +  this.subCategory.getName()+
				"' AND p.id = l.id_product)" );
		SellerJDBC sellerJDBC;
		SubcategoryJDBC subCategoryJDBC;
		Product prod = null;
		try {
			Statement stm = ConnectionDB.creetConnectionDB().getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while( rs.next() ) {	
				prod = new ProductJDBC();
				prod.setIdProd((int) rs.getObject("id"));	
				prod.setName((String) rs.getObject("name"));	
				String pseudo = (String) rs.getObject("pseudo");
				sellerJDBC = new SellerJDBC(pseudo);			
				prod.setSeller(sellerJDBC.readByPseudo());
				prod.setPrice(rs.getFloat("price"));
				prod.setQuantity((int) rs.getObject("quantity"));
				prod.setSubCategory(this.subCategory);
				list.add(prod);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * find if the product exist
	 * @return true if the product exist else false
	 */
	public boolean checkProductExist(){
		String sql1 = ("SELECT * FROM product "
				+ "WHERE name = '" + this.name + "' AND"
				+ " pseudo = '" + this.seller.getPseudo() +"' AND"
				+ " name_subcategory = '"+ this.subCategory.getName() +"'");
		try {
			
			Statement stm1 = ConnectionDB.creetConnectionDB().getConn().createStatement();
			ResultSet rs1 = stm1.executeQuery(sql1);
			return rs1.next();
		} catch (SQLException e) {e.printStackTrace();}
		return true;
	}

	@Override
	public boolean majQuantity(int qty) {
		this.quantity = qty;
		String sql = ("update product "
				+ "set quantity =" + this.quantity + " where id='"+ this.idProd +"'");
		try {
			Statement stm = ConnectionDB.creetConnectionDB().getConn().createStatement();
			return stm.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean majPrice(float price) {
		this.price = price;
		String sql = ("update product "
				+ "set price =" + this.price + " where id='"+ this.idProd +"'");
		try {
			Statement stm = ConnectionDB.creetConnectionDB().getConn().createStatement();
			return stm.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean majName(String name) {
		this.name = name;
		String sql = ("update product "
				+ "set name = '" + this.name + "' where id='"+ this.idProd +"'");
		try {
			Statement stm = ConnectionDB.creetConnectionDB().getConn().createStatement();
			return stm.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}