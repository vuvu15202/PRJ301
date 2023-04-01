package controllers;

import DAL.Cart;
import DAL.Item;
import DAL.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import models.ProductDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author user
 */
public class CartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie arr[] = req.getCookies();
        ArrayList<String> cookiesText = new ArrayList<>();
        if (arr != null) {
            for (Cookie arrCookies : arr) {
                if (arrCookies.getName().contains("item")) {
                    cookiesText.add(arrCookies.getValue());
                }
            }
        }

        Cart cart = new Cart();
        ArrayList<Item> itemmm = cart.decryptionCookiesText(cookiesText);

        if (req.getParameter("iddDelete") != null) {
            int index = -1;
            if (arr != null) {
                for (Cookie arrCookie : arr) {
                    if (arrCookie.getName().equals("item" + req.getParameter("iddDelete"))) {
                        index++;
                        arrCookie.setMaxAge(0);
                        resp.addCookie(arrCookie);
                        itemmm.remove(index);
                    }
                }
            }
        }

        if (req.getParameter("id") != null) { //co ID
            int id = Integer.parseInt(req.getParameter("id"));
            int quantity = cart.getQuantityByID(Integer.parseInt(req.getParameter("id")));
            int index = -1;
            if (cart.isProductInCart(id)) { //sp da co trong cookie
                if (req.getParameter("plus1_minus0") == null) {
                    quantity++;
                } else {
                    if (Integer.parseInt(req.getParameter("plus1_minus0")) == 1) {
                        quantity++;
                    } else if (Integer.parseInt(req.getParameter("plus1_minus0")) == 0) {
                        quantity--;
                    }
                }
                if (arr != null) {
                    for (Cookie arrCookie : arr) {
                        if (arrCookie.getName().equals("item" + id)) {
                            index++;
                            if (quantity > 0) {
                                arrCookie.setValue(id + "-" + quantity);
                                resp.addCookie(arrCookie);
                                for (int i = 0; i < itemmm.size(); i++) {
                                    if (itemmm.get(i).getProduct().getProductID() == id) {
                                        itemmm.remove(itemmm.get(i));
                                        itemmm.add(i, new Item(new ProductDAO().getProductInfor(id), quantity));
                                    }
                                }
                            } else {
                                arrCookie.setMaxAge(0);
                                resp.addCookie(arrCookie);
                                for (int i = 0; i < itemmm.size(); i++) {
                                    if (itemmm.get(i).getProduct().getProductID() == id) {
                                        itemmm.remove(itemmm.get(i));
                                    }
                                }
                            }

                        }
                    }
                }

            } else { //sp chua co trong cookie
                Cookie c = new Cookie("item" + id, id + "-1");
                c.setMaxAge(60 * 60 * 24 * 30);
                resp.addCookie(c);
                itemmm.add(new Item(new ProductDAO().getProductInfor(id), 1));
            }

        } else {//Ko ID
            //req.setAttribute("cart", itemmm);
        }

        if (itemmm.isEmpty()) {
            req.setAttribute("cartIsEmptyMsg", "Cart is empty, Let's add some thing into cart!");
        }
        
        double totalAmount = 0;
        for (Item item : itemmm) {
            totalAmount+=item.getQuantity() * item.getProduct().getUnitPrice();
        }
        
        
        req.setAttribute("itemList", itemmm);
        req.getSession().setAttribute("numberOfItemInCart", itemmm.size());
        req.setAttribute("totalAmount", totalAmount);
        if(req.getParameter("justAdd")!=null){
            Product productInfor = new ProductDAO().getProductInfor(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("productInfor", productInfor);
            req.getRequestDispatcher("detail.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

}
