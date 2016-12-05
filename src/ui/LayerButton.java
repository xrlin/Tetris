package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class LayerButton extends Layer {
	
	/**
	 *  暂停按钮图片
	 */
	private static final ImageIcon Config_IMG = new ImageIcon("graphics/string/config.png");
	/**
	 * 暂停按钮左上角x坐标
	 */
	private  final int CONFIG_IMG_X;
	/**
	 * 暂停按钮左上角y坐标
	 */
	private  final int CONFIG_IMG_Y;
	/**
	 * 开始按钮图片
	 */
	private  final ImageIcon START_IMG = new ImageIcon("graphics/string/start.png");
	/**
	 * 开始按钮左上角x坐标
	 */
	private final int START_IMG_X;
	/**
	 * 开始按钮左上角y坐标
	 */
	private  final int START_IMG_Y;
	/**
	 * LayerButton构造函数
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public LayerButton(int x, int y, int width, int height) {
		super(x, y, width, height);
		//间隔
		int spa = (this.width - Config_IMG.getIconWidth() - START_IMG.getIconWidth()) / 3;
		this.START_IMG_X = this.x + this.PADDING + this.BORDER_SIZE +spa;
		this.CONFIG_IMG_X = this.START_IMG_X + START_IMG.getIconWidth() + spa;
		this.CONFIG_IMG_Y = this.y + (this.height - Config_IMG.getIconHeight() >>1);
		this.START_IMG_Y = this.y + (this.height - START_IMG.getIconHeight() >>1);
//		JButton jb = new JButton("sdfsdfsdf");
//		jb.setBounds(0, 0, 50, 50);
//		add(jb);
	}

	@Override
	public void paint(Graphics g) {

		this.createWindow(g);
		//g.drawImage(START_IMG.getImage(), this.START_IMG_X, this.START_IMG_Y, null);
		//g.drawImage(Config_IMG, CONFIG_IMG_X, CONFIG_IMG_Y, null);
	}

}
