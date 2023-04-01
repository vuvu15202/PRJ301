/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.CategoryDAO;
import models.ProductDAO;

public class DeleteProduct_admin extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            int ID = Integer.parseInt(req.getParameter("id"));

            if (productDAO.getProductInfor(ID) == null) {
                req.setAttribute("sessionMsg", "Product ID" + req.getParameter("id") + " doesn't exist!");
                req.getRequestDispatcher("./productManage_admin?currentPage=" + (int) req.getSession().getAttribute("currentPage")).forward(req, resp);  
            }

            Product pro = new ProductDAO().checkProExistInOrder(ID);
            if (pro == null) {
                try {
                    if (productDAO.Delete(ID) > 0) {
                        req.setAttribute("sessionMsg", "Delete product " + ID + " successfull!");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(EditProduct_admin.class.getName()).log(Level.SEVERE, null, ex);
                    req.setAttribute("sessionMsg", "Delete product " + ID + " fail");

                }
            } else {
                req.setAttribute("sessionMsg", "Product cannot delete, it's exist in some order! ");
            }

        } else {
            req.setAttribute("sessionMsg", "productID is undefined");
        }
        req.getRequestDispatcher("./productManage_admin?currentPage=" + (int) req.getSession().getAttribute("currentPage")).forward(req, resp);

    }

}
