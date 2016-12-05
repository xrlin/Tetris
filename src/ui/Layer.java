package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import config.FrameConfig;
import config.GameConfig;
import dto.GameDto;
/**
 * PanelGame中各种panel的基类
 * @author xrlin
 *
 */
public abstract class Layer extends JPanel {
	 /**
	 * 边框宽度
	 */
	static FrameConfig frameCfg = GameConfig.getFrameConfig(); //获取游戏配置对象
	protected static final int BORDER_SIZE = frameCfg.getBorderSize();
	/**
	 * 窗口图片
	 */
	private static final Image WINDOW_IMG = new ImageIcon("graphics/window/Window.png").getImage();
	
	/**
	 * 数字图片
	 */
	protected static final Image NUM_IMG = new ImageIcon("graphics/string/num.png").getImage();
	/**
	 * 值槽图片
	 */
	private static final Image EXP_RECT_IMG = new ImageIcon("graphics/window/rect.png").getImage();
	/**
	 * 字体
	 */
	private static final Font DEF_FONT = new Font("黑体", Font.BOLD, 25);
	/**
	 * 原值槽图片的宽度
	 */
	private static final int EXP_RECT_W = EXP_RECT_IMG.getWidth(null);
	/**
	 * 值槽宽度
	 */
	protected int expW;
	/**
	 * 值槽左上角y坐标
	 */
	protected int expY ;
	/**
	 * 值槽高度
	 */
	protected static final int RECTH = EXP_RECT_IMG.getHeight(null);
	/**
	 * 图片宽度
	 */
	private static final int WINDOW_IMG_W = WINDOW_IMG.getWidth(null);
	
	/**
	 * 图片高度
	 */
	private static final int WINDOW_IMG_H = WINDOW_IMG.getHeight(null);
	 /**
	 * 每个窗口的Padding
	 */
	//从配置文件获取padding的值
	protected static final int PADDING = frameCfg.getPadding();
	/**
	 * 窗口左上角x坐标
	 */
	protected int x;
	/**
	 * 窗口左上角y坐标
	 */
	protected int y;
	/**
	 * 窗口实际宽度
	 */
	protected int width;
	/**
	 * 窗口时间高度
	 */
	protected int height;
	/**
	 * Dto数据对像
	 */
	protected GameDto dto;
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	
	public Layer(int x, int y,int width,int height ){
		//窗口左上角坐标
		this.x = x;
		this.y = y;
		//窗口高度、宽度
		this.width = width + (BORDER_SIZE << 1);
		this.height = height + (BORDER_SIZE << 1);
	}
	/**
	 * 绘制窗口
	 */
	public void createWindow(Graphics g){
		//绘制左上
		g.drawImage(WINDOW_IMG, x, y, x+BORDER_SIZE, y+BORDER_SIZE, 0, 0, 7, 7,null);
		//绘制中上
		g.drawImage(WINDOW_IMG, x+BORDER_SIZE,y, x+width-BORDER_SIZE, y+BORDER_SIZE, BORDER_SIZE,0, WINDOW_IMG_W-BORDER_SIZE,BORDER_SIZE, null);
		//绘制右上
		g.drawImage(WINDOW_IMG, x+width-BORDER_SIZE, y, x+width, y+BORDER_SIZE, WINDOW_IMG_W-BORDER_SIZE, 0, WINDOW_IMG_W, BORDER_SIZE, null);
		//绘制中左
		g.drawImage(WINDOW_IMG, x, y+BORDER_SIZE, x+BORDER_SIZE, y+height-BORDER_SIZE, 0, BORDER_SIZE, BORDER_SIZE, WINDOW_IMG_H-BORDER_SIZE, null);
		//绘制中间部分
		g.drawImage(WINDOW_IMG, x+BORDER_SIZE, y+BORDER_SIZE, x+width-BORDER_SIZE, y+height-BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, WINDOW_IMG_W-BORDER_SIZE, WINDOW_IMG_H-BORDER_SIZE, null);
		//绘制中右部分
		g.drawImage(WINDOW_IMG, x+width-BORDER_SIZE, y+BORDER_SIZE, x+width,y+height-BORDER_SIZE, WINDOW_IMG_W-BORDER_SIZE, BORDER_SIZE, WINDOW_IMG_W, WINDOW_IMG_H-BORDER_SIZE, null);
		//绘制左下部分
		g.drawImage(WINDOW_IMG, x, y+height-BORDER_SIZE, x+BORDER_SIZE, y+height, 0, WINDOW_IMG_H-BORDER_SIZE, BORDER_SIZE, WINDOW_IMG_H, null);
		//绘制下中部分
		g.drawImage(WINDOW_IMG, x+BORDER_SIZE, y+height-BORDER_SIZE, x+width-BORDER_SIZE, y+height, BORDER_SIZE, WINDOW_IMG_H-BORDER_SIZE, WINDOW_IMG_W-BORDER_SIZE, WINDOW_IMG_H, null);
		//绘制下左部分
		g.drawImage(WINDOW_IMG, x+width-BORDER_SIZE, y+height-BORDER_SIZE, x+width, y+height, WINDOW_IMG_W-BORDER_SIZE, WINDOW_IMG_H-BORDER_SIZE, WINDOW_IMG_W,WINDOW_IMG_H, null);
	}
	/*
	 * 绘制图形
	 * @parameter g
	 */
	public abstract void paint(Graphics g);
	public void setDto(GameDto dto) {
		this.dto = dto;
	}
	
