package config;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GameConfig {
		/**
		 * 游戏Frame配置对象
		 */
		private static FrameConfig frameCfg;
		/**
		 * 游戏Layer配置对象
		 */
		private static LayerConfig layerCfg;
		/**
		 * 游戏Sysetem配置对象
		 */
		private static SystemConfig sysCfg;
		/**
		 *游戏Date配置对象 
		 */
		private static  DataInterfaceConfig dataInterfaceCfg;
		
		static{
			SAXReader saxreader = new SAXReader();
			Document source;
			try {
				source = saxreader.read("config/cfg.xml");
				//获取game节点
				Element game = source.getRootElement();
				
				//建立对象
				frameCfg = new FrameConfig(game.element("frame"));
				sysCfg = new SystemConfig(game.element("system"));
				dataInterfaceCfg = new DataInterfaceConfig(game.element("data"));
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		public GameConfig() throws Exception {
			
		}
		
		/**
		 *  获取FrameConfig对象
		 * @param e
		 */
		public static FrameConfig  getFrameConfig(){
			return frameCfg;
		}
		public static DataInterfaceConfig getDataInterfaceCfg() {
			return dataInterfaceCfg;
		}
		public static LayerConfig getLayerCfg() {
			return layerCfg;
		}
		public static SystemConfig getSysCfg() {
			return sysCfg;
		}
}
