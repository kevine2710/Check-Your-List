package UI;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import facade.TaskFacade;
import model.product.Product;
import model.task.Task;

<<<<<<< HEAD
// TODO: Auto-generated Javadoc
/**
 * The Class TaskUI.
 */
=======
@SuppressWarnings("serial")
>>>>>>> origin/master
public class TaskUI extends JFrame {
	
	/** The task. */
	private Task task;
	
	/** The tf. */
	private TaskFacade tf;
	
	/** The panel products. */
	private JPanel panelProducts;
<<<<<<< HEAD
	
	/**
	 * Instantiates a new task ui.
	 *
	 * @param task the task
	 */
	/*
	public static void main(String args[]) {
		TaskUI.launch();
	}
=======
>>>>>>> origin/master

	public TaskUI(Task task) {
		this.task = task;
		this.tf = new TaskFacade();
		getContentPane().setLayout(null);
		
		JLabel titleTask = new JLabel(this.task.getName());
		titleTask.setHorizontalAlignment(SwingConstants.CENTER);
		titleTask.setFont(new Font("Tahoma", Font.BOLD, 18));
		titleTask.setBounds(10, 11, 414, 22);
		getContentPane().add(titleTask);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setBounds(10, 75, 414, 14);
		getContentPane().add(lblDescription);
		
		JLabel txtDescription = new JLabel(this.task.getDescription());
		txtDescription.setHorizontalAlignment(SwingConstants.CENTER);
		txtDescription.setBounds(10, 100, 414, 22);
		getContentPane().add(txtDescription);
		
		JLabel lblNewLabel_1 = new JLabel("Frequency");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 133, 414, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel frqText = new JLabel(this.task.getFrequency());
		frqText.setHorizontalAlignment(SwingConstants.CENTER);
		frqText.setBounds(10, 158, 414, 14);
		getContentPane().add(frqText);
		
		JLabel lblPeriod = new JLabel("From " + this.task.getStartDate() + " to " + this.task.getEndDate());
		lblPeriod.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeriod.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPeriod.setBounds(10, 44, 414, 14);
		getContentPane().add(lblPeriod);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 202, 414, 117);
		getContentPane().add(scrollPane);
		
		panelProducts = new JPanel();
		scrollPane.setViewportView(panelProducts);
		panelProducts.setLayout(null);
		setSize(449,369);
		this.initRessources(this.tf.getAllProductFromTask(this.task));
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * Inits the ressources.
	 *
	 * @param list the list
	 */
	public void initRessources(List<Product> list){
		int idx =0;
		panelProducts.removeAll();
		for (Product p : list){
			addPanelProduct(p,idx);
			idx++;
		}
		panelProducts.setPreferredSize(new Dimension(390, 30 * (list.size() )));
		panelProducts.repaint();
	}

	/**
	 * Adds the panel product.
	 *
	 * @param p the p
	 * @param idx the idx
	 */
	public void addPanelProduct(Product p, int idx){
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0 + (30 * idx), 412, 41);
		panelProducts.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTitleRessource = new JLabel(p.getName());
		lblTitleRessource.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitleRessource.setBounds(10, 16, 130, 14);
		panel_1.add(lblTitleRessource);
		
		JLabel quantity = new JLabel(p.getQuantity() +" pcs");
		quantity.setBounds(167, 17, 96, 14);
		panel_1.add(quantity);
		
		JLabel price = new JLabel(p.getPrice()+" �");
		price.setBounds(285, 17, 117, 14);
		panel_1.add(price);
	}
}
