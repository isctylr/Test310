package com.BackEnd.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.BackEnd.CollageGenerator;
import com.BackEnd.FileHost;

public class FileHostTest {

	@Test
	public void testFileHostDoGet() {
		FileHost mFileHost= new FileHost();
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setPathInfo("gimages/dog/output.png");
		
		try {
			mFileHost.doGet(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(response.getHeader("Content-Type"));
		System.out.println(response.getHeader("Content-Length"));
		System.out.println(response.getHeader("Content-Disposition"));
		//assertTrue(response.getHeader(name))
		
		
	}

	

}
