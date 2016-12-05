package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import config.DataBaseConfig;
import config.GameConfig;

import dto.Player;

/**
 * 
 * 实现数据接口的数据库类
 * @author xrlin
 *
 */
public class DataBase implements Data{
	/**
	 * 显示多少组数据
	 */
	private static int maxRow = GameConfig.getDataInterfaceCfg().getMaxRow();
	/**
	 * 数据库记录的玩家数据
	 */
	private List<Player> p;
	/**
	 * 数据库驱动
	 */
	private String dbDriver = "" ;
	/**
	 * 数据库访问地址
	 */
	private String dbUrl = "";
	/**
	 * 用户名
	 */
	private String user = "";
	/**
	 * 密码
	 */
	private String passwd = ""; 
	/**
	 * 数据库连接
	 */
	private Connection conn;
	/**
	 * 按照分数降序获取数据的前五条记录sql语句
	 */
	private String sqlDesc = "";
	/**
	 * sql插入语句
	 */
	private String sqlInsert = "";
	public DataBase() {
		//初始化要用到的变量、常量
		DataBaseConfig dbCfg= GameConfig.getDataInterfaceCfg().getDataBaseCfg();
		this.dbDriver = dbCfg.getDbDriver();
		this.dbUrl = dbCfg.getDbUrl();
		this.user = dbCfg.getUser();
		this.passwd = dbCfg.getPasswd();
		this.sqlDesc = dbCfg.getSqlDesc();
		this.sqlInsert = dbCfg.getSqlInsert();
	}
	@Override
	public List<Player> loadData() {
		Connection conn;
		p = new ArrayList<Player>();
		
		//数据库连接操作
		try { 
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			//连接数据库并进行操作
			conn = DriverManager.getConnection(dbUrl,user , passwd);
			PreparedStatement stmt = conn.prepareStatement(sqlDesc);
			ResultSet playerInfo = stmt.executeQuery();
			while(playerInfo.next()){
				p.add(new Player(playerInfo.getString(1), playerInfo.getInt(2)));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//若数据库记录少于5个则主动添加以避免出错
		while(p.size() < 5){
			p.add(new Player("None",0));
		}
		return p;
	}
	@Override
	public void saveData(Player player) {
		Connection conn = null;
		PreparedStatement stmt = null;		
		//数据库连接操作
		try { 
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			//连接数据库并进行操作
			conn = DriverManager.getConnection(dbUrl,user , passwd);
			stmt = conn.prepareStatement(sqlInsert);
			//设置用户名
			stmt.setObject(1, player.getName());
			//设置分数
			stmt.setObject(2, player.getPoint());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
