package com.cdd.editItem;

import java.awt.*; 

import javax.swing.*; 
import java.awt.image.BufferedImage; 
import java.io.*; 
import javax.imageio.ImageIO; 
/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class GameFrame extends JFrame{ 
/** 
* @This program is bout of Gamethis 
*/ 
private static final int Width=Toolkit.getDefaultToolkit().getScreenSize().width; 
private static final int Heigth=Toolkit.getDefaultToolkit().getScreenSize().height; 
private JLabel message = new JLabel("�ļ������У����Ժ�");
private JButton button = new JButton("�˳�");
private BufferedImage bfimage; 
private JPanel jpan = new JPanel();
public GameFrame(){ 
	this.setLayout(null);
	message.setBounds(0, 10, 50, 20);
	this.add(message);
	
this.setTitle("���屳��"); 
this.setResizable(false); 
this.setSize(200,200); 
this.setLocation(Width-800, Heigth-700); 
this.setDefaultCloseOperation(this.EXIT_ON_CLOSE); 
this.setVisible(true); 
this.add(jpan);
} 
public void paint(Graphics g){ 
//���뱳��ͼƬ 
try{ 
File file=new File("src\\image\\back.JPG"); 
bfimage=ImageIO.read(file); 
}catch(Exception e){} 
g.drawImage(bfimage, 0, 30, this); 
} 
public static void main(String[] args) { 
new GameFrame(); 
} 

}
