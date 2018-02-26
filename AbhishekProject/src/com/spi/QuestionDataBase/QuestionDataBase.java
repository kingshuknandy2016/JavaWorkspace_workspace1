package com.spi.QuestionDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuestionDataBase {
	
	static String driver, url, user, password;

	static{
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/questions";
		user = "root";
		password = "root";
	}

	private static Connection getConnection() throws ClassNotFoundException,SQLException {
		Class.forName(driver);
		return DriverManager.getConnection(url, user, password);
	}

	static Connection con;
	static Statement st;

	static java.sql.PreparedStatement pst;
	static ResultSet rs;
	
	public List Questions(){
		String qry = "Select *from question_bank";
		List list1 = new ArrayList ();
		QuestionAnswer qa;

		try {
			pst = getConnection().prepareStatement(qry);
			rs = pst.executeQuery();
			while(rs.next()) {
				
				    qa=new QuestionAnswer();
					qa.setqId(rs.getInt("Qid"));
					qa.setQuestion(rs.getString("Qname"));
					list1.add(qa);
					//System.out.println("added to list");
			
			}
			

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list1;
		
	}
	
	public List Anwser(){
		String qry = "Select *from answer_bank";
		List list = new ArrayList ();
		QuestionAnswer qu;

		try {
			pst = getConnection().prepareStatement(qry);
			rs = pst.executeQuery();
			while(rs.next()) {
				    qu=new QuestionAnswer();
				    qu.setqId(rs.getInt("Qid"));
					qu.setOption1(rs.getString("option_1"));
					qu.setOption2(rs.getString("option_2"));
					qu.setOption3(rs.getString("option_3"));
					qu.setOption4(rs.getString("option_4"));
					qu.setRightOption(rs.getString("corroption"));
					System.out.println("---------------------------");
				    list.add(qu);
			}
			

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public void display(List QuesList,List AnsList){
		Iterator ite=QuesList.iterator();
		while(ite.hasNext()){
			QuestionAnswer que=(QuestionAnswer) ite.next();
			System.out.println("Question No"+que.getqId()+":"+que.getQuestion());
		}
		
		Iterator ite2=AnsList.iterator();
		while(ite2.hasNext()){
			QuestionAnswer que1=(QuestionAnswer) ite2.next();
			System.out.println("Option1:"+que1.getOption1()+"\t"+"Option2:"+que1.getOption2()+"\t"+"Option3:"+que1.getOption3()+"\t"+"Option4:"+que1.getOption4()+"\t"+"Correct Anwser:"+que1.getRightOption());
		}
	}
	
	
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*QuestionDataBase q=new QuestionDataBase();
		List  lis1 =q.Questions();
		List  lis2=q.Anwser();
		q.display(lis1,lis2);*/
		//q.Anwser();

	}

}
