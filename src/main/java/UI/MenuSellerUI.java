package UI;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.person.Seller;

<<<<<<< HEAD
// TODO: Auto-generated Javadoc
/**
 * The Class MenuSellerUI.
 */
=======
@SuppressWarnings("serial")
>>>>>>> origin/master
public class MenuSellerUI extends JFrame implements ChangeListener {
	
	/** The tabbed pane. */
	private JTabbedPane tabbedPane;
	
	/** The seller. */
	private Seller seller;
	
	/** The fs. */
	private ProfilSellerUI fs;
	
	/** The cp. */
	private CreateProductUI cp;
	
	/** The shop. */
	private ShopUI shop;

	/**
	 * Create the frame.
	 *
	 * @param s the s
	 */
	public MenuSellerUI(Seller s) {
		setResizable(false);
		this.seller = s;
		getContentPane().setLayout(null);
		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.tabbedPane.addChangeListener(this);
		this.setMinimumSize(new Dimension(400, 300));
		this.setLocationRelativeTo(null);
		getContentPane().add(tabbedPane);
		this.initFrame();
		
		
		
	}
	
	/**
	 * Inits the frame.
	 */
	public void initFrame() {
		this.fs = new ProfilSellerUI(this.seller);	
		Component comp = fs.getContentPane();
		this.setSize(fs.getWidth() + 15, fs.getHeight() + 40);
		tabbedPane.setSize(fs.getWidth(), fs.getHeight());
		tabbedPane.addTab("Profil", comp);
		
		this.cp = new CreateProductUI(this.seller);
		Component compCreateProd = cp.getContentPane();
		tabbedPane.addTab("Create product", compCreateProd);
		
		this.shop = new ShopUI();
		Component compShop = shop.getContentPane();
		tabbedPane.addTab("Shop", compShop);
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent c) {
		JTabbedPane pane = (JTabbedPane) c.getSource();
		String panneName = pane.getTitleAt(pane.getSelectedIndex());
		if (panneName.equals("Profil")) {
			this.fs.dispose();
			this.fs.initFrame();
			this.setSize(fs.getWidth(), fs.getHeight() + 40);
			tabbedPane.setSize(fs.getWidth(), fs.getHeight());
		}
		else if (panneName.equals("Create product")) {
			this.cp.dispose();
			this.setSize(cp.getWidth() + 15, cp.getHeight() + 30);
			tabbedPane.setSize(cp.getWidth(), cp.getHeight());
		}
		else if (panneName.equals("Shop")) {
			this.shop.dispose();
			this.shop.initFrame();
			this.setSize(shop.getWidth() + 15, shop.getHeight() + 30);
			tabbedPane.setSize(shop.getWidth(), shop.getHeight());
		}
	}

}
