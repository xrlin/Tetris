package ui;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerPoint extends Layer {

	/**
	 *  分数值与消行的最大位数
	 */
	private static final int MAX_WEI = 5;
	/**
	 * 分数数值的左上角X偏移量
	 */
	private int pointX;
	/**
	 * 分数数值的左上角Y偏移量
	 */
	private int pointY;
	/**
	 * 分数图片
	 */
	private static final Image POINT_IMG = new ImageIcon("graphics/string/point.png").getImage();
	/**
	 * 消行图片
	 */
	private static final Image RMLINE_IMG = new ImageIcon("graphics/string/rmline.png").getImage();
	/**
	 * 消行数值的左上角X偏移量
	 */
	private int rmlineX = this.width-(this.NUM_IMG.getWidth(null)>>1)-this.PADDING;
	/**
	 * 消行数值的左上角Y偏移量
	 */
	private int rmlineY = RMLINE_IMG.getHeight(null)+ 32;;
	
	/**
	 * 分数数值的左上角X偏移量
	 */
	public LayerPoint(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.pointX = this.width-(this.NUM_IMG.getWidth(null)>>1)- this.BORDER_SIZE - this.PADDING ;
		this.pointY = this.PADDING + this.BORDER_SIZE;
		this.rmlineX = this.width-(this.NUM_IMG.getWidth(null)>>1) -this.BORDER_SIZE - this.PADDING ;
		this.rmlineY = RMLINE_IMG.getHeight(null)+ 32;
		this.expY = rmlineY + this.RMLINE_IMG.getHeight(null) + 40 ;
	}

	@Override
	public void paint(Graphics g) {
		//TODO 绘制分数窗口及内容 硬编码
		this.createWindow(g);
		//绘制分数
		g.drawImage(POINT_IMG, this.x+this.BORDER_SIZE+PADDING, this.y+PADDING+this.BORDER_SIZE, null);
		this.drawNum(this.NUM_IMG, MAX_WEI, this.dto.getNowPoint(), pointX, pointY, g);
		//绘制消行
		g.drawImage(RMLINE_IMG, this.x+ PADDING + this.BORDER_SIZE, this.y+RMLINE_IMG.getHeight(null)+ 32, null);
		this.drawNum(this.NUM_IMG, MAX_WEI, this.dto.getNowRemoveRow(), this.rmlineX, this.rmlineY, g);
		//绘制值槽
		this.drawExpRect(this.expY,"NEXT", "", this.dto.getNowPoint(),g);
	}
}
