package ui.subWindow;

import java.awt.TextField;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
/**
 * 实现按键设置的文本框类
 * @author archer
 *
 */
public class TextCtl extends TextField {
	
	private String menthod;
	
	private int keyCode;
	/**
	 * 构造函数
	 * 通过x,y,width,height参数设定文本框的位置、大小
	 * @param x 
	 * @param y
	 * @param width
	 * @param height
	 */
	public TextCtl(int x ,int y, int width, int height, String menthod) {
		this.menthod = menthod;
		this.setBounds(x, y, width, height);
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				keyCode = e.getKeyCode();
				setText(KeyEvent.getKeyText(e.getKeyCode()));
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				setText(null);
			}
		});
	}
	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}
	/**
	 *   根据keycode设置keychar
	 * @param keyCode
	 */
	public void setKeyCahrByCode(int keyCode) {
		this.setText(KeyEvent.getKeyText(keyCode));
	}
	/**
	 * 获取Keycode
	 * @return
	 */
	public int getKeyCode() {
		return keyCode;
	}
	public String getMenthod() {
		return menthod;
	}
}
