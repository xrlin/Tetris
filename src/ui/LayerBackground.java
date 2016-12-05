package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class LayerBackground extends Layer {
	/**
	 * 背景图片的数组的长度
	 */
	private static final int IMG_LIST_LENGTH = 10;
	/*
	 * 背景 图片 数组
	 */
	//private static final Image BG_IMG = new ImageIcon("graphics/background/0.jpg").getImage();
	/**
	 * 存储背景图片的数组
	 */
	private static List<Image> bgList;
	public LayerBackground(int x, int y, int width, int height) {
		super(x, y, width, height);
		bgList = new ArrayList<Image>();
		//将背景图片添加进bglist中
		for(int i=0; i<IMG_LIST_LENGTH; i++){
			bgList.add(new ImageIcon("graphics/background/" + i + ".jpg").getImage());
		}
	}


	@Override
	public void paint(Graphics g) {
		//背景图片的index
		int idx = this.dto.getLevel() % 10;
		g.drawImage(bgList.get(idx), x, y,x+width,y+width, null);
	}

}
