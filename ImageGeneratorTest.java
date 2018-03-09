package com.BackEnd.tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.BackEnd.ImageGenerator;

import magick.MagickException;

public class ImageGeneratorTest {

	private boolean contains(String arr[], String key )
	{
		boolean ret=false;
		for(int i=0;i<arr.length;++i)
		{
			
			if(arr[i].equals(key)) {
				ret=true;
				return ret;
			}
				
		}
		return ret;
		
	}
	
	
	
	@Test
	public void testBuildCollage() {
		try {
			ImageGenerator.BuildCollage("dog");
		} catch (MagickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File myFiles= new File("gimages/dog");
		int numFiles=myFiles.listFiles().length;
		
		assertTrue(numFiles==31);
		assertTrue(contains(myFiles.list(),"output.png"));
		
		for(int i=1;i<31;++i)
		{
			assertTrue(contains(myFiles.list(),"saved"+Integer.toString(i-1)+".png"));
		}
		
		
	}

	@Test
	public void testConvertToPngFile() {
		String res=ImageGenerator.ConvertToPngFile();
		assertTrue(res.equals("data:image/png;base64,"));
	}

}
