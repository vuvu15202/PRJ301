/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.Category;
import DAL.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.CategoryDAO;
import models.ProductDAO;


public class CreateProduct_admin extends HttpServlet{
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final ProductDAO productDAO = new ProductDAO();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryDAO.getCategory();
        req.getSession().setAttribute("create1_edit2", 1);
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/create_product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String back = req.getParameter("backToProductlist");
//        if(back != null){
//            resp.sendRedirect("./product-list?currentPage="+req.getSession().getAttribute("currentPage"));
//            return;
//        }
        
        String productName = req.getParameter("txtProductName");
        String quantityPerUnit = req.getParameter("txtQuantityPerUnit");
        boolean discontinued = !(req.getParameter("chkDiscontinued")==null);
        int categoryID = Integer.parseInt(req.getParameter("ddlCategory"));

        Product product = new Product(productName, categoryID, quantityPerUnit, discontinued);
               
        int check = 0;
        if (productName.isEmpty()) {
            req.setAttribute("productNameMsg", "productName not allow null");
            check++;
        }
        
        if (req.getParameter("ddlCategory").isEmpty()) {
            req.setAttribute("ddlCategoryMsg", "Category not allow null");
            check++;
        }
        
        try {            
            int unitsInStock = Integer.parseInt(req.getParameter("txtUnitsInStock"));
            if (unitsInStock < 0) {
                req.setAttribute("unitsInStockMsg", "unitsInStock must greater or equal zero");
                check++;
            }
            product.setUnitsInStock(unitsInStock);
        } catch (NumberFormatException e) {
            req.setAttribute("unitsInStockMsg", "unitsInStock must be an integer number");
            check++;
        }
        
        try {            
            int unitsOnOrder = Integer.parseInt(req.getParameter("txtUnitsOnOrder"));
            if (unitsOnOrder < 0) {
                req.setAttribute("unitsOnOrderMsg", "unitsOnOrder must greater or equal zero");
                check++;
            }
            product.setUnitsOnOrder(unitsOnOrder);
        } catch (NumberFormatException e) {
            req.setAttribute("unitsOnOrderMsg", "unitsOnOrder must be an integer number");
            check++;
        }
        
        try {            
            int reorderLevel = Integer.parseInt(req.getParameter("txtReorderLevel"));
            if (reorderLevel < 0) {
                req.setAttribute("reorderLevelMsg", "reorderLevel must greater or equal zero");
                check++;
            }
            product.setReorderLevel(reorderLevel);
        } catch (NumberFormatException e) {
            req.setAttribute("reorderLevelMsg", "reorderLevel must be an integer number");
            check++;
        }
        
        try {            
            double unitPrice = Double.parseDouble(req.getParameter("txtUnitPrice"));
            if (unitPrice < 0) {
                req.setAttribute("unitPriceMsg", "unitPrice must greater or equal zero");
                check++;
            }
            product.setUnitPrice(unitPrice);
        } catch (NumberFormatException e) {
            req.setAttribute("unitPriceMsg", "unitPrice must be an double number");
            check++;
        }
        
        if (check != 0) {
            req.setAttribute("product", product);
            req.setAttribute("categories", new CategoryDAO().getCategory());
            req.getRequestDispatcher("/create_product.jsp").forward(req, resp);
            return;
        }

        try {
            productDAO.CreateProduct(product);
        } catch (SQLException ex) {
            Logger.getLogger(EditProduct_admin.class.getName()).log(Level.SEVERE, null, ex);
            req.getSession().setAttribute("sessionMsg", "Create product fail");
            resp.sendRedirect("./product-list");
            return;
        }
        req.getSession().removeAttribute("create1_edit2");
        req.setAttribute("sessionMsg", "Create product successfull!");
        resp.sendRedirect("./productManage_admin"); //?currentPage="+req.getSession().getAttribute("currentPage")
    }
    
}
