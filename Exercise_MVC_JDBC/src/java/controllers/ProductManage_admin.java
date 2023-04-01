/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.Category;
import DAL.PaginationObject;
import DAL.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import models.CategoryDAO;
import models.ProductDAO;

/**
 *
 * @author user
 */
public class ProductManage_admin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int categoryID= Integer.parseInt(req.getParameter("ddlCategory"));
//        req.setAttribute("ddlCategory", categoryID);
        //req.getRequestDispatcher("productManage_admin").forward(req, resp);
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccAdminSession") == null) {
            resp.getWriter().print("Access Denied!");
            return;
        }
        PaginationObject paging = new PaginationObject();

        int currentPage = 1;
        if ( req.getParameter("currentPage")!=null) {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }

        ArrayList<Product> proList = null;
        List<Product> listInCurrentPage = null;
        
        
        Enumeration<String> enumeration = req.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String parameterName = (String) enumeration.nextElement();
            if (parameterName.equals("txtSearch2")) {
                req.getSession().setAttribute("mode", 1);
            }
            if (parameterName.equals("dddlCategory")) {
                req.getSession().setAttribute("mode", 2);
            }
        }
        String sample = req.getParameter("txtSearch2");
        if (req.getSession().getAttribute("mode") != null) {
            if ((int) req.getSession().getAttribute("mode") == 1) {
                if (!sample.isEmpty()) {
                    proList = new ProductDAO().getProductbySearch(sample);
                    req.getSession().setAttribute("searchSession", proList);
                    req.getSession().setAttribute("sampleSession", sample);
                } else{
                    proList = new ProductDAO().getProducts(true);
                    req.getSession().removeAttribute("searchSession");
                    req.getSession().removeAttribute("categorySession");
                    req.getSession().removeAttribute("sampleSession");
                    req.getSession().removeAttribute("categoryIDSession");
                    req.getSession().removeAttribute("ProductsByCatNSearch");
                }
            }

            if ((int) req.getSession().getAttribute("mode") == 2) {
                if (req.getParameter("dddlCategory") != null ) {
                    int catID=Integer.parseInt(req.getParameter("dddlCategory"));
                    String sampleSession = (String)req.getSession().getAttribute("sampleSession");
                    if(catID==0 && req.getSession().getAttribute("searchSession")!=null){
                        proList = new ProductDAO().getProductbySearch(sampleSession);
                        req.getSession().removeAttribute("categorySession");
                        req.getSession().removeAttribute("categoryIDSession");
                    }else if(catID==0 && req.getSession().getAttribute("searchSession")==null){
                        proList = new ProductDAO().getProducts(true);
                        req.getSession().removeAttribute("categorySession");
                        req.getSession().removeAttribute("categoryIDSession");
                    }else{
                        proList = new ProductDAO().getProductByCategoryID(catID);
                        req.getSession().setAttribute("categoryIDSession", catID);
                        req.getSession().setAttribute("categorySession", proList);
                    }
                    

                }
            }
            if(req.getSession().getAttribute("searchSession")!=null && req.getSession().getAttribute("categorySession")!=null){
                String sampleSession = (String)req.getSession().getAttribute("sampleSession");
                int categoryIDSession = (int) req.getSession().getAttribute("categoryIDSession");
                proList= new ProductDAO().getProductsByCatNSearch(sampleSession,categoryIDSession , true);
                req.getSession().setAttribute("ProductsByCatNSearch", proList);
            }
        } else {
            if (req.getSession().getAttribute("searchSession") != null && req.getSession().getAttribute("categorySession") == null) {
                proList = (ArrayList<Product>) req.getSession().getAttribute("searchSession");
            } else if (req.getSession().getAttribute("categorySession") != null && req.getSession().getAttribute("searchSession") == null) {
                proList = (ArrayList<Product>) req.getSession().getAttribute("categorySession");
            }else if(req.getSession().getAttribute("modeProductsByCatNSearch")!=null){
                proList = (ArrayList<Product>) req.getSession().getAttribute("ProductsByCatNSearch");
            }else {
                proList = new ProductDAO().getProducts(true);
            }

        }
        if(proList.isEmpty()){
            req.setAttribute("emptyListMsg", "There is nothing in Product List!");
        }

        req.getSession().removeAttribute("mode");
        req.setAttribute("categoryList", new CategoryDAO().getCategory());
        listInCurrentPage = paging.getListInCurrentPage(proList, currentPage);
        req.setAttribute("numberOfPage", paging.getNumberOfPage(proList));
        req.getSession().setAttribute("currentPage", currentPage);
        req.setAttribute("listInCurrentPage", listInCurrentPage);
        req.getRequestDispatcher("/product.jsp").forward(req, resp);
    }

}
