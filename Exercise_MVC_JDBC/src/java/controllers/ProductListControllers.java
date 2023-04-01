/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.Category;
import DAL.PaginationObject;
import DAL.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
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
 * @author ADMIN
 */
public class ProductListControllers extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PaginationObject paging = new PaginationObject(); paging.setNumberOfRowEachPage(16);
        
        int currentPage = 1; 
        if ( req.getParameter("currentPage")!=null) {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }
        
        List<Product> listInCurrentPage = null;
        ArrayList<Product> proList= new ArrayList<>();
        
        Enumeration<String> enumeration = req.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String parameterName = (String)enumeration.nextElement();
            if(parameterName.equals("txtSearch1")){
                req.getSession().setAttribute("mode", 1);
            }
            if(parameterName.equals("categoryID")){
                req.getSession().setAttribute("mode", 2);
            }
            if(parameterName.equals("idProDetail")){
                req.getSession().setAttribute("mode", 3);
            }
            if(parameterName.equals("isDefault")){
                req.getSession().setAttribute("mode", 4);
            }
        }
        
        if(req.getSession().getAttribute("mode")!=null){
            switch ((int)req.getSession().getAttribute("mode")) {
                case 1:
                    String sample = req.getParameter("txtSearch1");
                    if(!req.getParameter("txtSearch1").isEmpty()){
                        proList= new ProductDAO().getProductbySearch(sample);
                        req.getSession().setAttribute("searchSession1", proList);
                        req.getSession().setAttribute("sampleSession1", sample);
                    }else{
                        proList= new ProductDAO().getProducts(false);
                        req.getSession().setAttribute("searchSession1", proList);
                        req.getSession().removeAttribute("categorySession1");
                        req.getSession().removeAttribute("sampleSession1");
                        req.getSession().removeAttribute("categoryIDSession1");
                        req.getSession().removeAttribute("ProductsByCatNSearch1");
                    }   break;
                case 2:
                    int categoryID = Integer.parseInt(req.getParameter("categoryID"));
                    proList= new ProductDAO().getProductByCategoryID(categoryID);
                    req.getSession().setAttribute("categorySession1", proList);
                    req.getSession().setAttribute("categoryIDSession1", categoryID);
                    break;
                case 3:
                    int idDetail = Integer.parseInt(req.getParameter("idProDetail"));
                    Product productInfor = new ProductDAO().getProductInfor(idDetail);
                    req.setAttribute("productInfor", productInfor);
                    req.getRequestDispatcher("detail.jsp").forward(req, resp);
                    break;
                default:
                    proList= new ProductDAO().getHotProduct();
                    req.setAttribute("mode", "default");
                    req.getSession().removeAttribute("searchSession1");
                    req.getSession().removeAttribute("categorySession1");
                    req.getSession().removeAttribute("sampleSession1");
                    req.getSession().removeAttribute("categoryIDSession1");
                    req.getSession().removeAttribute("ProductsByCatNSearch1");
                    break;
            }
            if(req.getSession().getAttribute("searchSession1")!=null && req.getSession().getAttribute("categorySession1")!=null){
                String sampleSession = (String)req.getSession().getAttribute("sampleSession1");
                int categoryIDSession = (int) req.getSession().getAttribute("categoryIDSession1");
                proList= new ProductDAO().getProductsByCatNSearch(sampleSession,categoryIDSession , false);
                req.getSession().setAttribute("ProductsByCatNSearch1", proList);
            }
        }else{
            if (req.getSession().getAttribute("searchSession1") != null && req.getSession().getAttribute("categorySession1") == null) {
                proList = (ArrayList<Product>) req.getSession().getAttribute("searchSession1");
            } else if (req.getSession().getAttribute("categorySession1") != null && req.getSession().getAttribute("searchSession1") == null) {
                proList = (ArrayList<Product>) req.getSession().getAttribute("categorySession1");
            }else if(req.getSession().getAttribute("modeProductsByCatNSearch1")!=null){
                proList = (ArrayList<Product>) req.getSession().getAttribute("ProductsByCatNSearch1");
            }else {
                proList= new ProductDAO().getHotProduct();
                req.setAttribute("mode", "default");
            }
        }
        
        Cookie arr[] = req.getCookies(); 
        int numberOfItemInCart=0;
        if(arr!=null){
            for (Cookie arrCookies : arr) {
                if (arrCookies.getName().contains("item")) {
                    numberOfItemInCart++;
                }
            }
        }
        req.getSession().setAttribute("numberOfItemInCart", numberOfItemInCart);
        req.getSession().removeAttribute("mode");
        req.setAttribute("categoryList", new CategoryDAO().getCategory());
        
        if(req.getSession().getAttribute("searchSession1") != null || req.getSession().getAttribute("categorySession1") != null){
            listInCurrentPage = paging.getListInCurrentPage(proList, currentPage);
            req.setAttribute("proList", listInCurrentPage);
            req.setAttribute("numberOfPage", paging.getNumberOfPage(proList));
            req.getSession().setAttribute("currentPage", currentPage);
            
        }else{
            req.setAttribute("proList", proList);
        }
        
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    
    
    
}
