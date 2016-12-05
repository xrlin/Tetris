package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerAbout extends Layer {
	
	/*
	 * 作者信息图片
	 */
	private static final Image SIGN_IMG = new ImageIcon("graphics/string/sign.png").getImage();

	public LayerAbout(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g) {
		//TODO 绘制窗口及内容
		this.createWindow(g);
		this.drawImgAtCenter(this.x, this.y, this.width, this.height, SIGN_IMG, g);
	}

}
