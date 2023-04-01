/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.Customer;
import DAL.PaginationObject;
import DAL.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.CustomerDAO;

/**
 *
 * @author user
 */
@WebServlet(name = "CustomerManage_admin", urlPatterns = {"/customerManage_admin"})
public class CustomerManage_admin extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PaginationObject paging = new PaginationObject();

        int currentPage = 1;
        if ( req.getParameter("currentPage")!=null) {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }
        ArrayList<Customer> cusList = new CustomerDAO().getAllCustomers();
        List<Customer> listInCurrentPage = new ArrayList<>();
        listInCurrentPage = paging.getListInCurrentPage(cusList, currentPage);
        req.setAttribute("numberOfPage", paging.getNumberOfPage(cusList));
        req.getSession().setAttribute("currentPage", currentPage);
        req.setAttribute("listInCurrentPage", listInCurrentPage);
        
        req.getRequestDispatcher("customer.jsp").forward(req, resp);
    }
    
}
