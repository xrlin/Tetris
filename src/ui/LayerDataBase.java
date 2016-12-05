package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import dto.Player;
/**
 * 数据库排名
 * @author xrlin
 *
 */
public class LayerDataBase extends LayerData {

	
	/**
	 * 数据库图片
	 */
	private Image titleImage = new ImageIcon("graphics/string/db.png").getImage();
	
	public LayerDataBase(int x, int y, int width, int height) {
		super(x, y, width, height);
		SPA = (this.height - ((this.PADDING + this.BORDER_SIZE)<<1) - (this.RECTH + 4) * 5 - titleImage.getHeight(null)) / MAX_ROW;
		startY = titleImage.getHeight(null) + this.PADDING + this.BORDER_SIZE + this.SPA;
		
	}
	@Override
	public void paint(Graphics g) {
		//TODO 绘制数据库窗口及内容
		this.createWindow(g);
		this.players = this.dto.getDbRecode();
		drawLayerData(this.titleImage, players , g);
	}

}
