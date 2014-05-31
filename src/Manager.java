import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import list.CircularIterator;
import list.CircularList;
import list.DListIterator;
import list.DoubleList;


class FPS extends Renderizable{
	private float fps;
	private Manager man;
	private DecimalFormat decimalFormat = new DecimalFormat("0.00");
	
	public FPS(Manager pmanager) {
		// TODO Auto-generated constructor stub
		man = pmanager;
		x = 50;
		y = 50;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		fps = (man.getLastTime() - man.getFirstTime())/1000000;
		fps = 1000/fps;
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.drawString(decimalFormat.format(fps) + " fps", x, y);
	}
	
}

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
	
	private long firstTime;
	private long lastTime;
	private FPS fps;
	private Graphics doublebuffer;
	private Image buffer;
	public final long TIME_WAIT = 17;
	
	
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
		fps.update();
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Toolkit.getDefaultToolkit().sync();
		buffer = f.createImage(getWidth(), getHeight());
		doublebuffer = f.getGraphics();
		
		g = buffer.getGraphics();
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
		fps.render();
		doublebuffer.drawImage(buffer, 0, 0, null);
		doublebuffer.dispose();
		g.dispose();
	}
	
	
	public void sleep() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(TIME_WAIT);
		} catch (InterruptedException e) {
			JOptionPane.showConfirmDialog(null, "Ha Ocurrido un Error");
			System.exit(0);
		}

	}
	
	public void poweroff(){
		exec = false;
	}

	public void iniciarFPS(){
		fps = new FPS(this);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		fps = new FPS(this);
		while(exec){
			lastTime = System.nanoTime();
			update();
			render();
			firstTime = System.nanoTime();
			sleep();
		}
	}
	
	public void stop(){
		System.out.println("El sistema ha sido cerrado!");
		exec = false;
	}
	
	public long getFirstTime(){
		return firstTime;
	}
	public long getLastTime(){
		return lastTime;
	}
}
