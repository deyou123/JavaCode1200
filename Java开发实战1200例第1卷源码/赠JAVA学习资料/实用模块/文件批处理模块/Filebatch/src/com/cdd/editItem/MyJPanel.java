package com.cdd.editItem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class MyJPanel extends JPanel {
private BufferedImage bfimage; 
public void	paintComponent(Graphics g){
	try{ 
		File file=new File("src\\image\\back.JPG"); 
		bfimage=ImageIO.read(file); 
		}catch(Exception e){} 
		g.drawImage(bfimage, 0, 0, this); 
	}
}
