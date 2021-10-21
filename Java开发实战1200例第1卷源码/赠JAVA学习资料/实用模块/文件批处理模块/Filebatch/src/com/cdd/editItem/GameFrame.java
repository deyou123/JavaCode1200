package com.cdd.editItem;

import java.awt.*; 

import javax.swing.*; 
import java.awt.image.BufferedImage; 
import java.io.*; 
import javax.imageio.ImageIO; 
/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class GameFrame extends JFrame{ 
/** 
* @This program is bout of Gamethis 
*/ 
private static final int Width=Toolkit.getDefaultToolkit().getScreenSize().width; 
private static final int Heigth=Toolkit.getDefaultToolkit().getScreenSize().height; 
private JLabel message = new JLabel("文件复制中，请稍后");
private JButton button = new JButton("退出");
private BufferedImage bfimage; 
private JPanel jpan = new JPanel();
public GameFrame(){ 
	this.setLayout(null);
	message.setBounds(0, 10, 50, 20);
	this.add(message);
	
this.setTitle("窗体背景"); 
this.setResizable(false); 
this.setSize(200,200); 
this.setLocation(Width-800, Heigth-700); 
this.setDefaultCloseOperation(this.EXIT_ON_CLOSE); 
this.setVisible(true); 
this.add(jpan);
} 
public void paint(Graphics g){ 
//加入背景图片 
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