	/**
	 * 显示数字
	 * @param num_img 表示数字的图片
	 * @param maxWei  最大位数
	 * @param num		要显示的数字
	 * @param x		相对左上角的x偏移量
	 * @param y		相对左上角的y偏移量
	 * @param g		Graphics对象
	 */
	protected void drawNum(Image num_img, int maxWei, int num, int x, int y, Graphics g){
		// 包含0～9数字的图片中的每个数字的宽度
		int numImgWidth = num_img.getWidth(null) / 10;
		// 包含0～9数字的图片中的每个数字的长度
		int numImgHeight = num_img.getHeight(null) ;
		//将数值转换为字符串
		String numString = Integer.toString(num);
		for(int i=0; i< maxWei; i++){
			if(maxWei-i <= numString.length()){
				int idx = i - maxWei + numString.length();
				int bit = numString.charAt(idx) - '0';
				g.drawImage(num_img, this.x + x + i * numImgWidth, this.y + y, this.x + x + (i+1)*numImgWidth, this.y + y + numImgHeight, bit*numImgWidth, 0, (bit+1)*numImgWidth, numImgHeight, null);
			}
		}
	}
	
	/**
	 * 绘制值槽
	 * @param startY  值槽开始绘制的左上角的y
	 * @param title 值槽中的标题
	 * @param name 
	 * @param point 分数
	 * @param g
	 */
	protected void drawExpRect(int startY, String title, String name, double point,  Graphics g){
		//初始化值
		this.expY = startY;
		this.expW = this.width - ((this.PADDING + this.BORDER_SIZE) << 1 );
		int rectX = this.x + this.PADDING + this.BORDER_SIZE;
		int rectY = this.y + expY ;
		//绘制值槽边框
		g.setColor(Color.black);
		g.fillRect(rectX, rectY, this.expW, RECTH + 4);
		g.setColor(Color.WHITE);
		g.fillRect(rectX+1, rectY+1, this.expW-2, RECTH + 2);
		g.setColor(Color.black);
		g.fillRect(rectX+2, rectY+2, this.expW-4, RECTH);
		
		//计算经验值比例和经验值对应的值槽图片截取范围
		double p = getPercent(point);
		//宽度
		int w = (int)(p * (this.expW - 4));
		//int w = (int)(point / ((this.dto.getLevelMaxPoint() / (this.expW - 4))));
		//颜色
		int subIdx = (int)(p* EXP_RECT_W ) - 1;
		//subIdx = 178;
		//TODO
		System.out.println(w + " " + subIdx);
		//绘制
		g.drawImage(EXP_RECT_IMG, rectX + 2, 
				rectY + 2, rectX +2 + w,
				rectY + 2 + this.RECTH, 
				subIdx, 0, subIdx + 1, this.RECTH, null);
		g.setFont(DEF_FONT);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString(title, rectX + 2, rectY + this.RECTH);
		if(name != null){
			g.drawString(name, rectX+200, rectY+this.RECTH);
		}
	}
	public double getPercent(double point){
		//比例
		//求出当前等级目标分数 由 this.levelMaxPoint = this.getLevel() * 100 可求
		//int level = ((int)(point / 100) + 1);
		//int lvMaxPoint = level * 100;
		int lvMaxPoint = this.dto.getLevelMaxPoint();
		System.out.println(point/lvMaxPoint);
		double tmp = (double)(point / lvMaxPoint);
		double p = (tmp > 1 ? 0 : tmp);
		return p;
	}
	
	/**
	 * 居中显示图片
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param img
	 * @param g
	 */
	public void drawImgAtCenter(int x , int y,  int w , int h ,  Image img, Graphics g){
		//获取图片显示的左上角坐标
		int imgX = x + (( w - img.getWidth(null) ) >> 1);
		int imgY = y + (( h - img.getHeight(null)) >> 1);
		g.drawImage(img, imgX, imgY, null);
		
	}

}
