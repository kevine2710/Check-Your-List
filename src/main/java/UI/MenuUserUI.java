package UI;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.person.User;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuUserUI.
 */
@SuppressWarnings("serial")
public class MenuUserUI extends JFrame implements ChangeListener {
	
	/** The tabbed pane. */
	private JTabbedPane tabbedPane;
	
	/** The user. */
	private User user;
	
	/** The fa. */
	private AllActivitiesUI fa;
	
	/** The fp. */
	private ProfilUserUI fp;
	
	/** The fc. */
	private CreateActivityUI fc;
	
	/** The fs. */
	private ShopUI fs;

	/**
	 * Create the frame.
	 *
	 * @param p the p
	 */
	public MenuUserUI(User p) {
		setResizable(false);
		this.user = p;
		getContentPane().setLayout(null);
		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.tabbedPane.addChangeListener(this);
		getContentPane().add(tabbedPane);
		this.initFrame();
	}
	
	/**
	 * Inits the frame.
	 */
	public void initFrame() {
		fp = new ProfilUserUI(this.user);
		Component comp = fp.getContentPane();
		this.setSize(fp.getWidth() + 15, fp.getHeight() + 30);
		tabbedPane.setSize(fp.getWidth(), fp.getHeight());
		tabbedPane.addTab("Profil", comp);
		fc = new CreateActivityUI(this.user);
		comp = fc.getContentPane();
		tabbedPane.addTab("Create activity", comp);
		
		fa = new AllActivitiesUI(this.user);
		comp = fa.getContentPane();
		tabbedPane.addTab("All activities", comp);
		
		fs = new ShopUI();
		comp = fs.getContentPane();
		tabbedPane.addTab("Shop", comp);
		this.setLocationRelativeTo(null);
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent c) {
		JTabbedPane pane = (JTabbedPane) c.getSource();
		String panneName = pane.getTitleAt(pane.getSelectedIndex());
		if (panneName.equals("Profil")) {
			fp.initFrame();
			this.setSize(fp.getWidth() + 15, fp.getHeight() + 30);
			tabbedPane.setSize(fp.getWidth(), fp.getHeight());
		}
		else if (panneName.equals("Create activity")) {
			this.setSize(fc.getWidth() + 15, fc.getHeight() + 30);
			tabbedPane.setSize(fc.getWidth(), fc.getHeight());
		}
		else if (panneName.equals("All activities")) {
			fa.initFrame();
			this.setSize(fa.getWidth() + 15, fa.getHeight() + 30);
			tabbedPane.setSize(fa.getWidth(), fa.getHeight());
		}
		else if (panneName.equals("Shop")) {
			fs.initFrame();
			this.setSize(fs.getWidth() + 15, fs.getHeight() + 30);
			tabbedPane.setSize(fs.getWidth(), fs.getHeight());
		}
	}
}
