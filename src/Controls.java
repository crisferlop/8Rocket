

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Controls implements KeyListener{
	private Manager manager;
	
	public Controls(Manager pmanager){
		manager = pmanager;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			manager.left = true;
			break;
		case KeyEvent.VK_RIGHT:
			manager.right = true;
			break;
		case KeyEvent.VK_Z:
			manager.shoot = true;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_Z:
			manager.shoot = false;
			break;
		case KeyEvent.VK_LEFT:
			manager.left = false;
			break;
		case KeyEvent.VK_RIGHT:
			manager.right = false;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
