package me.djroxzhiaJGE.graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JFrame;

public class Window {
	
	public static int width, height;
	private JFrame m_frame;
	private Canvas m_canvas;

	public Window(int width, int height, String title, boolean isResizable, Image image)
	{
		Window.width = width;
		Window.height = height;
		
		m_frame = new JFrame(title);
		m_frame.setSize(width, height);
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_frame.setResizable(isResizable);
		m_frame.setLocationRelativeTo(null);
		m_frame.setIconImage(image);
		m_frame.setVisible(true);
		
		m_canvas = new Canvas();
		m_canvas.setPreferredSize(new Dimension(width, height));
		m_canvas.setMaximumSize(new Dimension(width, height));
		m_canvas.setMinimumSize(new Dimension(width, height));
		m_canvas.setFocusable(false);
		
		m_frame.add(m_canvas);
		m_frame.pack();
	}
	
	public JFrame getFrame()
	{
		return m_frame;
	}
	
	public Canvas getCanvas()
	{
		return m_canvas;
	}
}
