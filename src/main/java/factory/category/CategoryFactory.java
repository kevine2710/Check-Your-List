package factory.category;

import model.category.Category;
import model.category.Subcategory;
import model.category.JDBC.CategoryJDBC;
import model.category.JDBC.SubcategoryJDBC;

/**
 * to be the intermediary between CategoryJDBC and AbstractCategoryFactory 
 *
 */
public class CategoryFactory extends AbstractCategoryFactory {

	@Override
	public Category createCategory(String pseudo, String shortDescription, String detailedDescription) {
		return new CategoryJDBC(pseudo, shortDescription, detailedDescription);
	}

	@Override
	public Category createCategory(String pseudo) {
		return new CategoryJDBC(pseudo);
	}
	
	@Override
	public Subcategory createSubcategory(String pseudo, String shortDescription, String detailedDescription, Category category) {
		return new SubcategoryJDBC(pseudo, shortDescription, detailedDescription, category);
	}

	@Override
	public Subcategory createSubcategory(String pseudo) {
		return new SubcategoryJDBC(pseudo);
	}

	@Override
	public Category createCategory() {
		return new CategoryJDBC();
	}
	
	@Override
	public Subcategory createSubcategory() {
		return new SubcategoryJDBC();
	}

	@Override
	public Subcategory createSubcategory(Category c) {
		return new SubcategoryJDBC(c);
	}

}
