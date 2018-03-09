package com.BackEnd.tests;

import org.springframework.mock.web.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.junit.Test;

import com.BackEnd.CollageGenerator;

public class CollageGeneratorTest {
	
	

	@Test
	public void testCallServletCachedQuery() {

		CollageGenerator mCollageGenerator= new CollageGenerator();
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		request.setParameter("SearchBox", "dog");
		
		try {
			mCollageGenerator.doGet(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(response.getRedirectedUrl()=="CollagePage.jsp");
		ArrayList<String> queries=(ArrayList<String>)request.getSession().getAttribute("queryList");
		String query= (String)request.getSession().getAttribute("query");
		assertTrue(query=="dog");
		assertTrue(queries.size()==1);
		assertTrue(queries.get(0)=="dog");
		File myFiles= new File("gimages/dog");
		int numFiles=myFiles.listFiles().length;
		
		assertTrue(numFiles==31);
		assertTrue(myFiles.list()[0]==(System.getProperty("user.dir")+"/gimages/dog/output.png"));
		
		for(int i=1;i<31;++i)
		{
			assertTrue(myFiles.list()[i]==(System.getProperty("user.dir")+"/gimages/dog/dog"+Integer.toString(i-1)+".png"));
		}
	
		
		//fail("Not yet implemented");
	}

	

	

	@Test
	public void testDoGetEmptyStringSearch() {

		CollageGenerator mCollageGenerator= new CollageGenerator();
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setParameter("SearchBox", "");
		
		try {
			mCollageGenerator.doGet(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		assertTrue(response.getRedirectedUrl()==null);
		assertTrue(request.getSession().getAttribute("queryList")==null);
		assertTrue(request.getSession().getAttribute("query")==null);
	
	}
	
	@Test
	public void testDoGetMultipleSearchesSameSession() {
	CollageGenerator mCollageGenerator= new CollageGenerator();
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		request.setParameter("SearchBox", "dog");
		
		try {
			mCollageGenerator.doGet(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setParameter("SearchBox", "dog");
		
		try {
			mCollageGenerator.doGet(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		assertTrue(response.getRedirectedUrl()=="CollagePage.jsp");
		ArrayList<String> queries=(ArrayList<String>)request.getSession().getAttribute("queryList");
		String query= (String)request.getSession().getAttribute("query");
		assertTrue(query=="dog");
		assertTrue(queries.size()==1);
		assertTrue(queries.get(0)=="dog");
		File myFiles= new File("gimages/dog");
		int numFiles=myFiles.listFiles().length;
		
		assertTrue(numFiles==31);
		assertTrue(myFiles.list()[0]==(System.getProperty("user.dir")+"/gimages/dog/output.png"));
		
		for(int i=1;i<31;++i)
		{
			assertTrue(myFiles.list()[i]==(System.getProperty("user.dir")+"/gimages/dog/dog"+Integer.toString(i-1)+".png"));
		}
	
	}
	
	@Test
	public void testDoGetMultipleUniqueSearchesSameSession() {
	CollageGenerator mCollageGenerator= new CollageGenerator();
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		request.setParameter("SearchBox", "dog");
		
		try {
			mCollageGenerator.doGet(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setParameter("SearchBox", "cat");
		
		try {
			mCollageGenerator.doGet(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		assertTrue(response.getRedirectedUrl()=="CollagePage.jsp");
		ArrayList<String> queries=(ArrayList<String>)request.getSession().getAttribute("queryList");
		String query= (String)request.getSession().getAttribute("query");
		assertTrue(query=="cat");
		assertTrue(queries.size()==2);
		assertTrue(queries.get(0)=="dog");
		assertTrue(queries.get(1)=="cat");
		File myFiles= new File("gimages/dog");
		int numFiles=myFiles.listFiles().length;
		
		assertTrue(numFiles==31);
		assertTrue(myFiles.list()[0]==(System.getProperty("user.dir")+"/gimages/dog/output.png"));
		
		for(int i=1;i<31;++i)
		{
			assertTrue(myFiles.list()[i]==(System.getProperty("user.dir")+"/gimages/dog/dog"+Integer.toString(i-1)+".png"));
		}
		
		File myFiles2= new File("gimages/cat");
		int numFiles2=myFiles2.listFiles().length;
		
		assertTrue(numFiles2==31);
		assertTrue(myFiles2.list()[0]==(System.getProperty("user.dir")+"/gimages/cat/output.png"));
		
		for(int i=1;i<31;++i)
		{
			assertTrue(myFiles2.list()[i]==(System.getProperty("user.dir")+"/gimages/cat/cat"+Integer.toString(i-1)+".png"));
		}
	
	}
	
	@Test
	public void testDoGetUncachedSearches() {
	CollageGenerator mCollageGenerator= new CollageGenerator();
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		request.setParameter("SearchBox", "houses");
		
		try {
			mCollageGenerator.doGet(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		assertTrue(response.getRedirectedUrl()=="CollagePage.jsp");
		ArrayList<String> queries=(ArrayList<String>)request.getSession().getAttribute("queryList");
		String query= (String)request.getSession().getAttribute("query");
		assertTrue(query=="houses");
		assertTrue(queries.size()==1);
		assertTrue(queries.get(0)=="houses");
		
		File myFiles= new File("gimages/houses");
		int numFiles=myFiles.listFiles().length;
		
		assertTrue(numFiles==31);
		assertTrue(myFiles.list()[0]==(System.getProperty("user.dir")+"/gimages/houses/output.png"));
		
		for(int i=1;i<31;++i)
		{
			assertTrue(myFiles.list()[i]==(System.getProperty("user.dir")+"/gimages/houses/houses"+Integer.toString(i-1)+".png"));
		}
		
	
	}


}
