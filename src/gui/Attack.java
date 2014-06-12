package gui;
import java.awt.image.BufferedImage;

import list.CircularIterator;


public class Attack extends Renderizable{
	
	private CircularIterator<BufferedImage> iterator;
	private int direction = -1;
	
	public Attack(int x, int y, int height, int width){
		super.x = x;
		super.y = y;
		super.height = height;
		super.width = width;
		iterator = AttackConstants.getInstance().makeIterator();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		y += 10*direction;
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		g.drawImage(iterator.getNext(), x, y, width, height, null);
	}
	
	public void moveToUp(){
		direction = 1;
	}

	public void moveToDown(){
		direction = -1;
	}
}
