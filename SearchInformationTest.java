
import com.BackEnd.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SearchInformationTest {

	@Test
	public void test() {
		SearchInformation si = new SearchInformation();
		si.searchTime = "0.5";
		si.formattedSearchTime = "0.5s";
		si.formattedTotalResults = "1,000,000";
		assertNotNull(si);
		assertEquals(si.searchTime, "0.5");
		assertEquals(si.formattedSearchTime, "0.5s");
		assertEquals(si.formattedTotalResults, "1,000,000");
	}

}
