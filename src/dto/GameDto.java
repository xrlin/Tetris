package dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import entity.GameAct;

/**
 * 游戏数据源
 **/
public class GameDto {
	/**
	 * 是否显示shadow
	 */
	private boolean showShadow;
	/**
	 * 方块下落速度
	 */
	private long speed;
	/**
	 * 游戏运行状态
	 */
	private boolean start;
	/**
	 * 游戏暂停
	 */
	private boolean pause;
	/**
	 * 是否GameOver
	 */
	private boolean gameOver;
	/**
	 * 数据库数据
	 */
	private List<Player> dbRecode;
	/**
	 * 磁盘记录数据
	 */
	private List<Player> diskRecode;
	/**
	 * 堆积的方块
	 */
	private boolean[][] gameMap;
	/**
	 * 游戏主窗体中下落的方块
	 */
	private GameAct gameAct;
	/**
	 * 等级
	 */
	private int level = 1;
	/**
	 * nowPoint 现在的分数
	 */
	private int nowPoint;
	/**
	 * 进入下一等级所需的分数
	 */
	private int levelMaxPoint;
	/**
	 * 消去的行数
	 */
	private int nowRemoveRow;
	/**
	 * TODO 可以考虑修改该方法以提高游戏性
	 * 当前等级的最高分数
	 * @return leveMaxoint
	 */
	public int getLevelMaxPoint() {
		this.levelMaxPoint = this.getLevel() * 100;
		return levelMaxPoint;
	}
	public List<Player> getDbRecode() {
		return dbRecode;
	}
	public List<Player> getDiskRecode() {
		return diskRecode;
	}
	public int getNowPoint() {
		return nowPoint;
	}
	public void setNowPoint(int i) {
		this.nowPoint = i;
	}
	public void setGameAct(GameAct gameAct) {
		this.gameAct = gameAct;
	}
	public GameAct getGameAct() {
		return gameAct;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level){
		this.level = level;
	}
	public void setNowRemoveRow(int nowRemoveRow) {
		this.nowRemoveRow = nowRemoveRow;
	}
	public int getNowRemoveRow() {
		return nowRemoveRow;
	}
	public void setDbRecode(List<Player> dbRecode) {
		Collections.sort(dbRecode);
		this.dbRecode = dbRecode;
	}
	public void setDiskRecode(List<Player> diskRecode) {
		if(diskRecode == null){
			diskRecode = new ArrayList<Player>();
		}
		while(diskRecode.size()<5){
			diskRecode.add(new Player("No data",0));
		}
		Collections.sort(diskRecode);
		this.diskRecode = diskRecode;
		
	}
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	public boolean isGameOver(){
		return this.gameOver;
	}
	public boolean isPause() {
		return pause;
	}
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	public void setSpeed(long speed) {
		this.speed = speed;
	}
	public long getSpeed() {
		return speed;
	}
	public boolean isShowShadow() {
		return showShadow;
	}
	public void setShowShadow(boolean showShadow) {
		this.showShadow = showShadow;
	}
}