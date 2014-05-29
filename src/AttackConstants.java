import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import list.CircularIterator;
import list.CircularList;


public class AttackConstants {
	
	public static String PATH_ATTACK_1 = "gallery/attack1.png";
	public static String PATH_ATTACK_2 = "gallery/attack2.png";
	public static String PATH_ATTACK_3 = "gallery/attack3.png";
	public static BufferedImage ATTACK1;
	public static BufferedImage ATTACK2;
	public static BufferedImage ATTACK3;
	private CircularList<BufferedImage> attacks = new CircularList<>();
	private static AttackConstants instance = new AttackConstants();
	
	private AttackConstants(){
		try {
			ATTACK1 = ImageIO.read(AttackConstants.class.getResource(PATH_ATTACK_1));
			ATTACK2 = ImageIO.read(AttackConstants.class.getResource(PATH_ATTACK_2));
			ATTACK3 = ImageIO.read(AttackConstants.class.getResource(PATH_ATTACK_3));
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(null, "Recursos no encontrados:\n"
					+ "revise el fichero de instalacion y verifique\n"
					+ " que existen las tres imagenes del path de ataque.");
			System.exit(0);
		}
		BufferedImage[] ataques = {ATTACK1,ATTACK2, ATTACK3};
		for (int x = 0; x < 3; x++){
			for (int y=0; y < 5; y++){
				attacks.add(ataques[x]);
			}
		}
	}
	
	public static AttackConstants getInstance(){
		return instance;
	}
	
	public CircularIterator<BufferedImage> makeIterator(){
		return attacks.getIterator();
	}
	
}
