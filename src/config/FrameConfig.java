package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
/**
 * 包含所有ui的配置信息
 * @author xrlin
 *
 */
public class FrameConfig {
	
	/**
	 * width of the frame
	 */
	private int width;
	/**
	 * height of the frame
	 */
	private int height;
	/**
	 * 窗口标题
	 */
	private String title;
	/**
	 * panel窗口大小
	 */
	private int borderSize;
	/**
	 * 
	 */
	private int windowUp;
	/**
	 * layer边框宽度(内边距）
	 */
	private int padding;

	/**
	 * layer配置类列表
	 */
	private List<LayerConfig> layersConfig = new ArrayList<LayerConfig>();
	/**
	 * 按钮配置
	 */
	private List<ButtonConfig> btnsConfig = new ArrayList<ButtonConfig>();
	/**
	 * 
	 * @param frame
	 */
	public FrameConfig(Element frame) {
		//获取frame信息
		this.windowUp = Integer.parseInt(frame.attributeValue("windowUp"));
		this.title = frame.attributeValue("title");
		this.width = Integer.parseInt(frame.attributeValue("width"));
		this.height = Integer.parseInt(frame.attributeValue("height"));
		this.borderSize = Integer.parseInt(frame.attributeValue("borderSize"));
		this.padding = Integer.parseInt(frame.attributeValue("padding"));
		// 获取layer节点
		List<Element> layers = frame.elements("layer");
		for(Element layer:layers){
			LayerConfig lcfg = new LayerConfig();
			lcfg.setClassName(layer.attributeValue("className"));
			lcfg.setX(Integer.parseInt(layer.attributeValue("x")));
			lcfg.setY(Integer.parseInt(layer.attributeValue("y")));
			lcfg.setWidth(Integer.parseInt(layer.attributeValue("width")));
			lcfg.setHeight(Integer.parseInt(layer.attributeValue("height")));
			layersConfig.add(lcfg);
		}
		
		//获取按钮配置
		List<Element> btns = frame.elements("button");
		for(Element btn : btns){
			ButtonConfig btnCfg = new ButtonConfig();
			btnCfg.setX(Integer.parseInt(btn.attributeValue("x")));
			btnCfg.setY(Integer.parseInt(btn.attributeValue("y")));
			btnCfg.setW(Integer.parseInt(btn.attributeValue("width")));
			btnCfg.setH(Integer.parseInt(btn.attributeValue("height")));
			btnsConfig.add(btnCfg);
			
		}
		
	}
	public List<ButtonConfig> getBtnsConfig() {
		return btnsConfig;
	}
	public int getHeight() {
		return height;
	}
	public List<LayerConfig> getLayersConfig() {
		return layersConfig;
	}
	public int getWidth() {
		return width;
	}
	public int getPadding() {
		return padding;
	}
	public int getBorderSize() {
		return borderSize;
	}
	public String getTitle() {
		return title;
	}
	public int getWindowUp() {
		return windowUp;
	}

}
