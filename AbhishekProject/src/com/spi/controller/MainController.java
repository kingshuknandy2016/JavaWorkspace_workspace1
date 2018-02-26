package com.spi.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spi.QuestionDataBase.QuestionAnswer;
import com.spi.QuestionDataBase.QuestionDataBase;

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
		
		System.out.println("+++++++++++++++++++++++++");
		QuestionDataBase qu=new QuestionDataBase();
		List queslist=qu.Questions();
		List anslist=qu.Anwser();
		
		for (int i = 0; i < queslist.size(); i++) {
			QuestionAnswer obj=(QuestionAnswer) queslist.get(i);
			QuestionAnswer obj2=(QuestionAnswer) anslist.get(i);
			System.out.println("--------"+obj.getqId()+obj.getQuestion());
			System.out.println("*************"+obj2.getqId()+"\t"+obj2.getRightOption());
			System.out.println("////////////////////////////////////////////////////////////");
			PrintWriter out=response.getWriter();
			out.println(obj.getqId()+obj.getQuestion());
			/*request.setAttribute("qno", obj.getqId());
			request.setAttribute("ques", obj.getQuestion());
			request.setAttribute("op1", obj2.getOption1());
			request.setAttribute("op2", obj2.getOption2());
			request.setAttribute("op3", obj2.getOption3());
			request.setAttribute("op4", obj2.getOption4());
			RequestDispatcher rd=request.getRequestDispatcher("questions.jsp");
			rd.forward(request, response);*/
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
