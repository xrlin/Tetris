package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import config.GameConfig;

import dto.Player;

public class DataDisk implements Data {

	private List<Player> players = new ArrayList<Player>();
	/**
	 * 磁盘记录存储地址
	 */
	private static final String DEST_DIR = GameConfig.getDataInterfaceCfg()
			.getDiskDataCfg().getDestDir();

	@Override
	public List<Player> loadData() throws FileNotFoundException, IOException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				DEST_DIR));
		try {
			this.players = (List<Player>) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			ois.close();
		}
		//若记录少于5个则主动添加以避免出错
		while(this.players.size() < 5){
			players.add(new Player("None",0));
		}
		return players;
	}

	@Override
	public void saveData(Player player) throws FileNotFoundException,
			IOException {
		// 添加记录
		//!为了避免输入输出流同时操作出现错误，先加载数据后建立输出流
		this.loadData();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				DEST_DIR));
		;
		this.players.add(player);
		try {
			oos.writeObject(players);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
