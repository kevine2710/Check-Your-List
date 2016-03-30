package facade;

import java.util.List;

import manager.ProductManager;
import model.person.Seller;
import model.product.Product;

public class ProfilSellerFacade {
	
	private ProductManager pm;
	
	public ProfilSellerFacade() {
		this.pm = new ProductManager();
	}
	
	public List<Product> getAllProduct(Seller s) {
		return pm.getAllProductBySeller(s);
	}
	
	public boolean deleteProduct(Product prod) {
		return pm.deleteProduct(prod);
	}

	public boolean majQuantity(Product prod, int qty) {
		return pm.majQuantity(prod,qty);	
	}

	public boolean majPrice(Product prod, float price) {
		return pm.majPrice(prod,price);	
	}
	
	public boolean majName(Product prod, String name) {
		return pm.majName(prod,name);	
	}
	
}
