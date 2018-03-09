package com.BackEnd;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import javax.xml.bind.DatatypeConverter;

import java.io.*;
import java.awt.*;
import magick.*;
import java.net.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.io.Reader.*;

import java.util.List;
import java.util.Random;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.google.gson.Gson;


public class ImageGenerator {
	static String MALFORMED_URL_EXCEPTION_RESPONSE = "That's not a good URL!";
	static String URI_SYNTAX_EXCEPTION_RESPONSE = "The URI was worded improperly.";
	static String IO_EXCEPTION_RESPONSE = "There was an error with the input.";
	static String MISHANDLED_IMAGE_RESPONSE = "Unable to get image: ";
	static String QUERY_FOUND_IN_CACHE_RESPONSE = " was found in the cache.";
	static String IMAGE_CACHE_LOCATION = "gimages/";
	static String INDIVIDUAL_IMAGE_NAME = "saved";
	static String IMAGE_TYPE = "png";
	static int NUM_IMAGES_PULL = 30;
	static String IMAGE_OUTPUT_NAME = "output";

	static String IMAGE_BORDER_COLOR = "white";
	static Rectangle IMAGE_BORDER_WIDTH = new Rectangle(3, 3, 3, 3);
	static int IMAGE_RANDOM_ROTATION = 45;
	static int COLLAGE_IMAGE_OFFSET	= 100;

	static int BLACK_PIXEL_INT = 0;
	static int KEY_PIXEL_INT_1 = 231;
	static int KEY_PIXEL_INT_2 = 255;
	static int KEY_PIXEL_INT_3 = 137;
	static int TRANSPARENT_PIXEL_INT = 65535;
	static PixelPacket KEY_PIXEL = new PixelPacket(KEY_PIXEL_INT_1, KEY_PIXEL_INT_2, KEY_PIXEL_INT_3, TRANSPARENT_PIXEL_INT);
	static PixelPacket TRANSPARENT_PIXEL = new PixelPacket(KEY_PIXEL_INT_1, KEY_PIXEL_INT_2, KEY_PIXEL_INT_3, BLACK_PIXEL_INT);

	static String GOOGLE_API_KEY = "AIzaSyCNhCOrApS5QZqxFiEXR7mMHThxDClkFxk";
	static String GOOGLE_API_CX  = "011031421419325587491:wp7q_caj7u4";
	static int GOOGLE_START_INDEX = 1;
	static String GOOGLE_SEARCH_TYPE = "image";
	static int GOOGLE_IMAGES_PER_QUERY = 10;
	static String GOOGLE_QUERY_CONSTANTS = "&filter=1&googlehost=google.com&imgSize=medium&alt=json";

	static boolean DEBUG = false;
	
	
	
	CollageImage mImage;
	String PngFilePath;
	
