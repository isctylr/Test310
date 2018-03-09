package com.BackEnd.tests;

import org.springframework.mock.web.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.junit.Test;

import com.BackEnd.CollageGenerator;

public class CollageGeneratorTest {
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
		assertTrue(contains(myFiles.list(),"output.png"));
		
		for(int i=1;i<31;++i)
		{
			assertTrue(contains(myFiles.list(),"saved"+Integer.toString(i-1)+".png"));
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
		MockHttpServletResponse response2 = new MockHttpServletResponse();
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
			mCollageGenerator.doGet(request, response2);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		assertTrue(response.getRedirectedUrl()=="CollagePage.jsp");
		assertTrue(response2.getRedirectedUrl()=="CollagePage.jsp");
		ArrayList<String> queries=(ArrayList<String>)request.getSession().getAttribute("queryList");
		String query= (String)request.getSession().getAttribute("query");
		assertTrue(query=="dog");
		assertTrue(queries.size()==1);
		assertTrue(queries.get(0)=="dog");
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
	public void testDoGetMultipleUniqueSearchesSameSession() {
	CollageGenerator mCollageGenerator= new CollageGenerator();
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpServletResponse response2 = new MockHttpServletResponse();
		
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
			mCollageGenerator.doGet(request, response2);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		assertTrue(response.getRedirectedUrl()=="CollagePage.jsp");
		assertTrue(response2.getRedirectedUrl()=="CollagePage.jsp");
		ArrayList<String> queries=(ArrayList<String>)request.getSession().getAttribute("queryList");
		String query= (String)request.getSession().getAttribute("query");
		assertTrue(query=="cat");
		assertTrue(queries.size()==2);
		assertTrue(queries.get(0)=="dog");
		assertTrue(queries.get(1)=="cat");
		File myFiles= new File("gimages/dog");
		int numFiles=myFiles.listFiles().length;
		
		assertTrue(numFiles==31);
		
		assertTrue(contains(myFiles.list(),"output.png"));
		
		for(int i=1;i<31;++i)
		{
			assertTrue(contains(myFiles.list(),"saved"+Integer.toString(i-1)+".png"));
		}
		
		File myFiles2= new File("gimages/cat");
		int numFiles2=myFiles2.listFiles().length;
		
		assertTrue(numFiles2==31);
		File myFilter= new File("output.png");
		assertTrue(contains(myFiles2.list(), "output.png"));
		
		
		
		
		
		for(int i=1;i<31;++i)
		{
			
			assertTrue(contains(myFiles2.list(),"saved"+Integer.toString(i-1)+".png" ));
		}
	
	}
	
	@Test
	public void testDoGetUncachedSearches() {
	CollageGenerator mCollageGenerator= new CollageGenerator();
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		request.setParameter("SearchBox", "house");
		
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
		assertTrue(query=="house");
		assertTrue(queries.size()==1);
		assertTrue(queries.get(0)=="house");
		
		File myFiles= new File("gimages/house");
		int numFiles=myFiles.listFiles().length;

		assertTrue(numFiles==31);
		
		assertTrue(contains(myFiles.list(), "output.png"));
		
		for(int i=1;i<31;++i)
		{
			assertTrue(contains(myFiles.list(), "saved"+Integer.toString(i-1)+".png"));
		}
		
	
	}


}
