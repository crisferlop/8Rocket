import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;



public class Interfaz extends JFrame implements WindowListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Manager manager;
	private Thread man;
	
	public Interfaz() {
		// TODO Auto-generated constructor stub
		setResizable(false);
		setVisible(true);
		manager = new Manager(this.getGraphics(),this);
		this.setBounds(manager.getX(), manager.getY(), manager.getWidth(), manager.getHeight());
		
		man = new Thread(manager);
		man.start();
		addKeyListener(new Controls(manager));
		this.setIgnoreRepaint(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(this);
	}
	
	
	public static void main(String[] args) {
		Interfaz in = new Interfaz();
		in.setVisible(true);
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@SuppressWarnings("deprecation")
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		manager.stop();
		man.stop();
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
