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
@WebServlet(name = "AccountProfile2", urlPatterns = {"/account/profile2"})
public class AccountProfile2 extends HttpServlet {

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
        ArrayList<Order> odList = new OrderDAO().getAllCanceledOrdersOfCus(accID);
        ArrayList<OrderDetail> odDetailList = new OrderDAO().getDetailOfOrderByAcc(accID);
        ArrayList<Product> proList = new ProductDAO().getProducts(true);

        if (odList.isEmpty()) {
            req.setAttribute("emptyListMsg", "There is nothing Canceled Order, Let's buy something!");
            req.getRequestDispatcher("../profile2.jsp").forward(req, resp);
        } else {
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
            req.getRequestDispatcher("../profile2.jsp").forward(req, resp);
        }
        
    }

}
