package config;

/*
 * 包含类的配置信息的类
 */
public class LayerConfig {
	/*
	 * 用来创建的类的名称 
	 */
	private String className;
	/*
	 * layer 的左上角x坐标
	 */
	private int x;
	/*
	 * layer的左上角y坐标
	 */
	private int y;
	/*
	 * layer的宽度
	 */
	private int width;
	/*
	 * layer的高度
	 */
	private int height;
	
	public String getClassName(){
		return className;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	
	
}
