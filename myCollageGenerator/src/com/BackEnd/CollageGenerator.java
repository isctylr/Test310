package com.BackEnd;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.imageio.*;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.File;

@WebServlet("/CollageGenerator")
public class CollageGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public CollageGenerator() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s= request.getParameter("SearchBox");
		if(s.length()==0) {
			System.out.println("nothing inputted");
			return;
		}
		boolean res=false;
		HttpSession curr= request.getSession();
		ArrayList<String> mylist;
		if(curr.getAttribute("queryList")==null)
		{
			mylist=new ArrayList<String>();
			mylist.add(s);
		}
		else {
			mylist= (ArrayList<String>) curr.getAttribute("queryList");
			if(!mylist.contains(s)) {
				
				mylist.add(s);
			}
			else {
				res=true;
			}
		}
		
		curr.setAttribute("queryList", mylist);
		curr.setAttribute("query", s);
		
		
		//System.out.println("java.library.path is: " + System.getProperty("java.library.path"));
		if(!res) {
		try {
		ImageGenerator.BuildCollage(s);
		}
		catch(Exception e)
		{}
		}
		
		//System.out.println(System.getProperty("user.dir"));
		
		System.out.println(s);
		
		
		
		
		
		response.sendRedirect("CollagePage.jsp");
	}
}
