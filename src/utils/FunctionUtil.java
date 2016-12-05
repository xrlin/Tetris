package utils;
/**
 * 游戏运算
 * @author archer
 *
 */
public class FunctionUtil {
	/*
	 * 计算方块下落速度
	 */
	public static long calculateSpeedByLevel(int level){
		long speed;
		int tmp = 700 + ((100 - 700) / 20) * (level - 1);
		speed = (long)(level > 20 ? 100 : tmp);
		return speed;
	}
}
