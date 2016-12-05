package config;

import org.dom4j.Element;

public class DataBaseConfig {
	/**
	 * 数据库驱动jdbc
	 */
	private String dbDriver="";
	/**
	 * 数据库地址
	 */
	private String dbUrl="" ;
	/**
	 * 数据库用户
	 */
	private String user="";
	/**
	 * 用户密码
	 */
	private String passwd="" ;
	/**
	 * 数据库查询语句
	 */
	private String sqlDesc="select uname, score from player order by score desc limit 5";
	/**
	 * 数据库插入语句
	 */
	private String sqlInsert="";
	
	public DataBaseConfig(Element dataA) {
		this.dbDriver = dataA.attributeValue("dbDriver");
		this.dbUrl = dataA.attributeValue("dbUrl");
		this.user = dataA.attributeValue("user");
		this.passwd = dataA.attributeValue("passwd");
		this.sqlDesc = dataA.attributeValue("sqlDesc");
		this.sqlInsert = dataA.attributeValue("sqlInsert");
	}
	public String getDbDriver() {
		return dbDriver;
	}
	public String getDbUrl() {
		return dbUrl;
	}
	public String getPasswd() {
		return passwd;
	}
	public String getUser() {
		return user;
	}
	public String getSqlDesc() {
		return sqlDesc;
	}
	public String getSqlInsert() {
		return sqlInsert;
	}
	
}
