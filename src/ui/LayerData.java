package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import config.GameConfig;

import dto.Player;
/**
 * layerdatabase和layerdisk的基类
 * @author xrlin
 *
 */
public class LayerData extends Layer {

	/**
	 * 值槽条数
	 */
	protected static final int MAX_ROW = GameConfig.getDataInterfaceCfg().getMaxRow();
	/**
	 * 绘制值槽的起始y坐标
	 */
	protected static int startY = 0;
	/**
	 * 值槽间的间隔
	 */
	protected static int SPA = 0;
	/**
	 * 标题图片
	 */
	protected Image titleImage;
	/**
	 * 玩家数据
	 */
	protected List<Player> players;
	/**
	 * 构造函数
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public LayerData(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		drawLayerData(titleImage, players , g);
	}
	public void drawLayerData(Image titleImage, List<Player> players, Graphics g){
		g.drawImage(titleImage, this.x+PADDING + this.BORDER_SIZE, this.y+PADDING + this.BORDER_SIZE, null);
		//绘制5条值槽
		for(int i=0;i<MAX_ROW;i++){
			Player p = players.get(i);
			this.drawExpRect(startY + i * (RECTH + 4 + SPA), p.getName(), Integer.toString(p.getPoint()), p.getPoint(), g);
		}
	}
}
