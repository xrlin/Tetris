package config;

import org.dom4j.Element;

public class DiskDataConfig {
	
	private String destDir = "";
	public DiskDataConfig(Element dataB) {
		this.destDir = dataB.attributeValue("destDir");
	}
	public String getDestDir() {
		return destDir;
	}
}
