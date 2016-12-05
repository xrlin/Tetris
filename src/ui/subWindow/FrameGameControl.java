package ui.subWindow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import control.GameControler;
import ui.JPanelGame;

public class FrameGameControl extends JFrame {
	
	private GameControler gameCtl;
	private JPanelGame jpanelGame;
	/**
	 *  按键与方法映射的HashMap
	 */
	private HashMap<Integer, String> keyToActionString;
	/**
	 * 按键方法
	 */
	private final String[] menthodes= {
			"keyRotate","keyDown", "keyLeft", "keyRight", "keyQuickDown", "keySwitchShadow" ,"keyPause"
	};
	/**
	 * 存储按键配置文件的路径
	 */
	private final String KEYPATH = "config/keyCfg.data";
	/**
	 * 确定按钮
	 */
	private JButton jbOK = new JButton("确定");
	/**
	 * 取消按钮
	 */
	private JButton jbCancel = new JButton("取消");
	/**
	 * 文本框数组
	 */
	private TextCtl[] textctls = {new TextCtl(100, 70, 100, 50, menthodes[2]),
	new TextCtl(300, 70, 100, 50, menthodes[3]),new TextCtl(200, 0, 100, 50, menthodes[0]),
	new TextCtl(200, 140, 100, 50, menthodes[1]), new TextCtl(50,200,100,50, menthodes[4]), 
	new TextCtl(350,200,100,50, menthodes[5]), new TextCtl(200,200,100,50, menthodes[6])
	};
	
	public FrameGameControl(JPanelGame jpanelGame, GameControler gameCtl){
		this.gameCtl = gameCtl;
		this.jpanelGame = jpanelGame;
		initText();
		//设定布局
		this.setSize(500, 360);
		this.setTitle("设置");
		this.setResizable(false);
		//设置窗口关闭事件
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		//设置窗口居中
		//获取屏幕尺寸
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimension = toolkit.getScreenSize();
		int screen_width = screenDimension.width;
		int screen_height = screenDimension.height;
		//为了在视觉上好一点，将y坐标加上一定值
		int x = (screen_width-this.getWidth())/2;
		int y = (screen_height-this.getHeight())/2-16;
		this.setLocation(x, y);
		
		this.setLayout(new BorderLayout());
		this.add(this.createMainPanel());
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
	}
	
	
	/**
	 * 主Panel，包含tabbedPane
	 * @return
	 */
	private JTabbedPane createMainPanel(){
		JTabbedPane jt = new JTabbedPane();
		jt.addTab("按键控制", this.createControlPanel());
		return jt;
	}
	/**
	 * 按钮的Panel
	 * @return
	 */
	private JPanel createButtonPanel(){
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.jbOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(writeKeyCfg()){
					setVisible(false);
					//刷新以使游戏界面获取焦点
					jpanelGame.repaint();
				}
			}
		});
		this.jbCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cancelEvent();
			}
		});
		jp.add(this.jbOK);
		jp.add(this.jbCancel);
		return jp;
	}
	/**
	 * 按键控制设置的Panel
	 * @return
	 */
	private JPanel createControlPanel(){
		JPanel jp = new JPanel();
		jp.setLayout(null);
		for(int i=0; i< textctls.length; i++){
			jp.add(textctls[i]);
		}
		return jp;
	}
	/**
	 * 初始化文本框内容
	 */
	public void initText(){
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(KEYPATH));
			keyToActionString = (HashMap<Integer, String>)ois.readObject();
			Set<Entry<Integer, String>> entry = keyToActionString.entrySet();
			for(Entry<Integer, String> e:entry){
				for(TextCtl tc : textctls){
					if(tc.getMenthod().equals(e.getValue())){
						tc.setKeyCode(e.getKey());
						tc.setKeyCahrByCode(e.getKey());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void cancelEvent(){
		this.setVisible(false);
		jpanelGame.repaint();
	}
	public boolean writeKeyCfg(){
		keyToActionString = new HashMap<Integer, String>();
		for(int i=0; i<textctls.length; i++){
			keyToActionString.put(textctls[i].getKeyCode(), textctls[i].getMenthod());
		}
		ObjectOutputStream oos = null;
		try{
			oos = new ObjectOutputStream(new FileOutputStream(KEYPATH));
			oos.writeObject(keyToActionString);
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
		finally{
			try {
				oos.close();
				//更新按键设置
				this.gameCtl.getKeyConfig();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
