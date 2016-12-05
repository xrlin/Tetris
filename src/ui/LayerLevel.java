package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerLevel extends Layer {
	
	/**
	 * “等“级字符串图片
	 */
	private static final Image LEVEL_IMG = new ImageIcon("graphics/string/level.png").getImage();
	/**
	 *  所要绘制的数值的最大位数
	 */
	private static final int MAX_WEI = 3;
	/**
	 * 数字相对所在的Layer的左上角的偏移量
	 */
	private static final int NUM_X = 25;
	/**
	 * 数字相对所在的Layer的左上角的偏移量
	 */
	private static final int NUM_Y = 80;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */

	public LayerLevel(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		//绘制layer的标题
		g.drawImage(LEVEL_IMG, this.x+PADDING + BORDER_SIZE, this.y+PADDING + BORDER_SIZE, null);
		this.drawNum(this.NUM_IMG,MAX_WEI,this.dto.getLevel(),NUM_X,NUM_Y,g);
	}

}
