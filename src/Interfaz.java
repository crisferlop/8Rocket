import javax.swing.JFrame;


public class Interfaz extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Manager manager;
	
	public Interfaz() {
		// TODO Auto-generated constructor stub
		this.setIgnoreRepaint(true);
		setResizable(false);
		setVisible(true);
		manager = new Manager(this.getGraphics(),this);
		this.setBounds(manager.getX(), manager.getY(), manager.getWidth(), manager.getHeight());
		Thread man = new Thread(manager);
		man.start();
		addKeyListener(new Controls(manager));
		
	}
	
	
	public static void main(String[] args) {
		Interfaz in = new Interfaz();
		in.setVisible(true);
	}

}
