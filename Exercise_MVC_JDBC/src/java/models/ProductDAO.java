 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import DAL.DBContext;
import DAL.Product;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class ProductDAO extends DBContext{
    public ArrayList<Product> getProducts(boolean isAdmin) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "select * from Products";
            String sql2 = "select * from Products where Discontinued=?";
            //b2 tao doi tuong nhe
            PreparedStatement ps;
            if(isAdmin==true){ 
                ps= connection.prepareStatement(sql);
            }else{
                 ps= connection.prepareStatement(sql2);
                 ps.setInt(1, 0);
            }
            //b3thuc thi truy van
            ResultSet rs = ps.executeQuery();
            //b4 xu ly kqua tra ve
            while (rs.next()) {
                //doc du lieu tu 'rs' gan cho cac bien cuc bo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                products.add(p);
            }
            
        } catch (Exception e) {
            
        }
//        finally{
//            connection.close();
//        }
        
        return products;
    }
    
    public ArrayList<Product> getProductsByCatNSearch(String sample,int CatID,boolean isAdmin) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "select * from Products where CategoryID=? AND ProductName like ?";
            String sql2 = "select * from Products where CategoryID=? AND ProductName like ? AND Discontinued=?";
            //b2 tao doi tuong nhe
            PreparedStatement ps;
            if(isAdmin==true){ 
                ps= connection.prepareStatement(sql);
                ps.setInt(1, CatID);
                ps.setString(2, "%"+sample+"%");
            }else{
                ps= connection.prepareStatement(sql2);
                ps.setInt(1, CatID);
                ps.setString(2, "%"+sample+"%");
                ps.setInt(3, 0);
            }
            //b3thuc thi truy van
            ResultSet rs = ps.executeQuery();
            //b4 xu ly kqua tra ve
            while (rs.next()) {
                //doc du lieu tu 'rs' gan cho cac bien cuc bo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                products.add(p);
            }
            
        } catch (Exception e) {
            
        }
//        finally{
//            connection.close();
//        }
        
        return products;
    }
    
    public ArrayList<Product> getHotProduct() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "select top 4 * from (select COUNT(OrderID) as numberOrder, ProductID from [Order Details] \n" +
"group by ProductID) as A inner join Products on A.ProductID=Products.ProductID ORDER BY numberOrder DESC";
            //b2 tao doi tuong nhe
            PreparedStatement ps = connection.prepareStatement(sql);
            //b3thuc thi truy van
            ResultSet rs = ps.executeQuery();
            //b4 xu ly kqua tra ve
            while (rs.next()) {
                //doc du lieu tu 'rs' gan cho cac bien cuc bo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                products.add(p);
            }
            
        } catch (Exception e) {
            
        }
