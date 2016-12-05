package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import dto.Player;

/**
 * 数据接口
 * @author xrlin
 *
 */
public interface Data{
	/**
	 * 读取数据接口
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public List<Player> loadData() throws FileNotFoundException, IOException;
	/**
	 * 设置数据接口
	 * @param players
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void saveData(Player player) throws FileNotFoundException, IOException;
}
