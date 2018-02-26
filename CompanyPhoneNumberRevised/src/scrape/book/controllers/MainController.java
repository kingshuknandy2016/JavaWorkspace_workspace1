package scrape.book.controllers;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrape.book.numbers.RegExpCollectPhoneNumberRevi;

/*import scrape.book.numbers.RegExpCollectPhoneNumberRevi;
import scrape.phone.numbers.RegExpCollectPhoneNumber;*/

/**
 * Servlet implementation class MainController
 */
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
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
		//RegExpCollectPhoneNumberRevised.Scraper(Company, Company);
		String PhNo=RegExpCollectPhoneNumberRevi.Scraper(Company, Country);	
		System.out.println("ph no"+PhNo);
	    request.setAttribute("Cname", Country);
	    request.setAttribute("Pno", PhNo);
	    System.out.println("request properties set");
	    RequestDispatcher rd=request.getRequestDispatcher("table.jsp");
	    rd.forward(request, response);
	}

}
