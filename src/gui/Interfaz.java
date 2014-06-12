package gui;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


class Event8Rocket extends WindowAdapter{
	private Manager manager;
	private Thread man;
	
		public Event8Rocket(Manager pmanager, Thread pman) {
			// TODO Auto-generated constructor stub
			manager = pmanager;
			man = pman;
		}
	@SuppressWarnings("deprecation")
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		manager.stop();
		man.stop();
	}

}


public class Interfaz extends JFrame{

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
		addWindowListener(new Event8Rocket(manager, man));
	}
	
	
	public static void main(String[] args) {
		Interfaz in = new Interfaz();
		in.setVisible(true);
	}

}
