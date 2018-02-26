package com.pojo;

public class User {

	ExcelLibrary ex=new ExcelLibrary("");
	public static void main(String[] args) {
  	
	}
	
	public Employee getEmp(int rowNum){
		
		   Employee emp=new Employee();
		   emp.setName(ex.getCellData("input", rowNum, 1).toString());
		   emp.setDept(ex.getCellData("input", rowNum, 2).toString());
		   emp.setEmpID((Integer)ex.getCellData("input", rowNum, 3));
		   return emp;
	}
}
