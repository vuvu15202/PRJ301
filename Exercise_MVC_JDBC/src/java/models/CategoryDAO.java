
package models;

import DAL.Category;
import DAL.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PQT2212
 */
public class CategoryDAO extends DBContext{
    //private Connection connection;
    //private PreparedStatement ps;
    //private ResultSet rs;
    
    public ArrayList<Category> getCategory() {     
        ArrayList<Category> categories = new ArrayList<>();
        try {
            //connection = DBContext.getInstance().getConnection();
            
            String sql = "SELECT * FROM Categories";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");        
                
                categories.add(new Category(categoryID, categoryName));
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.WARNING,e.getMessage(),e);
        } finally {
            //DBContext.releaseJBDCObject(rs, ps, connection);
        }
        return categories;
    }
}
