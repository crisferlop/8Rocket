import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class Renderizable {
	protected int x,y,height,width;
	protected Rectangle rectangle;
	protected static Graphics g;

	public abstract void update();
	public abstract void render();
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	
	public Rectangle getRectangle(){
		return rectangle;
	}
	public boolean colision(Renderizable prenderizable){
		return rectangle.intersects(prenderizable.getRectangle());
	}
}
