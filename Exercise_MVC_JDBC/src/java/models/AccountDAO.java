
package models;

import DAL.Account;
import DAL.Customer;
import DAL.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO extends DBContext{
    public Account getAccountByEmail(String email){
        Account acc = null;
        try {
            String sql = "select * from Accounts where Email=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");
                acc = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
            }
        } catch (Exception e) {
        }
        return acc;
    }
    
    public Account getAccount(String email, String pass){
        Account acc = null;
        try {
            String sql = "select * from Accounts where Email=? and Password=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");
                acc = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
            }
        } catch (Exception e) {
        }
        return acc;
    }
    
    public Account checkAccountExist(String email){
        Account acc = null;
        try {
            String sql = "select * from Accounts where Email=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");
                acc = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
            }
        } catch (Exception e) {
        }
        return acc;
    }
    
    public int editProfile(Customer cus, Account acc){
        int result1=0, result2=0;
            try {
                String sql1="UPDATE Customers SET CompanyName = ?,ContactName = ?,ContactTitle=?, [Address]=? WHERE CustomerID=?";
                String sql2="UPDATE Accounts SET [Password] = ? WHERE Email=?";
                PreparedStatement ps1 = connection.prepareStatement(sql1);
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps1.setString(1, cus.getCompanyName());
                ps1.setString(2,cus.getContactName() );
                ps1.setString(3, cus.getContactTitle() ); 
                ps1.setString(4,cus.getAddress() );
                ps1.setString(5,cus.getCustomerID());
                
                if(acc!=null){
                    ps2.setString(1, acc.getPassword());
                    ps2.setString(2, acc.getEmail() );
                    result2 = ps2.executeUpdate();
                    
                }else{result2=1;}
                
                result1 = ps1.executeUpdate();
                
            } catch (Exception e) {
            }
            
            if (result1>0 && result2>0) {
                return 1;
            } else {
                return 0; 
            }
        
    }
  
    
    public int createAccount(Customer cus, Account acc){
        int result1=0, result2=0;
            try {
                String sql1="insert into Customers(CustomerID, CompanyName , ContactName,ContactTitle,Address,CreateDate) values(?,?,?,?,?,GETDATE())";
                String sql2="insert into Accounts(Email, Password,CustomerID,Role) values(?,?,?,?)";
                PreparedStatement ps1 = connection.prepareStatement(sql1);
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                String CustomerID=randomString(5);
                ps1.setString(1,CustomerID );
                ps1.setString(2, cus.getCompanyName());
                ps1.setString(3,cus.getContactName() );
                ps1.setString(4, cus.getContactTitle());
                ps1.setString(5,cus.getAddress());
                
                ps2.setString(1, acc.getEmail());
                ps2.setString(2, acc.getPassword());
                ps2.setString(3, CustomerID);
                ps2.setInt(4, 2);
                result1 = ps1.executeUpdate();
                result2 = ps2.executeUpdate();
            } catch (Exception e) {
            }
            
            if (result1>0 && result2>0) {
            return 1;
        } else {
            return 0; 
        }
        
    }
    
    
    private String randomString(int n) {
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
    
    public String randomId(int n) {
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
    
    public int changePassword(Account account) {
        int rs = 0;
        try {
            String sql = "update Accounts set Password =? where Email=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, account.getPassword());
            ps.setString(2, account.getEmail());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
        }
        if (rs > 0) {
            return 1;
        } else {
            return 0;
        }
    }
    
    
    public static void main(String[] args) {
        Account acc = new Account(0, "vuvu1234", "1234", "s2In7", 0, 0);
        Customer cus = new Customer("s2In7", "vuvu", "vuvu", "vuvu", "vuvu");
        AccountDAO accDa0 = new AccountDAO();
        //accDa0.editProfile(cus, acc);
        accDa0.getAccount("vuvu1234", "123");
        System.out.println(accDa0.getAccount("vuvu1234", "123"));
    }
}
