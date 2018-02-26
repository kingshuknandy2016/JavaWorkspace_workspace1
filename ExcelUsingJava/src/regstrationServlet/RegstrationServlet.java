package regstrationServlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spi.WriteFunction.WriteFunction;



/**
 * Servlet implementation class RegstrationServlet
 */
public class RegstrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String fname,lname,email;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegstrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	/*	System.out.println("welcome");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String email=request.getParameter("email");
		PrintWriter out=response.getWriter();
		System.out.println("middle");
		out.println("Name----"+ fname +"" + lname+" "+ " email---" + email);
		WriteToExcel.customWrite(fname, lname, email);*/
		//WriteFunction.write(fname, lname, email);
	}
	/*public void write() throws IOException{
		System.out.println("inside write");
		String file="C:/Users/ABHI/workspace/chars.txt";
		PrintWriter out=new PrintWriter(new FileWriter(file));
		out.println(88);
		out.close();
		System.out.println("inside excel");
		File exlFile = new File("C:/Users/ABHI/workspace/Basis.xlsx");
		//FileInputStream fis= new FileInputStream(exlFile);
		FileInputStream fi = new FileInputStream(exlFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fi);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Row row = sheet.getRow(2);
		Cell cell = row.createCell(0);
		Cell cell1=row.createCell(1);
		Cell cell2=row.createCell(2);
		cell.setCellType(cell.CELL_TYPE_STRING);
		cell.setCellValue(fname);
		cell1.setCellValue(lname);
		cell2.setCellValue(email);
		System.out.println("Succesfully done");
		FileOutputStream fos = new FileOutputStream(new File("C:/Users/ABHI/workspace/Basis.xlsx"));
		workbook.write(fos);


	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("welcome");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String email=request.getParameter("email");
		PrintWriter out=response.getWriter();
		System.out.println("middle");
		out.println("Name----"+ fname +"" + lname+" "+ " email---" + email);
		WriteToExcel.customWrite(fname, lname, email);
	}

}
