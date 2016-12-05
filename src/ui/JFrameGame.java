package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import config.FrameConfig;
import config.GameConfig;
/**
 * 窗体
 * @author xrlin
 *
 */
public class JFrameGame extends JFrame {
	int width;
	int height;
	final int BORDER_SIZE;
	int windowUp;
	
	public JFrameGame(JPanel japnel) throws Exception{
		FrameConfig cfg = GameConfig.getFrameConfig(); //获取游戏配置对象
		BORDER_SIZE = cfg.getBorderSize();
		windowUp = cfg.getWindowUp();
		//设置窗口标题
		this.setTitle(cfg.getTitle());
		//设置窗口关闭事件
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//设置窗口大小
		
		this.setSize(cfg.getWidth(),cfg.getHeight());
		//设置窗口不能改变大小
		this.setResizable(false);
		//设置窗口居中
		//获取屏幕尺寸
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimension = toolkit.getScreenSize();
		int screen_width = screenDimension.width;
		int screen_height = screenDimension.height;
		//为了在视觉上好一点，将yarg0坐标加上一定值
		int x = (screen_width-this.getWidth())/2;
		int y = (screen_height-this.getHeight())/2-windowUp;
		this.setLocation(x, y);
		
		//设置默认的Panel
		this.setContentPane(japnel);
		
		//显示窗口
		this.setVisible(true);
		
	}
}