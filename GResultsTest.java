
import com.BackEnd.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GResultsTest {

	@Test
	public void testGetLink() {
		GResults gr = new GResults();
		gr.link = "http://localhost/";
		assertEquals(gr.getLink(), "http://localhost/");
	}

	@Test
	public void testGetUrl() {
		GResults gr = new GResults();
		gr.htmlFormattedUrl = "http://localhost/";
		assertEquals(gr.getUrl(), "http://localhost/");
	}

	@Test
	public void testSetUrl() {
		GResults gr = new GResults();
		gr.setUrl("http://localhost/");
		assertEquals(gr.htmlFormattedUrl, "http://localhost/");
	}

	@Test
	public void testGetItems() {
		GResults gr = new GResults();
		List<GResults> items = new ArrayList<GResults>();
		gr.items = items;
		assertNotNull(gr.getItems());
	}

	@Test
	public void testSetLink() {
		GResults gr = new GResults();
		gr.setLink("http://localhost/");
		assertEquals(gr.link, "http://localhost/");
	}

	@Test
	public void testSetGroups() {
		GResults gr = new GResults();
		List<GResults> groups = new ArrayList<GResults>();
		gr.setGroups(groups);
		assertNotNull(gr.items);
	}

	@Test
	public void testGetThing() {
		GResults gr = new GResults();
		List<GResults> items = new ArrayList<GResults>();
		gr.items = items;
		GResults gr2 = new GResults();
		items.add(gr2);
		items.get(0).link = "http://localhost";
		gr.getThing(0);
	}

	@Test
	public void testGetLinkInt() {
		GResults gr = new GResults();
		List<GResults> items = new ArrayList<GResults>();
		gr.items = items;
		GResults gr2 = new GResults();
		items.add(gr2);
		items.get(0).link = "http://localhost";
		assertEquals(gr.getLink(0), "http://localhost");
	}

	@Test
	public void testToString() {
		GResults gr = new GResults();
		gr.link = "http://localhost";
		assertEquals(gr.toString(), "http://localhost");
	}

}
