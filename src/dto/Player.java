package dto;

import java.io.Serializable;

/**
 * player数据
 * 实现序列化接口以存储对象在磁盘中
 * @author xrlin
 *
 */
public class Player implements Comparable<Player>, Serializable{
	
	private String name;
	
	private int point;
	public Player(String name, int point) {
		super();
		this.name = name;
		this.point = point;
	}
	
	public String getName() {
		return name;
	}
	public int getPoint() {
		return point;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public int compareTo(Player p) {
		return  p.point - this.point;
	}

}
