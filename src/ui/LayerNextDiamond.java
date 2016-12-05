package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerNextDiamond extends Layer {

	/*
	 * “下一个”图片
	 */
	private Image NEXT_ACT_IMG;
	public LayerNextDiamond(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	@Override
	public void paint(Graphics g) {
		//TODO 绘制下一个方块的窗口及内容
		this.createWindow(g);
		//g.drawImage(NEXT_IMG, this.x+PADDING, this.y+PADDING, null);
		drawNextDiamond(g);
	}
	/**
	 * 绘制下一个方块的图片
	 * @param g
	 */
	public void drawNextDiamond(Graphics g){
		if(!this.dto.isStart())
			return ;
		int nextIndex = this.dto.getGameAct().getNextTypeCode();
		NEXT_ACT_IMG = new ImageIcon("graphics/game/"+nextIndex+".png").getImage();
		//居中显示
		int actWidth = NEXT_ACT_IMG.getWidth(null);
		int actHeight = NEXT_ACT_IMG.getHeight(null);
		g.drawImage(NEXT_ACT_IMG, this.x + (this.width - actWidth >> 1), this.y + (this.height - actHeight >> 1), null);
	}
}
