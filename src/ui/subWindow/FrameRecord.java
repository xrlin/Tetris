package ui.subWindow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.JPanelGame;
import control.GameControler;

public class FrameRecord extends JFrame {
	private static final Font DEF_FONT = new Font("黑体", Font.BOLD, 25);
	private GameControler gameCtl;
	private JPanelGame jplGame;
	private JPanel jp1;
	private JPanel jp2;
	private JPanel jp3;
	private JButton jbOk;
	private JLabel jlName;
	private JTextField jtName;
	private JLabel jlScore;

	public FrameRecord(JPanelGame jplGame, GameControler gameCtl) {
		this.jplGame = jplGame;
		this.gameCtl = gameCtl;
		this.setLayout(new BorderLayout());
		this.setTitle("分数记录");
		this.setResizable(false);
		this.setSize(400, 200);
		// 设置窗口居中
		// 获取屏幕尺寸
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimension = toolkit.getScreenSize();
		int screen_width = screenDimension.width;
		int screen_height = screenDimension.height;
		// 为了在视觉上好一点，将y坐标加上一定值
		int x = (screen_width - this.getWidth()) / 2;
		int y = (screen_height - this.getHeight()) / 2 - 16;
		this.setLocation(x, y);
		this.initComponent();
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(true);

	}

	private void initComponent() {
		this.jp1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.jp2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.jp3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.jlScore = new JLabel("分数："
				+ this.gameCtl.getGameService().dto.getNowPoint());
		jlScore.setFont(DEF_FONT);
		jlScore.setSize(5, 10);
		this.jlName = new JLabel("敢问尊姓大名：");
		jlName.setFont(DEF_FONT);
		this.jtName = new JTextField(10);
		jtName.setFont(DEF_FONT);
		this.jbOk = new JButton("确定");
		this.jbOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateData();
			}
		});
		jp1.add(jlScore);
		jp2.add(jlName);
		jp2.add(jtName);
		jp3.add(jbOk);
		this.add(jp1, BorderLayout.NORTH);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

	}

	public void updateData() {
		if (this.jtName.getText() == null)
			return;
		System.out.println(111);
		this.gameCtl.updateDataRecord(this.jtName.getText(),
					this.gameCtl.getGameService().dto.getNowPoint());
		this.setVisible(false);
		// 重绘使主游戏窗口获得焦点
		this.jplGame.repaint();
	}

}
