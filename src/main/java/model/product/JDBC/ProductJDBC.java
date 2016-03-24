package model.product.JDBC;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import UI.LoginUI;
import database.ConnectionDB;
import model.activity.Activity;
import model.category.Subcategory;
import model.category.JDBC.SubcategoryJDBC;
import model.person.Seller;
import model.person.User;
import model.person.JDBC.SellerJDBC;
import model.person.JDBC.UserJDBC;
import model.product.Product;


public class ProductJDBC extends Product{
	
	public ProductJDBC(String name, Seller seller, Subcategory subCategory){
		super(name,seller,subCategory);
	}
	
	public ProductJDBC(int id,String name, Seller seller, Subcategory subCategory){
		super(id,name,seller,subCategory);
	}
	
	public ProductJDBC(String name, Seller seller, float price, int quantity, Subcategory subCategory) {
		super(name,seller,price,quantity,subCategory);
	}

	/*CREATE TABLE product
	(
	  id serial NOT NULL,
	  name character,
	  id_sell serial NOT NULL,
	  name_subcategory character,
	  [...]
	 */
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
				return stm.execute(sql);
			} catch (SQLException e) 
				{e.printStackTrace();}
		}
		System.out.println("produit exist");
		return false;
		
	}
	
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
	public Product readByNameAndSeller(){
		//si le nom ou le seller vide alors Erreur   <--------------------------
		System.out.println(this.name);
		System.out.println(this.seller.getPseudo());
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
	
}
	
	
	
	
	
	
	/*public static void main(String args[]) {
		//ProductJDBC prod = new ProductJDBC("whisky","jack","boisson");
		//prod.save();
	}*/

	


