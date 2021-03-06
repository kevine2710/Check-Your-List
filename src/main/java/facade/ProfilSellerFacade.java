package facade;

import java.util.List;

import manager.ProductManager;
import model.person.Seller;
import model.product.Product;

/**
 * to be the intermediary between ProfilSellerManager and UI seller class
 *
 */
public class ProfilSellerFacade {
	
	/**
	 * Description of the property sellerUIs.
	 */
	private ProductManager pm;
	
	/**
	 * constructor of ProfilSellerFacade
	 */
	public ProfilSellerFacade() {
		this.pm = new ProductManager();
	}
	
	/**
	 * get all products of a seller
	 * @param seller
	 * @return list of products
	 */
	public List<Product> getAllProduct(Seller s) {
		return pm.getAllProductBySeller(s);
	}
	
	/**
	 * delete a product
	 * @param product
	 * @return true if the product is deleted
	 */
	public boolean deleteProduct(Product prod) {
		return pm.deleteProduct(prod);
	}

	/**
	 * update quantity of product
	 * @param product
	 * @param quantity
	 * @return true if the quantity is updated else false
	 */
	public boolean majQuantity(Product prod, int qty) {
		return pm.majQuantity(prod,qty);	
	}

	/**
	 * update price of product
	 * @param prod product
	 * @param price
	 * @return true if the price is updated else false
	 */
	public boolean majPrice(Product prod, float price) {
		return pm.majPrice(prod,price);	
	}
	
	/**
	 * update name of product
	 * @param prod
	 * @param name
	 * @return true if the name is updated else false
	 */
	public boolean majName(Product prod, String name) {
		return pm.majName(prod,name);	
	}
	
}
