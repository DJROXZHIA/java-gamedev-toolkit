package me.djroxzhiaJGE.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {

	private boolean leftPressed, rightPressed, middlePressed;
	private boolean mouseWheelUp, mouseWheelDown;
	private int mouseX, mouseY;
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation() < 0)
		{
			mouseWheelUp = true;
			mouseWheelDown = false;
		} else if(e.getWheelRotation() > 0) 
		{
			mouseWheelUp = false;
			mouseWheelDown = true;
		}
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}


	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		else if(e.getButton() == MouseEvent.BUTTON2)
			middlePressed = true;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		else if(e.getButton() == MouseEvent.BUTTON2)
			middlePressed = false;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public int getMouseX()
	{
		return mouseX;
	}
	
	public int getMouseY()
	{
		return mouseY;
	}
	
	public boolean isMouseWheelUp()
	{
		return mouseWheelUp;
	}
	
	public boolean isMouseWheelDown()
	{
		return mouseWheelDown;
	}
	
	public boolean isMouseLeftClick()
	{
		return leftPressed;
	}
	
	public boolean isMouseRightClick()
	{
		return rightPressed;
	}
	
	public boolean isMouseMiddleClick()
	{
		return middlePressed;
	}
}
