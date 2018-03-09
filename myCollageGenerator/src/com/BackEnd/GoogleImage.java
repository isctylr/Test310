package com.BackEnd;
import java.awt.image.BufferedImage;

public class GoogleImage {
	// TODO: standardize casing
	// REPORT: standardized casing will not match class diagram
	private String imageType;
	private String urlLink;
	private BufferedImage Image;
	
	public String getImageType() {
		return imageType;
	}
	
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	public String getUrlLink() {
		return urlLink;
	}
	
	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}
	
	public BufferedImage getImage()
	{
		return Image;
	}
	
	public void setImage(BufferedImage image)
	{
		this.Image = image;
	}
}
