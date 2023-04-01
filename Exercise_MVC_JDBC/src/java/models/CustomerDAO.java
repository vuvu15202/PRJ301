/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import DAL.Account;
import DAL.Customer;
import DAL.DBContext;
import DAL.Order;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class CustomerDAO extends DBContext{
    public Customer getCustomerByEmail(String email){
        Customer cus = null;
        try {
            String sql = "select * from Customers c,Accounts a where c.CustomerID=a.CustomerID AND a.Email=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                Date CreateDate = rs.getDate("CreateDate");
                
                cus = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address,CreateDate);
            }
        } catch (Exception e) {
        }
        return cus;
    }
    
    public Customer getCustomerByID(String ID){
        Customer cus = null;
        try {
            String sql = "select * from Customers c where c.CustomerID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                Date CreateDate = rs.getDate("CreateDate");
                cus = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address,CreateDate);
            }
        } catch (Exception e) {
        }
        return cus;
    }
    
    public ArrayList<Customer> getAllCustomers(){
        ArrayList<Customer> cusList = new ArrayList<>();
        try {
            String sql = "select * from Customers";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                Date CreateDate = rs.getDate("CreateDate");
                Customer cus = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address,CreateDate);
                cusList.add(cus);
            }
        } catch (Exception e) {
        }
        return cusList;
    }
    
    public int createCustomer(Customer cus){
        int result1=0;
            try {
                String sql="insert into Customers(CustomerID, CompanyName , ContactName,ContactTitle,Address,CreateDate) values(?,?,?,?,?,GETDATE())";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1,cus.getCustomerID() );
                ps.setString(2, cus.getCompanyName());
                ps.setString(3,cus.getContactName() );
                ps.setString(4, cus.getContactTitle());
                ps.setString(5,cus.getAddress());
                
                result1 = ps.executeUpdate();
            } catch (Exception e) {
            }
            
            if (result1>0) {
            return 1;
        } else {
            return 0; 
        }
        
    }
    public String randomString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }
    
    public ArrayList<Customer> getNewCustomer(int month){
        ArrayList<Customer> cusList = new ArrayList<>();
        try {
            String sql = "select * from Customers where  Year(CreateDate)=YEAR(GETDATE()) AND MONTH(CreateDate)=?";
            //b2 tao doi tuong nhe
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, month);
            //b3thuc thi truy van
            ResultSet rs = ps.executeQuery();
            //b4 xu ly kqua tra ve
            while(rs.next()){
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                Date CreateDate = rs.getDate("CreateDate");
                Customer cus = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address,CreateDate);
                cusList.add(cus);
            }
        } catch (Exception e) {
            
        }//finally{ connection.close();}
        return cusList;
    }
    
    public ArrayList<Customer> getTotalNumberOfGuest(){
        ArrayList<Customer> cusList = new ArrayList<>();
        try {
            String sql = "select * from Customers where CustomerID not in "
                    + "(select c.CustomerID from Accounts a , Customers c where a.CustomerID=c.CustomerID)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                Date CreateDate = rs.getDate("CreateDate");
                Customer cus = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address,CreateDate);
                cusList.add(cus);
            }
        } catch (Exception e) {
        }
        return cusList;
    }
    
    
    public static void main(String[] args) {
        Customer cus = new CustomerDAO().getCustomerByID("0oYbA");
        System.out.println(cus);
    }
}
