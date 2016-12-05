package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import config.GameConfig;
import dao.DataDisk;
import dto.Player;
//TODO
public class Test {
	private static final String DEST_DIR = GameConfig.getDataInterfaceCfg().getDiskDataCfg().getDestDir();
	public static void main(String[] args) throws FileNotFoundException, IOException {
		DataDisk dd = new DataDisk();
		List<Player> players = new ArrayList<Player>();
		for(int i=0; i<5; i++){
			players.add(new Player("xrlin",1000));
		}
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DEST_DIR));;
		try {
			oos.writeObject(players);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
