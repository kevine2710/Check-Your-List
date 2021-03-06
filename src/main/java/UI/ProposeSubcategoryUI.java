package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import facade.CategoryFacade;
import model.category.Category;

<<<<<<< HEAD
// TODO: Auto-generated Javadoc
/**
 * The Class ProposeSubcategoryUI.
 */
=======
@SuppressWarnings("serial")
>>>>>>> origin/master
public class ProposeSubcategoryUI extends JFrame implements ActionListener {

	/** The cf. */
	private CategoryFacade cf;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The category name. */
	private JTextField categoryName;
	
	/** The text short description. */
	private JTextPane textShortDescription;
	
	/** The text detailed description. */
	private JTextPane textDetailedDescription;
<<<<<<< HEAD
	
	/** The combo box category. */
	private JComboBox comboBoxCategory;
	
	/** The list category. */
=======
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxCategory;
	@SuppressWarnings("unused")
>>>>>>> origin/master
	private List<Category> listCategory;
	
	/** The create product ui. */
	private CreateProductUI createProductUI;

	/**
	 * Launch the application.
	 *
	 * @param createProductUI the create product ui
	 */


	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public ProposeSubcategoryUI(CreateProductUI createProductUI) {
		this.cf = new CategoryFacade();
		this.createProductUI = createProductUI;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblCategoryName = new JLabel("Subcategory name :");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblCategoryName, 48, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblCategoryName, 20, SpringLayout.WEST, contentPane);
		contentPane.add(lblCategoryName);
		
		categoryName = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, categoryName, 6, SpringLayout.SOUTH, lblCategoryName);
		sl_contentPane.putConstraint(SpringLayout.WEST, categoryName, 20, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, categoryName, -15, SpringLayout.EAST, contentPane);
		contentPane.add(categoryName);
		categoryName.setColumns(10);
		
		JButton btnProposeNewCategory = new JButton("Propose new subcategory");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnProposeNewCategory, 108, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnProposeNewCategory, -10, SpringLayout.SOUTH, contentPane);
		btnProposeNewCategory.addActionListener(this);
		contentPane.add(btnProposeNewCategory);
		
		JLabel lblShortDescription = new JLabel("Short description : ");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblShortDescription, 0, SpringLayout.WEST, lblCategoryName);
		contentPane.add(lblShortDescription);
		
		JLabel lblDetailedDescription = new JLabel("Detailed Description :");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDetailedDescription, 0, SpringLayout.WEST, lblCategoryName);
		contentPane.add(lblDetailedDescription);
		
		this.textShortDescription = new JTextPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textShortDescription, 236, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textShortDescription, -25, SpringLayout.NORTH, lblDetailedDescription);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblShortDescription, -7, SpringLayout.NORTH, textShortDescription);
		sl_contentPane.putConstraint(SpringLayout.WEST, textShortDescription, 0, SpringLayout.WEST, lblCategoryName);
		sl_contentPane.putConstraint(SpringLayout.EAST, textShortDescription, -15, SpringLayout.EAST, contentPane);
		contentPane.add(textShortDescription);
		
		this.textDetailedDescription = new JTextPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textDetailedDescription, 427, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textDetailedDescription, -38, SpringLayout.NORTH, btnProposeNewCategory);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblDetailedDescription, -6, SpringLayout.NORTH, textDetailedDescription);
		sl_contentPane.putConstraint(SpringLayout.WEST, textDetailedDescription, 0, SpringLayout.WEST, lblCategoryName);
		sl_contentPane.putConstraint(SpringLayout.EAST, textDetailedDescription, 0, SpringLayout.EAST, categoryName);
		contentPane.add(textDetailedDescription);
		
		this.comboBoxCategory = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBoxCategory, 0, SpringLayout.WEST, lblCategoryName);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboBoxCategory, 0, SpringLayout.EAST, categoryName);
		@SuppressWarnings("unused")
		List<String> ls = new ArrayList<String>();
		contentPane.add(comboBoxCategory);
		this.initComboBoxCategory();
		
		//comboBoxCategory.addItem("ok");
		
		JLabel lblCategory = new JLabel("Category :");
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboBoxCategory, 11, SpringLayout.SOUTH, lblCategory);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblCategory, 23, SpringLayout.SOUTH, categoryName);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblCategory, 0, SpringLayout.WEST, lblCategoryName);
		contentPane.add(lblCategory);
		
		setSize(447,694);
		setResizable(false);
		setTitle("Propose new subcategory");
	}
	
	/**
	 * Inits the combo box category.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initComboBoxCategory() {
		List<Category> list = cf.getAllCategories();
		this.comboBoxCategory.setModel(new DefaultComboBoxModel(list.toArray()));
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		if (this.formComplete()) {
			if(!cf.subcategoryExist(this.categoryName.getText())) {
				if(!cf.categoryExist(this.categoryName.getText())) {
					this.cf.createSubcategory(this.categoryName.getText(), this.textShortDescription.getText(),
							this.textDetailedDescription.getText(),(Category) this.comboBoxCategory.getSelectedItem());
					this.createProductUI.detectCategoryClose();
					this.dispose();
					JOptionPane.showMessageDialog(this,
						    "You have created your product.");
				}
				else {
					JOptionPane.showMessageDialog(this,
							"This subcategory name is already the name of one category",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(this,
						"This subcategory already exist",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Form complete.
	 *
	 * @return true, if successful
	 */
	public boolean formComplete() {
		if (this.categoryName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"The category name field is empty",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if (this.textShortDescription.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"The short description field is empty",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if (this.textDetailedDescription.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"The detailed description field is empty",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	
}
