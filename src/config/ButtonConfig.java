package config;

public class ButtonConfig {
	/**
	 * 按钮的x坐标
	 */
	private int x;
	/**
	 * 按钮的y坐标
	 */
	private int y;
	/**
	 *按钮的宽度
	 */
	private int  w;
	/**
	 * 按钮的高度
	 */
	private int h;
	
	public int getH() {
		return h;
	}
	public int getW() {
		return w;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setH(int h) {
		this.h = h;
	}
	public void setW(int w) {
		this.w = w;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
}
