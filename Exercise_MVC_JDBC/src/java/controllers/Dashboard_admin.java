/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.Customer;
import DAL.Order;
import DAL.OrderDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import models.CustomerDAO;
import models.OrderDAO;

/**
 *
 * @author user
 */
@WebServlet(name = "Dashboard_admin", urlPatterns = {"/dashboard_admin"})
public class Dashboard_admin extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
       
        if(req.getSession().getAttribute("AccAdminSession")!=null){
            
            
            ArrayList<Customer> cusList = new CustomerDAO().getAllCustomers();
            ArrayList<Customer> newCusList = new CustomerDAO().getNewCustomer(month);
            
            ArrayList<Customer> totalOfGuest = new CustomerDAO().getTotalNumberOfGuest();
            
            

            ArrayList<OrderDetail> odDetail = new OrderDAO().getOrderDetailByMonth(month);
            double totalOrderAmount = 0;
            for (OrderDetail orderDetail : odDetail) {
                totalOrderAmount+=orderDetail.getQuantity() * orderDetail.getUnitPrice();
            }
             ArrayList<Double> statistic = new ArrayList<>();
             
            if(req.getParameter("year")!=null){
                int year = Integer.parseInt(req.getParameter("year"));
                for (int i = 1; i <= month; i++) {
                    double revenueInMonth = 0;
                    ArrayList<OrderDetail> odDetailList= new OrderDAO().getOrderDetailByMonth(i,year);
                    for (OrderDetail orderDetailEle : odDetailList) {
                        revenueInMonth+=orderDetailEle.getQuantity() * orderDetailEle.getUnitPrice();
                    }
                    statistic.add(revenueInMonth);
                }
            }else{
                 for (int i = 1; i <= month; i++) {
                    double revenueInMonth = 0;
                    ArrayList<OrderDetail> odDetailList= new OrderDAO().getOrderDetailByMonth(i);
                    for (OrderDetail orderDetailEle : odDetailList) {
                        revenueInMonth+=orderDetailEle.getQuantity() * orderDetailEle.getUnitPrice();
                    }
                    statistic.add(revenueInMonth);
                }
            }
            
            
            
            req.setAttribute("totalOrderAmount", totalOrderAmount);

            req.setAttribute("totalOfGuest", totalOfGuest.size());
            req.setAttribute("statistic", statistic);
            req.setAttribute("newCusList", newCusList.size());
            req.setAttribute("cusList", cusList.size());
            
            
            req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
        }else{
            resp.getWriter().print("Access Denied!");
            //return;
        }
    }
    
}
