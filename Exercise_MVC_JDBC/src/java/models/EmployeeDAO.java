/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


import DAL.DBContext;
import DAL.Employee;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class EmployeeDAO extends DBContext{
    public Employee getEmloyeeByID(int empID){
        Employee emp = null;
        try {
            String sql = "select * from Employees where EmployeeID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, empID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int EmployeeID = rs.getInt("EmployeeID");
                int DepartmentID = rs.getInt("DepartmentID");
                String LastName = rs.getString("LastName");
                String FirstName = rs.getString("FirstName");
                String Title = rs.getString("Title");
                String TitleOfCourtesy = rs.getString("TitleOfCourtesy");
                String Address = rs.getString("Address");
                Date BirthDate = rs.getDate("BirthDate");
                Date HireDate = rs.getDate("HireDate");
                
                emp = new Employee(EmployeeID, DepartmentID, LastName, FirstName, Title, TitleOfCourtesy, Address, BirthDate, HireDate);
            }
        } catch (Exception e) {
        }
        return emp;
    }
    
    public ArrayList<Employee> getAllEmloyees(){
        ArrayList<Employee> empList = new ArrayList<>();
        try {
            String sql = "select * from Employees";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int EmployeeID = rs.getInt("EmployeeID");
                int DepartmentID = rs.getInt("DepartmentID");
                String LastName = rs.getString("LastName");
                String FirstName = rs.getString("FirstName");
                String Title = rs.getString("Title");
                String TitleOfCourtesy = rs.getString("TitleOfCourtesy");
                String Address = rs.getString("Address");
                Date BirthDate = rs.getDate("BirthDate");
                Date HireDate = rs.getDate("HireDate");
                
                Employee emp = new Employee(EmployeeID, DepartmentID, LastName, FirstName, Title, TitleOfCourtesy, Address, BirthDate, HireDate);
                empList.add(emp);
            }
        } catch (Exception e) {
        }
        return empList;
    }
    
    public static void main(String[] args) {
        Employee emp = new EmployeeDAO().getEmloyeeByID(1);
        System.out.println(emp);
    }
}
