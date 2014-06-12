package gui;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class Rocket extends Renderizable{
	private BufferedImage sprite;
	private int direction;
	public static final String PATH = "/gallery/rocket.png";
	public static final String PATH_BAD  = "/gallery/enemy.png";
	
	
	public Rocket(int x, int y, boolean bad){
		String path = bad? PATH_BAD:PATH;
		try {
			sprite = ImageIO.read(Rocket.class.getResource(path));
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(null, "Recursos no encontrados:\n"
					+ "revise el fichero de instalacion y verifique\n"
					+ " que existen las tres imagenes del path de rocket.");
			System.exit(0);
		}
		super.x = x;
		super.y = y;
		super.height = sprite.getHeight();
		super.width = sprite.getWidth();
	}
	public void moveToLeft(){
		direction = -1;
	}
	public void moveToRight(){
		direction = 1;
	}
	public void dontmove(){
		direction = 0;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		x+= 5*direction;
	}
	@Override
	public void render() {
		// TODO Auto-generated method stub
		g.drawImage(sprite, x, y, width, height, null);
	}
}
