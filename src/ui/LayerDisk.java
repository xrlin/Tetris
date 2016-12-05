package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerDisk extends LayerData {

	/*
	 *磁盘图片
	 */
	private Image titleImage = new ImageIcon("graphics/string/disk.png").getImage();
	public LayerDisk(int x, int y, int width, int height) {
		super(x, y, width, height);
		SPA = (this.height - ((this.PADDING + this.BORDER_SIZE)<<1) - (this.RECTH + 4) * 5 - titleImage.getHeight(null)) / MAX_ROW;
		startY = titleImage.getHeight(null) + this.PADDING + this.BORDER_SIZE + this.SPA;
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		this.players = this.dto.getDiskRecode();
		drawLayerData(this.titleImage, players , g);
	}

}
