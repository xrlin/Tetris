package config;

import org.dom4j.Element;

/**
 * 数据节点的对象，包含dataA
 * 和dataB
 * @author xrlin
 *
 */
public class DataInterfaceConfig {
	/**
	 * DataBaseConfig对象
	 */
	private static DataBaseConfig dataBaseCfg;
	/**
	 * DiskDataConfig对象
	 */
	private static DiskDataConfig diskDataCfg;
	/**
	 * 要显示的数据行数
	 */
	private int maxRow;
	public DataInterfaceConfig(Element data) {
		dataBaseCfg = new DataBaseConfig(data.element("dataA"));
		diskDataCfg = new DiskDataConfig(data.element("dataB"));
		maxRow = Integer.parseInt(data.attributeValue("maxRow"));
	}
	public static DataBaseConfig getDataBaseCfg() {
		return dataBaseCfg;
	}
	public static DiskDataConfig getDiskDataCfg() {
		return diskDataCfg;
	}
	public int getMaxRow() {
		return maxRow;
	}
}
