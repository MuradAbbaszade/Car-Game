import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Car{
	private int x;
	private int y;
	public int getX(){
		return x;
	}
	public void setX(int x){
		this.x = x;
	}
	public int getY(){
		return y;
	}
	public Car(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public void setY(int y){
		this.y = y;
	}
}
class Line{
	private int x;
	private int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Line(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
public class Game extends JPanel implements KeyListener,ActionListener{
	Random rand= new Random();
	Timer timer = new Timer(40,this);
	private BufferedImage image,image1;
	private int carX;
	
	Car car = new Car(50,-80);
	Car car1 = new Car(230,-480);
	Car car2 = new Car(380,-220);
	Car car3 = new Car(110,-590);
	
	Line line = new Line(230,450);
	Line line1 = new Line(230,50);
	
	public Game() {
		timer.start();
		setBackground(Color.BLACK);
		try {
			image=ImageIO.read(new FileImageInputStream(new File("car1.png")));
			image1=ImageIO.read(new FileImageInputStream(new File("car2.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(line.getX(), line.getY(), 30, 220);
		g.fillRect(line1.getX(), line1.getY(), 30, 220);
		g.drawImage(image,carX,440,image.getWidth()/5,image.getHeight()/5,this);
		g.drawImage(image1,car.getX(),car.getY(),image.getWidth()/6,image.getHeight()/6,this);
		g.drawImage(image1,car1.getX(),car1.getY(),image.getWidth()/6,image.getHeight()/6,this);
		g.drawImage(image1,car2.getX(),car2.getY(),image.getWidth()/6,image.getHeight()/6,this);
		g.drawImage(image1,car3.getX(),car3.getY(),image.getWidth()/6,image.getHeight()/6,this);

	}
	public void repaint() {
		super.repaint();
	}
	public void actionPerformed(ActionEvent e) {
		car.setY(car.getY()+10);
		car1.setY(car1.getY()+10);
		car2.setY(car.getY()+10);
		car3.setY(car1.getY()+10);
		line.setY(line.getY()+10);
		line1.setY(line1.getY()+10);
		if(car.getY()>600) {
			car.setY(-80);
		}
		if(car1.getY()>600) {
			car1.setY(-80);
		}
		if(car2.getY()>600) {
			car2.setY(-80);
		}
		if(car3.getY()>600) {
			car3.setY(-80);
		}
		if(line.getY()>600) {
			line.setY(-350);
		}
		if(line1.getY()>600) {
			line1.setY(-380);
		}
		control();
		repaint();
	}
	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
		int c=e.getExtendedKeyCode();
		if(c==KeyEvent.VK_A){
			carX-=10;
		}
		if(c==KeyEvent.VK_D){
			carX+=10;
		}
		if(carX<5) {
			carX=5;
		}
		if(carX>450) {
			carX=450;
		}
		repaint();
	}
	public void keyReleased(KeyEvent e) {
	}
	public void control() {
			if(new Rectangle(car.getX(),car.getY(),image.getWidth()/6,image.getHeight()/6).intersects(new Rectangle(carX,440,image.getWidth()/6,image.getHeight()/6))
					||new Rectangle(car1.getX(),car1.getY(),image.getWidth()/6,image.getHeight()/6).intersects(new Rectangle(carX,440,image.getWidth()/6,image.getHeight()/6))
					||new Rectangle(car2.getX(),car2.getY(),image.getWidth()/6,image.getHeight()/6).intersects(new Rectangle(carX,440,image.getWidth()/6,image.getHeight()/6))
					||new Rectangle(car3.getX(),car3.getY(),image.getWidth()/6,image.getHeight()/6).intersects(new Rectangle(carX,440,image.getWidth()/6,image.getHeight()/6))) {
				timer.stop();
				JOptionPane.showMessageDialog(this, "Game Over");
				System.exit(0);
			}
		}
	}
