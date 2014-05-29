import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import list.CircularIterator;
import list.CircularList;
import list.DListIterator;
import list.DoubleList;


public class Manager extends Renderizable implements Runnable{
	private DoubleList<Attack> attacks = new DoubleList<>();
	private DoubleList<Rocket> enemys = new DoubleList<>();
	private int halfx = 360;
	private int halfy = 380;
	private Rocket actor = new Rocket(halfx, halfy, false);
	private boolean exec = true;
	
	private static BufferedImage bg1;
	private static BufferedImage bg2;
	private static BufferedImage bg3;
	
	private JFrame f;
	
	private int shootcount = 4;
	
	boolean shoot,left, right;
	
	private CircularList<BufferedImage> background;
	private CircularIterator<BufferedImage> backgroundIterator;
	
	
	
	@SuppressWarnings("static-access")
	public Manager(Graphics g, JFrame frame) {
		f = frame;
		// TODO Auto-generated constructor stub
		super.g= g;
		background = new CircularList<>();
		try {
			bg1 = ImageIO.read(Manager.class.getResource("/gallery/bg1.png"));
			bg2 = ImageIO.read(Manager.class.getResource("/gallery/bg2.png"));
			bg3 = ImageIO.read(Manager.class.getResource("/gallery/bg3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "Recursos no encontrados:\n"
					+ "revise el fichero de instalacion y verifique\n"
					+ " que existen las tres imagenes del path de rocket.");
			System.exit(0);
		}
		BufferedImage[] bg = {bg1,bg2,bg3};
		for(int x = 0; x<3;x++){
			for (int y=0; y<5;y++){
				background.add(bg[x]);
			}
		}
		backgroundIterator = background.getIterator();
		width = 720;
		height = 480;
		x = y = 100;
	}
	
	private void addShoot(){
		if(shootcount == 0){
			attacks.add(new Attack(actor.x, actor.y, 
			AttackConstants.ATTACK1.getWidth(),	AttackConstants.ATTACK1.getHeight()));
			shootcount = 5;
		}
		shootcount--;
	}
	
	private void updateAttacks(){
		DListIterator<Attack> attacksIterator = attacks.getIterator();
		for (int x = 0; x<attacks.getLenght();x++){
			attacksIterator.getNext().update();
			/*attacksIterator.actual().update();
			System.out.println("attacks: " + );
			if (!getRectangle().intersects(attacksIterator.getNext().getRectangle())){
				attacks.remove(x);
				x--;
			}
			*/
		}
	}
	
	private void updateEnemys(){
		DListIterator<Rocket> enemysIterator = enemys.getIterator();
		for (int x = 0; x<enemys.getLenght();x++){
			enemysIterator.getNext().update();
			/*enemysIterator.actual().update();
			if (!getRectangle().intersects(enemysIterator.getNext().getRectangle())){
				enemys.remove(x);
				x--;
			}
			*/
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(left){actor.moveToLeft();}
		else if(right){actor.moveToRight();}
		else{actor.dontmove();}
		if(shoot){addShoot();}
		updateAttacks();
		updateEnemys();
		actor.update();
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		g = f.getGraphics();
		Toolkit.getDefaultToolkit().sync();
		g.drawImage(backgroundIterator.getNext(), 0, 0, null);
		DListIterator<Attack> attacksIterator = attacks.getIterator();
		DListIterator<Rocket> enemysIterator = enemys.getIterator();
		for (int x = 0; x<attacks.getLenght();x++){
			attacksIterator.getNext().render();
		}
		for (int x = 0; x<enemys.getLenght();x++){
			enemysIterator.getNext().render();
		}
		actor.render();
		g.dispose();
	}
	public void sleep() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			JOptionPane.showConfirmDialog(null, "Ha Ocurrido un Error");
			System.exit(0);
		}

	}
	
	public void poweroff(){
		exec = false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(exec){
			update();
			render();
			sleep();
		}
	}

}
