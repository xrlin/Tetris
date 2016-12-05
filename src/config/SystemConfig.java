package config;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class SystemConfig {

	private final int minX;
	
	private final  int maxX;
	
	private final int minY;
	
	private final int maxY; 
	/**
	 * 组成每个方块的小方块的边长
	 */
	private final int ACT_SIZE; 
	
	private final List<Boolean> isRotatedList;
	
	private final List<Point[]> TypeList;
	
	public SystemConfig(Element sys) {
		this.minX = Integer.parseInt(sys.attributeValue("minX"));
		this.maxX = Integer.parseInt(sys.attributeValue("maxX"));
		this.minY = Integer.parseInt(sys.attributeValue("minY"));
		this.maxY = Integer.parseInt(sys.attributeValue("maxY"));
		this.ACT_SIZE = Integer.parseInt(sys.attributeValue("actSize"));
		
		isRotatedList = new ArrayList<Boolean>();
		List<Element> rects = sys.elements("rect");
		TypeList = new ArrayList<Point[]>(rects.size());
		for(Element rect : rects){
			isRotatedList.add(Boolean.parseBoolean(rect.attributeValue("rotate")));
			List<Element> pointsCfg = rect.elements("point");
			Point[] points = new Point[pointsCfg.size()];
			for(int i=0; i<points.length; i++){
				int x = Integer.parseInt(pointsCfg.get(i).attributeValue("x"));
				int y = Integer.parseInt(pointsCfg.get(i).attributeValue("y"));
				points[i] = new Point(x, y); 
			}
			TypeList.add(points);
		}
	}
	
	public List<Boolean> getIsRotatedList() {
		return isRotatedList;
	}
	public int getMinX() {
		return minX;
	}
	public int getMaxX() {
		return maxX;
	}
	public int getMinY() {
		return minY;
	}
	public int getMaxY() {
		return maxY;
	}
	public List<Point[]> getTypeList() {
		return TypeList;
	}

}
