package ui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import ui.subWindow.FrameGameControl;
import config.ButtonConfig;
import config.FrameConfig;
import config.GameConfig;
import config.LayerConfig;
import control.PlayerControler;
import dto.GameDto;
/**
 * 管理布局和绘制窗口
 */
public class JPanelGame extends JPanel {
	
	/**
	 *  设置按钮图片
	 */
	private final ImageIcon CONFIG_ICON = new ImageIcon("graphics/string/config.png");
	/**
	 * 开始按钮图片
	 */
	private  final ImageIcon START_IMG = new ImageIcon("graphics/string/start.png");
	
	/** 
	 * 开始按钮
	 */
	public JButton startBtn = new JButton(START_IMG);
	/** 
	 * 设置按钮
	 */ 
	public JButton settingBtn = new JButton(CONFIG_ICON);
	/**
	 * player control
	 */
	private PlayerControler playerCtl;
	/**
	 * 游戏数据
	 */
	private GameDto dto;
	
	private ArrayList<Layer> layers = null;
	/*
	 * 构造函数
	 */
	public JPanelGame(GameDto dto) throws Exception{	
		this.dto = dto;
		this.initLayer();
		this.initCompont();
	}
	/**
	 * 建立游戏控制
	 */
	public void setPlayerControl(PlayerControler playerControl){
		this.playerCtl = playerControl;
		this.addKeyListener(playerControl);
	}
	/**
	 * 初始化组件
	 */
	private void initCompont() {
		///获取配置配置对象
		FrameConfig frameCfg = GameConfig.getFrameConfig();
		//按钮配置列表
		List<ButtonConfig> btnsCfg = frameCfg.getBtnsConfig();
		//TODO 如果想到按钮在其他layer中不显示的解决办法，将按钮写在layerbutton中 
		//必须设置布局为null，否则按钮不能改变位置与大小
		this.setLayout(null);
		//设置位置
		startBtn.setBounds(btnsCfg.get(0).getX(), btnsCfg.get(0).getY(), btnsCfg.get(0).getW(), btnsCfg.get(0).getH());
		settingBtn.setBounds(btnsCfg.get(1).getX(), btnsCfg.get(1).getY(), btnsCfg.get(1).getW(), btnsCfg.get(1).getH());
		//添加事件监听
		startBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startGame();
				
			}

		});
		settingBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerCtl.getGameControler().showSettingWindow();
			}
		});
		//添加按钮
		this.add(startBtn);
		this.add(settingBtn);
	}
	/**
	 * 初始化层
	 */
	private void initLayer() throws Exception {
		///获取配置配置对象
		FrameConfig frameCfg = GameConfig.getFrameConfig();
		//获得layer配置对象
		List<LayerConfig> layersConfig = frameCfg.getLayersConfig();
		//运用反射进行layer对象创建
		/*
		 *
		 * 窗口数组
		 */
		layers = new ArrayList<Layer>(layersConfig.size());
		
		for(LayerConfig layerCfg : layersConfig) {
			//获得类对象
			Class<?> c = Class.forName(layerCfg.getClassName());
			//创建构造函数
			Constructor<?> ctr = c.getConstructor(int.class ,int.class ,int.class, int.class);
			//调用构造函数创建对象
			Layer l = (Layer) ctr.newInstance(layerCfg.getX(),layerCfg.getY(),layerCfg.getWidth(),layerCfg.getHeight());
			//往每个layer中设置Dto对象
			l.setDto(this.dto);
			layers.add(l);
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		//为了避免游戏界面刷新时出现不可预知的错误，调用基类的方法进行初始化
		super.paintComponent(g);
		//循环绘制窗口
		for(int i=0;i<layers.size();i++)
			//刷新窗口
			layers.get(i).paint(g);
		//注意，必须取得焦点后才能监听事件
		this.requestFocusInWindow();
	}
	/**
	 * 游戏按钮状态切换
	 * @param onOff
	 */
	public void buttonSwitch(boolean onOff){
		if(!this.dto.isPause())
			//暂停状态下开始按钮仍然处于disable状态
			this.startBtn.setEnabled(onOff);
		this.settingBtn.setEnabled(onOff);
	}
	/**
	 * 开始游戏
	 */
	private void startGame() {
		//初始化游戏
		this.playerCtl.getGameControler().startGame();
		this.dto.setPause(false);
		this.repaint();
	}
}
	