	static public void BuildCollage(String a) throws MagickException
	{
		String q;
		
			q =a;
		
		if (DEBUG)
			System.out.println("Searching for query: " + q);
		// These will be params too, sent from the browser viewport.
		int width = 800;
		int height = 600;

		try {
			Search(q);
		} catch(MalformedURLException mre) {
			System.err.println(MALFORMED_URL_EXCEPTION_RESPONSE);
			return;
		} catch(URISyntaxException use) {
			System.err.println(URI_SYNTAX_EXCEPTION_RESPONSE);
			return;
		} catch(IOException ioe) {
			System.err.println(IO_EXCEPTION_RESPONSE);
			return;
		}
		
		ImageInfo info = new ImageInfo(IMAGE_CACHE_LOCATION + q + "/" + INDIVIDUAL_IMAGE_NAME + "0." + IMAGE_TYPE);
		MagickImage image = new MagickImage(info);

		// Set the first image as a background for the collage, to the requested width/height.
		image = image.scaleImage(width, height);

		// Insert the random number generator for random values such as image location, rotation, size.
		Random rand = new Random(); 

		int locationX = -(COLLAGE_IMAGE_OFFSET);
		int locationY = -(COLLAGE_IMAGE_OFFSET);

		// 2x1 unravelled loop... starts at edges and works inward so the middle images are on top.
		for (int i = 1; i <= NUM_IMAGES_PULL/2; i++) 
		{

			/* First image */
			ImageInfo infoFront = new ImageInfo(IMAGE_CACHE_LOCATION + q + "/" + INDIVIDUAL_IMAGE_NAME + i + "." + IMAGE_TYPE);
			MagickImage imageFront = new MagickImage(infoFront);

			// Randomly resize image
			float randomScale = (height + rand.nextInt(height/4)-height/8)/(float)height;
			float scaleHeight = imageFront.getDimension().height * randomScale;
			float scaleWidth = imageFront.getDimension().width * randomScale;
			imageFront = imageFront.scaleImage((int)scaleWidth, (int)scaleHeight);

			// Add image border
			imageFront.setBorderColor(PixelPacket.queryColorDatabase(IMAGE_BORDER_COLOR));
		    	imageFront = imageFront.borderImage(IMAGE_BORDER_WIDTH);

			// Rotate image randomly between +45, -45 deg
			int randomRotate = rand.nextInt(IMAGE_RANDOM_ROTATION * 2) - IMAGE_RANDOM_ROTATION;
			imageFront.setBackgroundColor(KEY_PIXEL);
	    		imageFront.transparentImage(TRANSPARENT_PIXEL, TRANSPARENT_PIXEL_INT);
			imageFront = imageFront.rotateImage(randomRotate);

/*
			// Place the image in a random location on the canvas.
			int randomLocationX = rand.nextInt(width)-rand.nextInt(i);
			int randomLocationY = rand.nextInt(height)-rand.nextInt(i);
			
			// TODO: Random locations can be kind of ugly. Maybe instead do set locations...
			image.compositeImage(CompositeOperator.AtopCompositeOp, imageFront, randomLocationX, randomLocationY);
*/
			// Designate a location on the collage board.
			if (i != 1)
			{
				if ((i-1)%6 == 0) 
				{
					locationY += (height)/5;
					locationX = -100;
				}
				else 
				{
					locationX += (width-10)/6;
				}
			}
			// Place image on the canvas
			image.compositeImage(CompositeOperator.AtopCompositeOp, imageFront, locationX, locationY);
			if (DEBUG)
				System.out.println("Placing image " + i);

			/* last image */
			// If there's an even number of images, don't draw the middle image twice.
			if (i != NUM_IMAGES_PULL/2 || NUM_IMAGES_PULL%2 != 0)
			{ 
				ImageInfo infoBack = new ImageInfo(IMAGE_CACHE_LOCATION + q + "/" + INDIVIDUAL_IMAGE_NAME + Integer.toString(NUM_IMAGES_PULL - i) + "." + IMAGE_TYPE);
				MagickImage imageBack = new MagickImage(infoBack);

				// Randomly resize image
				randomScale = (height + rand.nextInt(height/4)-height/8)/(float)height;
				scaleHeight = imageFront.getDimension().height * randomScale;
				scaleWidth = imageFront.getDimension().width * randomScale;
				imageBack = imageBack.scaleImage((int)scaleWidth, (int)scaleHeight);

				// Add image border
				imageBack.setBorderColor(PixelPacket.queryColorDatabase(IMAGE_BORDER_COLOR));
			    	imageBack = imageBack.borderImage(IMAGE_BORDER_WIDTH);

				// Rotate image randomly between +45, -45 deg
				randomRotate = rand.nextInt(IMAGE_RANDOM_ROTATION * 2) - IMAGE_RANDOM_ROTATION;
				imageBack.setBackgroundColor(KEY_PIXEL);
	    			imageBack.transparentImage(TRANSPARENT_PIXEL, TRANSPARENT_PIXEL_INT);
				imageBack = imageBack.rotateImage(randomRotate);

				// Place image on the canvas
				image.compositeImage(CompositeOperator.AtopCompositeOp, imageBack, width-(3*COLLAGE_IMAGE_OFFSET)-locationX, height-(3*COLLAGE_IMAGE_OFFSET)-locationY);
				if (DEBUG)
					System.out.println("Placing image " + Integer.toString(30-i));
			}
		}
		
		image.setFileName(IMAGE_CACHE_LOCATION + q + "/" + IMAGE_OUTPUT_NAME + "." + IMAGE_TYPE);
		image.writeImage(info);
		
	}
	
