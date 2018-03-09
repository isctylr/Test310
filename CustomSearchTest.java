

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import com.BackEnd.*;


public class CustomSearchTest {

	@Test
	public void testPullImages() {
		// Hard to test something that isn't implemented...
		CustomSearch cs = new CustomSearch();
		ArrayList<GoogleImage> list = cs.PullImages("puppy");
		assertNull(list);
	}

}
