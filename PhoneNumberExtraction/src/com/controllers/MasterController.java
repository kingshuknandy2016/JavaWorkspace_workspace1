package com.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BAO.RegExpCollectPhoneNumberRevi;

/**
 * Servlet implementation class MasterController
 */
public class MasterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("check...................");
		String Company=request.getParameter("field1");
		String Country=request.getParameter("field2");
		System.out.println(Company+Country);
		
		String PHONENUMBER=RegExpCollectPhoneNumberRevi.Scraper(Company, Country);
		System.out.println("Extracted No:"+PHONENUMBER);
		
		request.setAttribute("Cname", Country);
	    request.setAttribute("Pno", PHONENUMBER);
	    RequestDispatcher rd=request.getRequestDispatcher("output.jsp");
	    rd.forward(request, response);
	}

}
