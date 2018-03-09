
import com.BackEnd.*;

import magick.ImageType;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Test;

public class CollageImageTest {

	CollageImage ci = new CollageImage();
	
	@Test
	public void testGetSetWidth() {
		ci.setWidth(10);
		assertEquals(ci.getWidth(), 10);
	}

	@Test
	public void testGetSetHeight() {
		ci.setHeight(10);
		assertEquals(ci.getHeight(), 10);
	}

	@Test
	public void testGetSetImage() {
		BufferedImage bi = new BufferedImage(800, 600, ImageType.GrayscaleMatteType);
		ci.setImage(bi);
		assertEquals(bi, ci.getImage());
	}

}
