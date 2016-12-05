package entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import config.GameConfig;

/**
 * 游戏的实体
 * 
 */
public class GameAct {
	
	/**
	 * 用来进行游戏的各个方块的集合
	 */
	private static final List<Point[]> TYPELIST = GameConfig.getSysCfg().getTypeList();
	/**
	 * 组成方块的各个小方块位置
	 */
	private Point[] actPoint;
	/**
	 * 游戏区域最左边
	 */
	private static final int MIN_X = GameConfig.getSysCfg().getMinX(); 
	/**
	 * 游戏区域最右边
	 */
	private static final int MAX_X  = GameConfig.getSysCfg().getMaxX();
	/**
	 * 游戏区域最上边
	 */
	private static final int MIN_Y = GameConfig.getSysCfg().getMinY() ;
	/**
	 * 游戏区域最下边
	 */
	private static final int MAX_Y = GameConfig.getSysCfg().getMaxY();
	/**
	 * 储存下落的方块
	 */
	private boolean[][] gameMap = new boolean[10][20];
	/**
	 * 包含有每中方块是否能够旋转的信息
	 */
	private static final List<Boolean> isRotateList = GameConfig.getSysCfg().getIsRotatedList();
	/**
	 * 方块的标号
	 */
	private int typeCode;
	/**
	 * 下一块方块的标号
	 */
	private int nextTypeCode;
	/**
	 * 包含所有方块的数组的长度
	 */
	public static final int MAX_TYPE = TYPELIST.size() - 1;
	public GameAct(){
		//初始化
		//给nextTypeCode赋值
		this.nextTypeCode = new Random().nextInt(MAX_TYPE);
		this.init(new Random().nextInt(MAX_TYPE));
	}
	public void init(int typeCode){
		//给typeCode赋值
		this.typeCode = typeCode;
		//实现对TYPELIST的深度复制而不是引用
		Point[] tmpList = TYPELIST.get(typeCode);
		actPoint = new Point[tmpList.length];
		for(int i=0; i<tmpList.length; i++){
			actPoint[i] = new Point(tmpList[i].x, tmpList[i].y);
		}
//		actPoint = new Point[]{
//				new Point(4,0), //中心点
//				new Point(3,0),
//				new Point(5,0),
//				new Point(5,1)
//				};
	}
	public Point[] getActPoint() {
		return actPoint;
	}
	
	/**
	 * 方块移动函数
	 * @param x 偏移量
	 * @param y 偏移量
	 */
	public void move(int x , int y, boolean[][] gameMap){
		if (canMove(x, y, gameMap)){
			for(int i=0;i<actPoint.length;i++){
				actPoint[i].x += x;
				actPoint[i].y += y;
			}
		}
	}
	
	public void rotate(boolean[][] gameMap){
		//判断是否可以旋转
		if(! isRotateList.get(this.typeCode)){
			return ;
		}
		for(int i=0;i<actPoint.length;i++){
			int newX = actPoint[0].x + actPoint[0].y - actPoint[i].y;
			int newY = actPoint[0].y - actPoint[0].x + actPoint[i].x;
			if (newX < MIN_X || newX > MAX_X || newY < MIN_Y || newY > MAX_Y || gameMap[newX][newY])
				return ;
		}
		for(int i=0;i<actPoint.length;i++){
			int newX = actPoint[0].x + actPoint[0].y - actPoint[i].y;
			int newY = actPoint[0].y - actPoint[0].x + actPoint[i].x;
			actPoint[i].x = newX;
			actPoint[i].y = newY;
		}
	}
	/**
	 * 判断是否可以移动
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean canMove(int x, int y, boolean[][] gampMap){
		for(int i=0;i<actPoint.length;i++){
			int newX = actPoint[i].x + x;
			int newY = actPoint[i].y + y;
			if (newX < MIN_X || newX > MAX_X || newY < MIN_Y || newY > MAX_Y || gameMap[newX][newY])
				return false;
		}
		
		return true;
	}
	public boolean[][] getGameMap() {
		return this.gameMap;
	}
	public int getTypeCode() {
		return this.typeCode;
	}
	public int getNextTypeCode() {
		return nextTypeCode;
	}
	public void setNextTypeCode(int nextTypeCode) {
		this.nextTypeCode = nextTypeCode;
	}
	public static int getMaxY() {
		return MAX_Y;
	}
	public static int getMaxX() {
		return MAX_X;
	}
}