	static public String ConvertToPngFile()
	{
	     ByteArrayOutputStream baos = new ByteArrayOutputStream();
//	     ImageIO.write(mImage.getImage(), "png", baos);
	     String data = DatatypeConverter.printBase64Binary(baos.toByteArray());
	     String imageString = "data:image/png;base64," + data;
	     return imageString;
	}
	
	
	static void Search(String query)  throws MalformedURLException, URISyntaxException, IOException 
	{

		if (checkCache(query)) {
			return;
		}

		
		int totalSuccess = 0;

		for (int start = GOOGLE_START_INDEX; totalSuccess < NUM_IMAGES_PULL; start=start+GOOGLE_IMAGES_PER_QUERY) 
		{
			URL url = new URL ("https://www.googleapis.com/customsearch/v1?key=" + GOOGLE_API_KEY + "&cx=" + GOOGLE_API_CX + "&q=" + query +"&num=" + GOOGLE_IMAGES_PER_QUERY + "&start=" + Integer.toString(start) + "&fileType=" + IMAGE_TYPE + "&searchType=" + GOOGLE_SEARCH_TYPE + GOOGLE_QUERY_CONSTANTS);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			BufferedReader br = new BufferedReader(new InputStreamReader ( ( conn.getInputStream() ) ) );
			GResults results = new Gson().fromJson(br, GResults.class);
			conn.disconnect();

			if (DEBUG)
				System.out.println("Total results: " + results.searchInformation.formattedTotalResults);

			if (results.searchInformation.totalResults < NUM_IMAGES_PULL) return;

			for (int i=0; i < GOOGLE_IMAGES_PER_QUERY; i++) 
			{
				String path  = results.getLink(i);
				try 
				{
				    	BufferedImage dlImage = ImageIO.read(new URL(path));
					File outputfile = new File(IMAGE_CACHE_LOCATION + query + "/" + INDIVIDUAL_IMAGE_NAME + totalSuccess + "." + IMAGE_TYPE);
					boolean success = outputfile.mkdirs();
		    			ImageIO.write(dlImage, IMAGE_TYPE, outputfile);
					if (++totalSuccess == NUM_IMAGES_PULL) break;
				} catch (IOException ioe) {
					System.err.println(MISHANDLED_IMAGE_RESPONSE + path);
				} catch (IllegalArgumentException iae) {
					System.err.println("IDK"); //TODO
				}	
			}
		}
	}

	static boolean checkCache(String name)
	{
		String cache = IMAGE_CACHE_LOCATION.replace("/", "");
		File file = new File(cache);
		File[] list = file.listFiles();
		if(list!=null) 
		{
			for (File fil : list)
			{
				if (name.equalsIgnoreCase(fil.getName()))
				{
					System.out.println(fil.getName() + QUERY_FOUND_IN_CACHE_RESPONSE);
					return true;
				}
			}
		}
		return false;
	}
	
	
	
		
	
}



class GResults 
{

	  public String link;
	  public String htmlFormattedUrl;

	  public SearchInformation searchInformation;

	  public List<GResults> items;

	  public String getLink() 
	  {
	    return link;
	  }

	  public String getUrl() 
	  {
	    return htmlFormattedUrl;
	  }

	  public void setUrl(String htmlFormattedUrl) 
	  {
	    this.htmlFormattedUrl = htmlFormattedUrl;
	  }

	  public List<GResults> getItems() 
	  {
	    return items;
	  }

	  public void setLink(String link) 
	  {
	    this.link = link;
	  }

	  public void setGroups(List<GResults> items) 
	  {
	    this.items = items;
	  }

	  public void getThing (int i) 
  	  {
	    System.out.println(items.get(i));
	  }

	  public String getLink(int i) 
	  {
	    return items.get(i).toString();
	  }

	  public String toString() 
	  {
	    return String.format("%s", link);
	  }
}

class SearchInformation 
{
	public String searchTime;
	public String formattedSearchTime;
	long totalResults;
	public String formattedTotalResults;
}



