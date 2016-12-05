package main;

import java.lang.reflect.InvocationTargetException;

import control.GameControler;
import control.PlayerControler;

import dto.GameDto;

import service.GameService;
import ui.JFrameGame;
import ui.JPanelGame;
/**
 * main 方法，进行各模块的调用组装
 * @author xrlin
 *
 */
public class Main{
	public static void main(String[] args) throws Exception {
		// 创建游戏数据
		GameDto dto = new GameDto();
		//创建游戏逻辑控制器
		GameService gameService  = new GameService(dto);
		//创建游戏窗体
		JPanelGame jpanel = new JPanelGame(dto);
		//创建游戏控制器获取游戏输入
		GameControler gameControl = new GameControler(jpanel, gameService);
		//创建玩家控制
		PlayerControler playerControl = new PlayerControler(gameControl);
		jpanel.setPlayerControl(playerControl);
		//创建窗口
		new JFrameGame(jpanel);
	}
}
