package control;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import service.GameService;
import ui.JPanelGame;
import ui.subWindow.FrameGameControl;
import ui.subWindow.FrameRecord;
import utils.FunctionUtil;
import dao.Data;
import dao.DataBase;
import dao.DataDisk;
import dto.Player;
import entity.GameAct;

/**
 * 游戏控制类
 */
public class GameControler {
	/**
	 * 游戏方块下落线程
	 */
	private Thread gameThread;
	/**
	 * 设置界面
	 */
	private FrameGameControl frameCtl;
	/**
	 * 数据库游戏数据
	 */
	private Data dataA;
	/**
	 * 磁盘游戏数据
	 */
	private Data dataB;
	/**
	 *  按键与方法映射的HashMap
	 */
	private HashMap<Integer, Method> keyToAction;
	/**
	 * 游戏界面
	 */
	private JPanelGame jpanelGame;
	/**
	 * 游戏逻辑控制类
	 */
	protected GameService gameService;
	private boolean[][] gameMap;
	/**
	 * 构造函数
	 * @param jpanelGame
	 * @param gameService
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */

	public GameControler(JPanelGame jpanelGame,GameService gameService) throws FileNotFoundException, IOException, SecurityException, NoSuchMethodException {
		//初始化变量
		this.jpanelGame = jpanelGame;
		this.gameService = gameService;
		this.gameMap = gameService.dto.getGameAct().getGameMap();
		//初始化按键映射表
		this.keyToAction = new HashMap<Integer, Method>();
		getKeyConfig();
		
		//TODO 测试方法
		//keyToAction.put(75, this.getClass().getMethod("test"));
		//keyToAction.put(32, this.getClass().getMethod("keyPause"));
		//创建数据库数据对象
		this.dataA = new DataBase();
		//从数据库获取数据
		this.gameService.dto.setDbRecode(dataA.loadData());
		//创建磁盘数据对象
		this.dataB = new DataDisk();
		//从磁盘获取数据
		this.gameService.dto.setDiskRecode(dataB.loadData());
		//初始化方块下落速度
		this.gameService.dto.setSpeed(FunctionUtil.calculateSpeedByLevel(this.gameService.dto.getLevel()));
	}
	/**
	 * 按键响应总方法
	 */
	public void ActionByKeycode(int keyCode){
		//判断游戏状态
		if(!this.gameService.dto.isStart())
			return;
		//判断该按键是否合法
		if( !keyToAction.containsKey(keyCode))
			return;
		Method method = keyToAction.get(keyCode);
		try {
			//调用方法
			method.invoke(this);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 按键响应
	 */
	public void keyDown() {
		//如果暂停则不进行操作
		if(this.gameService.dto.isPause())
			return ;
		boolean[][] map = this.gameService.dto.getGameAct().getGameMap();
		//储存下落的方块
		if(! this.gameService.dto.getGameAct().canMove(0, 1, gameMap)){
			Point[] poses = this.gameService.dto.getGameAct().getActPoint();
			for(int i=0; i<poses.length; i++){
				map[poses[i].x][poses[i].y] = true;
			}
			//获取下一块方块
			int nextTypeCode = this.gameService.dto.getGameAct().getNextTypeCode();
			this.gameService.dto.getGameAct().init(nextTypeCode);
			//设置下一块方块
			this.gameService.dto.getGameAct().setNextTypeCode(new Random().nextInt(this.gameService.dto.getGameAct().MAX_TYPE));
		}
		//判断是否GameOver
		this.dealGameOver(this.gameService.dto.getGameAct().getGameMap());
		int rmLines = this.removeAndgetRemoveLines();
		this.setGameData(rmLines);
		synchronized (this.gameService.dto) {
			this.gameService.dto.getGameAct().move(0,1, gameMap);
		}
		this.jpanelGame.repaint();
	}
	/**
	 * 方块快速下落
	 */
	public void keyQuickDown(){
		//如果暂停则不进行操作
		if(this.gameService.dto.isPause())
			return;
		boolean[][] map = this.gameService.dto.getGameAct().getGameMap();
		while(this.gameService.dto.getGameAct().canMove(0, 1, map)){
			keyDown();
		}
		//为了使方块在下到底部时马上装载进map中
		keyDown();
		System.out.println(111);
		//while(keyDown()) ;
	}
	/**
	 * 设置阴影
	 */
	public void keySwitchShadow(){
		boolean showShadow = this.gameService.dto.isShowShadow();
		this.gameService.dto.setShowShadow(!showShadow);
		this.jpanelGame.repaint();
	}
	/**
	 * TODO  有bug
	 * 判断是否GameOver
	 */
	public boolean isGameOver(boolean[][] map){
		GameAct act = this.gameService.dto.getGameAct();
		Point[] poses = act.getActPoint();
		for(int i=0; i < poses.length; i++){
			//若堆积的方块与正在下落的方块有重叠
			//表示GameOver
			if(map[poses[i].x][poses[i].y]){
				return true;
			}
		}
		return false;
	}
	/**
	 * GameOver时的操作
	 * 
	 */
	public void dealGameOver(boolean[][] map){
		if(isGameOver(map)){
			this.jpanelGame.buttonSwitch(true);
			this.gameService.dto.setStart(false);
			this.gameService.dto.setPause(false);
			this.gameService.dto.setGameOver(true);
			FrameRecord frameRecord = new FrameRecord(jpanelGame, this);
		}
	}
	public void keyRotate() {
		//如果暂停则不进行操作
				if(this.gameService.dto.isPause())
					return;
		//同步操作
		synchronized (this.gameService.dto) {
			this.gameService.dto.getGameAct().rotate(gameMap);
		}
		this.jpanelGame.repaint();
	}
	public void keyPause(){
		boolean tmp = this.gameService.dto.isPause();
		this.gameService.dto.setPause(!tmp);	
		this.jpanelGame.buttonSwitch(!tmp);
	}
	public void keyRight() {
		//如果暂停则不进行操作
				if(this.gameService.dto.isPause())
					return;
		synchronized (this.gameService.dto) {
			
			this.gameService.dto.getGameAct().move(1,0, gameMap);
		}
		this.jpanelGame.repaint();
	}

	public void keyLeft() {
		//如果暂停则不进行操作
				if(this.gameService.dto.isPause())
					return;
		synchronized (this.gameService.dto) {
			
			this.gameService.dto.getGameAct().move(-1,0, gameMap);
		}
		this.jpanelGame.repaint();
	}
	
	/**
	 * 消行计分
	 */
	public void setGameData(int rmLines){
		int nowPoint = this.gameService.dto.getNowPoint();
		int nowLevel = this.gameService.dto.getLevel();
		int nowLvMaxPoint = this.gameService.dto.getLevelMaxPoint();
		int nowRmLines = this.gameService.dto.getNowRemoveRow();
		int addPoints = rmLines * 10;
		//判断加分
		if(rmLines > 1)
			addPoints += (rmLines - 1) * 10;
		nowPoint += addPoints;
		this.gameService.dto.setNowPoint(nowPoint);
		//设置等级
		if(nowPoint > nowLvMaxPoint){
			this.gameService.dto.setLevel(nowLevel + 1); 
			//更新方块下落速度
			int level = this.gameService.dto.getLevel();
			this.gameService.dto.setSpeed(FunctionUtil.calculateSpeedByLevel(level));
		}
		//更新消行数
		
		this.gameService.dto.setNowRemoveRow(nowRmLines + rmLines);
	}
	/**
	 * 消行并返回消去的行数
	 * @return int
	 */
	public int removeAndgetRemoveLines(){
		int rmLines = 0;
		boolean[][] map= this.gameService.dto.getGameAct().getGameMap();
		for(int y=0; y<=this.gameService.dto.getGameAct().getMaxY(); y++){
			if(canRemoveLine(y,map)){
				this.removeLine(y, map);
				rmLines++;
			}
		}
		return rmLines;
	}
	/**
	 * 判断是否可以进行消行
	 * @param y
	 * @param map
	 * @return int
	 */
	public boolean canRemoveLine(int y, boolean[][] map){
		for(int x=0; x<=this.gameService.dto.getGameAct().getMaxX(); x++){
			if(!map[x][y])
				return false;
		}
		return true;
	}
	/**
	 * 消行
	 * @param destY
	 * @param map
	 */
	public void removeLine(int destY, boolean[][] map){
		for(int x = 0; x < this.gameService.dto.getGameAct().getMaxX(); x++){
			for(int y=destY; y > 0; y--){
				map[x][y] = map[x][y-1];
			}
			map[x][0] = false;
		}
	}
	
	/**TODO 测试
	 * 测试
	 * 包含界面重绘
	 */
	public void test() {
		int tmp = this.gameService.dto.getNowRemoveRow();
		//this.gameService.dto.setLevel(tmp + 1);
		this.gameService.dto.setSpeed(FunctionUtil.calculateSpeedByLevel(tmp+1));
		this.gameService.dto.setNowPoint((tmp + 1)*10);
		this.gameService.dto.setNowRemoveRow(tmp+1);
		//TODO测试改变数据的值
		List<Player> players = this.gameService.dto.getDbRecode();
		for(int i=0; i<players.size();i++){
			Player p = players.get(i);
			int tmpDbPoint = p.getPoint();
			p.setPoint(tmpDbPoint + 10);
		}
		this.gameService.dto.setDbRecode(players);
		
		this.jpanelGame.repaint();
	}
	
	public void getKeyConfig(){
		ObjectInputStream ois = null;
		HashMap<Integer, String> keyToActionString;
		try {
			ois = new ObjectInputStream(new FileInputStream("config/keyCfg.data"));
			keyToActionString = (HashMap<Integer, String>)ois.readObject();
			Set<Entry<Integer, String>> entry = keyToActionString.entrySet();
			for(Entry<Integer,String> e : entry){
				keyToAction.put(e.getKey(), this.getClass().getMethod(e.getValue()));
			}
		} catch (Exception e) {
			System.out.println(false);
			e.printStackTrace();
		}finally{
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 显示设置界面
	 */
	public void showSettingWindow(){
		frameCtl = new FrameGameControl(this.jpanelGame, this);
		frameCtl.setVisible(true);
	}
	/**
	 * 开始游戏
	 */
	public void startGame() {
		this.jpanelGame.buttonSwitch(false);
		GameAct act = new GameAct();
		this.gameService.dto.setGameAct(act);
		this.gameService.dto.setStart(true);
		this.gameService.dto.setGameOver(false);
		this.gameService.dto.setLevel(1);
		this.gameService.dto.setNowPoint(0);
		this.gameService.dto.setNowRemoveRow(0);
		this.gameThread = new Thread(){
			@Override
			public void run() {
				while(gameService.dto.isStart()){
				//线程延时
				try {
					Thread.sleep(gameService.dto.getSpeed());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//判断是否处于暂停状态
				if(!gameService.dto.isPause())
					//控制方块按照一定速度下落
					keyDown();
				//重绘
				jpanelGame.repaint();
				}
			}
		};
		//开启线程
		gameThread.start();
	}
	public GameService getGameService() {
		return gameService;
	}
	public void updateDataRecord(String userName, int point) {
		Player pl = new Player(userName, point);
		try {
			//存储数据
			this.dataA.saveData(pl);
			this.dataB.saveData(pl);
			//更新数据
			this.gameService.dto.setDbRecode(this.dataA.loadData());
			this.gameService.dto.setDiskRecode(this.dataB.loadData());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
