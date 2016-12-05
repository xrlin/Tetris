package ui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.lang.management.GarbageCollectorMXBean;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import config.GameConfig;

public class LayerGame extends Layer {
	/**
	 * 暂停图片
	 */
	private static final Image PAUSE_IMG = new ImageIcon("graphics/string/pause.png").getImage();
	/**
	 *  方块图片
	 */
	private static final Image ACT = new ImageIcon("graphics/game/rect.png").getImage();
	/**
	 * 阴影图片
	 */
	private static final Image SHADOW  = new ImageIcon("graphics/game/shadow2.png").getImage();
	/**
	 * 方块图片中每种方块图片的间隔
	 */
	private final int ACT_SIZE = 32;
	public LayerGame(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g) {
		//创建窗口
		this.createWindow(g);
		//绘制
		drawShadow(this.dto.getGameAct().getActPoint(), g);
		drawDiamond(g);
		paintMap(g);
		//如果暂停则显示暂停的图片
		if(this.dto.isPause())
			this.drawPause(g);
	}
	/**
	 * 根据level
	 * 打印不同颜色的地图(堆积的方块）
	 * @param g
	 */
	public void paintMap(Graphics g){
		boolean[][] map = this.dto.getGameAct().getGameMap();
		//根据等级判断应使用何种颜色表示堆积的方块
		int lv = this.dto.getLevel();
		int colorIdx;
		//GameOver时的方块颜色
		if(this.dto.isGameOver())
			colorIdx = 7;
		else
			colorIdx = lv == 1 ? 0 : (lv - 2) % 7 + 1;
		for(int x=0;x<map.length;x++){
			for(int y=0; y<map[x].length; y++)
			if(map[x][y])
				g.drawImage(ACT,
						x * ACT_SIZE+this.x + Layer.BORDER_SIZE, 
						y * ACT_SIZE+this.y+this.BORDER_SIZE,
						x * ACT_SIZE+this.x + ACT_SIZE + this.BORDER_SIZE,
						y * ACT_SIZE+this.y+ACT_SIZE+this.BORDER_SIZE, 
						 colorIdx * this.ACT_SIZE, 0, (colorIdx + 1) * this.ACT_SIZE, this.ACT_SIZE,  null);
		}
	}
	public void drawDiamond(Graphics g){
		//判断游戏是否正在进行中
		if(!this.dto.isStart())
			return ;
		Point[] poses = this.dto.getGameAct().getActPoint();
		int typeCode = this.dto.getGameAct().getTypeCode();
		for(int i=0;i<poses.length;i++){
			g.drawImage(ACT, poses[i].x * ACT_SIZE+this.x + Layer.BORDER_SIZE, poses[i].y * ACT_SIZE+this.y+Layer.BORDER_SIZE, poses[i].x * ACT_SIZE+this.x + ACT_SIZE + this.BORDER_SIZE, poses[i].y * ACT_SIZE+this.y+ACT_SIZE+this.BORDER_SIZE,  (typeCode+1)*ACT_SIZE, 0, (typeCode+2)*ACT_SIZE, 32,  null);
		}
	}
	/**
	 * 绘制阴影
	 */
	private void drawShadow(Point[] points, Graphics g){
		//判断游戏是否正在进行中
		if((!this.dto.isStart()) || !this.dto.isShowShadow())
			return ;
		//方块最左边
		int leftX = GameConfig.getSysCfg().getMaxX();
		//方块最右边
		int  rightX= GameConfig.getSysCfg().getMinX();
		
		for(int i=0; i<points.length; i++){
			leftX = points[i].x <= leftX ? points[i].x : leftX;
			rightX = points[i].x >= rightX ? points[i].x : rightX;
		}
		g.drawImage(SHADOW, this.x+leftX  * this.ACT_SIZE + this.BORDER_SIZE, this.y + this.BORDER_SIZE, this.x + (rightX + 1) * this.ACT_SIZE + this.BORDER_SIZE, this.y - this.BORDER_SIZE + this.height, 0, 0, 1, 1, null);
	}
	/**
	 * 绘制暂停图片
	 */
	public void drawPause(Graphics g){
		this.drawImgAtCenter(this.x , this.y ,this.width, this.height , PAUSE_IMG, g);
	}
	
}
