package service;

import java.awt.Point;

import dto.GameDto;
import entity.GameAct;

/**
 * 游戏逻辑层，接收玩家控制，控制画面、游戏逻辑
 * @author xrlin
 *
 */
public class GameService {
	/**
	 * 数据Dto
	 */
	public GameDto dto;
	/**
	 *
	 * @param dto
	 */
	public GameService(GameDto dto) {
		this.dto  = dto;
		this.dto.setGameAct(new GameAct());
	}

	/*
	 * 测试
	 */
	public void gameTest() {
		int tmp = this.dto.getNowPoint();
		this.dto.setNowPoint(tmp+1);
	}

}
