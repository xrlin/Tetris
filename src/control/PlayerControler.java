package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 玩家控制类
 */
public class PlayerControler extends KeyAdapter{
	
	private GameControler gameControler;
	/**
	 * 构造函数，进行变量初始化
	 * @param gameControl
	 */
	public PlayerControler(GameControler gameControl) {
		this.gameControler = gameControl;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//按键事件
		this.gameControler.ActionByKeycode(e.getKeyCode());
	}
	public GameControler getGameControler() {
		return gameControler;
	}
}