//        finally{
//            connection.close();
//        }
        
        return products;
    }
    
    public Product getProductInfor(int proID) {
        Product p = null; 
        try {
            String sql = "select * from Products,Categories where Products.CategoryID=Categories.CategoryID and ProductID = ? "; 
            //b2 tao doi tuong nhe
            PreparedStatement ps = connection.prepareStatement(sql); 
            ps.setInt(1, proID);  
            //b3thuc thi truy van
            ResultSet rs = ps.executeQuery();  
            //b4 xu ly kqua tra ve
            while (rs.next()) { 
                //doc du lieu tu 'rs' gan cho cac bien cuc bo
                int ProductID = rs.getInt("ProductID"); 
                String ProductName = rs.getString("ProductName"); 
                double UnitPrice = rs.getDouble("UnitPrice");
                String CategoryName = rs.getString("CategoryName"); 
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued"); 
                p= new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                
            }
        } catch (Exception e) {
        }

        return p;
    }
    
    public Product checkProExistInOrder(int proID) {
        Product p = null; 
        try {
            String sql = "select DISTINCT * from Products p, [Order Details] od where p.ProductID=od.ProductID AND p.ProductID=?"; 
           
            PreparedStatement ps = connection.prepareStatement(sql); 
            ps.setInt(1, proID);  
            ResultSet rs = ps.executeQuery();  
            while (rs.next()) { 
                int ProductID = rs.getInt("ProductID"); 
                String ProductName = rs.getString("ProductName"); 
                double UnitPrice = rs.getDouble("UnitPrice");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued"); 
                p= new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                
            }
        } catch (Exception e) {
        }

        return p;
    }
    
    public ArrayList<Product> getProductbySearch(String sample) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "select * from Products where ProductName like ?";
            //b2 tao doi tuong nhe
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%"+sample+"%");
            //b3thuc thi truy van
            ResultSet rs = ps.executeQuery();
            //b4 xu ly kqua tra ve
            while (rs.next()) {
                //doc du lieu tu 'rs' gan cho cac bien cuc bo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                products.add(p);
            }
            
        } catch (Exception e) {
            
        }// finally{connection.close();}
        
        return products;
    }
    public ArrayList<Product> getProductByCategoryID(int catID) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "select * from Products where CategoryID=?";
            //b2 tao doi tuong nhe
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, catID);
            //b3thuc thi truy van
            ResultSet rs = ps.executeQuery();
            //b4 xu ly kqua tra ve
            while (rs.next()) {
                //doc du lieu tu 'rs' gan cho cac bien cuc bo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                products.add(p);
            }
            
        } catch (Exception e) {
            
        }// finally{connection.close();}
        
        return products;
    }
    
    public void update(Product p) throws SQLException {
        try {
            //connection = DBContext.getInstance().getConnection();
            
            String sql = "update Products SET ProductName = ?, CategoryID = ?, "
                    + "QuantityPerUnit = ?, UnitPrice = ?, UnitsInStock = ?, UnitsOnOrder = ?, "
                    + "ReorderLevel = ?, Discontinued = ? where ProductID = ?";

            PreparedStatement ps = connection.prepareStatement(sql); 
            ps.setString(1, p.getProductName());
            ps.setInt(2, p.getCategoryID());
            ps.setString(3, p.getQuantityPerUnit());
            ps.setDouble(4, p.getUnitPrice());
            ps.setInt(5, p.getUnitsInStock());
            ps.setInt(6, p.getUnitsOnOrder());
            ps.setInt(7, p.getReorderLevel());
            ps.setBoolean(8, p.isDiscontinued());
            ps.setInt(9, p.getProductID());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            //DBContext.releaseJBDCObject(rs, ps, connection);
        }
    }
    
    public int Delete(int ID) throws SQLException {
        int result=0;
        String sql1 = "DELETE FROM Products WHERE ProductID = ?";
        //String sql2 = "delete from [Order Details] where ProductID = ?";
        try {
            //connection = DBContext.getInstance().getConnection();
            PreparedStatement ps1 = connection.prepareStatement(sql1); 
            //PreparedStatement ps2 = connection.prepareStatement(sql2); 
            ps1.setInt(1, ID);
            //ps2.setInt(1, ID);
            result=ps1.executeUpdate();
            //ps2.executeUpdate();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            //DBContext.releaseJBDCObject(rs, ps, connection);
        }
        return result>0?1:0;
    }
    public void CreateProduct(Product p) throws SQLException {
        String sql = "insert into Products(ProductName,CategoryID,QuantityPerUnit,UnitPrice,"
                + "UnitsInStock,UnitsOnOrder,ReorderLevel,Discontinued)\n"
                + "values(?,?,?,?,?,?,?,?)";
        try {            
            //connection = DBContext.getInstance().getConnection();
            
            PreparedStatement ps = connection.prepareStatement(sql); 
            ps.setString(1, p.getProductName());
            ps.setInt(2, p.getCategoryID());
            ps.setString(3, p.getQuantityPerUnit());
            ps.setDouble(4, p.getUnitPrice());
            ps.setInt(5, p.getUnitsInStock());
            ps.setInt(6, p.getUnitsOnOrder());
            ps.setInt(7, p.getReorderLevel());
            ps.setBoolean(8, p.isDiscontinued());
            ps.executeUpdate();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            //DBContext.releaseJBDCObject(rs, ps, connection);
        }
    }
    
    public static void main(String[] args) {
        ArrayList<Product> list = new ProductDAO().getProductsByCatNSearch("",1,true);
        System.out.println(list.size());
        for (Product product : list) {
            System.out.println(product);
        }
    }
            
}
