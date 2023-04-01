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


public class EditProduct_admin extends HttpServlet {

    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = new ProductDAO().getProductInfor(id);
        if (product != null) {
            List<Category> categories = categoryDAO.getCategory();
//            Category category = null;
//            for (Category categoryEle : categories) {
//                if (categoryEle.getCategoryID() == product.getCategoryID()) {
//                    category = categoryEle;
//                }
//            }
            req.getSession().setAttribute("create1_edit2", 2);
            req.getSession().setAttribute("txtProductID", id);
            req.setAttribute("product", product);
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/create_product.jsp").forward(req, resp);
        }else{
            req.setAttribute("sessionMsg", "Product "+id+" is not existed");
            req.getRequestDispatcher("./productManage_admin").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String back = req.getParameter("backToProductlist");
//        if(back != null){           
//            resp.sendRedirect("./product-list?currentPage="+req.getSession().getAttribute("currentPage"));
//            return;
//        }

        int productID = (int) req.getSession().getAttribute("txtProductID");
        String productName = req.getParameter("txtProductName");
        String quantityPerUnit = req.getParameter("txtQuantityPerUnit");
        boolean discontinued = !(req.getParameter("chkDiscontinued") == null);
        int categoryID = Integer.parseInt(req.getParameter("ddlCategory"));

        Product product = new Product(productID, productName, categoryID, quantityPerUnit, discontinued);

        int check = 0;
        if (productName.isEmpty()) {
            req.setAttribute("productNameMsg", "productName not allow null");
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
            req.setAttribute("categorys", new CategoryDAO().getCategory());
            req.getRequestDispatcher("/Edit.jsp").forward(req, resp);
            return;
        }

        try {
            productDAO.update(product);
            req.setAttribute("sessionMsg", "Update product" + productID + " successfull!");
        } catch (SQLException ex) {
            Logger.getLogger(EditProduct_admin.class.getName()).log(Level.SEVERE, null, ex);
            req.setAttribute("sessionMsg", "Update product " + productID + " fail");
            req.getRequestDispatcher("./productManage_admin").forward(req, resp);
            return;
        }
        req.getSession().removeAttribute("create1_edit2");
        req.setAttribute("sessionMsg", "Update product " + productID + " successfully");
        req.getRequestDispatcher("./productManage_admin?currentPage="+(int)req.getSession().getAttribute("currentPage")).forward(req, resp);
    }

}
