/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.Account;
import DAL.Order;
import DAL.OrderDetail;
import DAL.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.OrderDAO;
import models.ProductDAO;

/**
 *
 * @author user
 */
@WebServlet(name = "AccountProfile1", urlPatterns = {"/account/profile1"})
public class AccountProfile1 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("AccSession")==null){
            resp.getWriter().print("Access Denied!");
            return;
        }
        
        int accID = ((Account) req.getSession().getAttribute("AccSession")).getAccountID();
        ArrayList<Order> odList = new OrderDAO().getAllOrdersOfCus(accID);
        ArrayList<OrderDetail> odDetailList = new OrderDAO().getDetailOfOrderByAcc(accID);
        ArrayList<Product> proList = new ProductDAO().getProducts(true);

        if (odList.isEmpty()) {
            req.setAttribute("emptyListMsg", "There is nothing Order, Let's buy something!");
            req.getRequestDispatcher("/profile1.jsp").forward(req, resp);
        } else {
            if (req.getParameter("idCancel") != null) {
                int idCancel = Integer.parseInt(req.getParameter("idCancel"));
                try {
                    new OrderDAO().cancelOrder(idCancel);

                    for (int i = 0; i < odList.size(); i++) {
                        if (odList.get(i).getOrderID() == idCancel) {
                            odList.get(i).setRequiredDate(null);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EditProduct_admin.class.getName()).log(Level.SEVERE, null, ex);
                    req.setAttribute("CancelMsg", "Cancel order fail");
                    return;
                }
            }
            if(req.getParameter("idOdDetail")!=null){
                int odID=Integer.parseInt(req.getParameter("idOdDetail"));
                Order od = new OrderDAO().getOrderByID(odID);
                ArrayList<OrderDetail> odDetail = new OrderDAO().getDetailOfOrderByOdID(odID);
                req.setAttribute("order", od);
                req.setAttribute("orderDetailList", odDetail);
                req.setAttribute("productList", proList);
                req.getRequestDispatcher("../profile1_detail.jsp").forward(req, resp);
            }
//            if(req.getParameter("idProDetail")!=null){
//                int proID=Integer.parseInt(req.getParameter("idProDetail"));
//                Product productInfor = new ProductDAO().getProductInfor(proID);
//                req.setAttribute("productInfor", productInfor);
//                req.getRequestDispatcher("detail.jsp").forward(req, resp);
//            }
            req.setAttribute("orderList", odList);
            req.setAttribute("orderDetailList", odDetailList);
            req.setAttribute("productList", proList);
            req.getRequestDispatcher("/profile1.jsp").forward(req, resp);
        }
        
    }

}
