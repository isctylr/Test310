package com.BackEnd;

import java.awt.image.BufferedImage;

public class CollageImage {
	private int width;
	private int height;
	// REPORT: image was not named in the class diagram.
	private BufferedImage image;
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
