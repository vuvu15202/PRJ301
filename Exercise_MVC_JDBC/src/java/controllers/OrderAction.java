/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.Account;
import DAL.Cart;
import DAL.Customer;
import DAL.Item;
import DAL.Order;
import DAL.OrderDetail;
import DAL.SendMail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import models.CustomerDAO;
import models.OrderDAO;

/**
 *
 * @author user
 */
@WebServlet(name = "OrderAction", urlPatterns = {"/orderAction"})
public class OrderAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie arr[] = req.getCookies();
        int numberOfItemInCart = 0;
        if (arr != null) {
            for (Cookie arrCookies : arr) {
                if (arrCookies.getName().contains("item")) {
                    numberOfItemInCart++;
                }
            }
        }
        if (numberOfItemInCart == 0) {
            req.setAttribute("cartIsEmptyMsg", "Cart is empty, Let's add some thing into cart!");
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
        }

        if (req.getSession().getAttribute("AccSession") != null) {
            int accID = ((Account) req.getSession().getAttribute("AccSession")).getAccountID();
            ArrayList<Order> odList = new OrderDAO().getAllOrdersOfCus(accID);
            if (!odList.isEmpty()) {
                Order odTemplate = odList.get(0);
                req.setAttribute("odTemplate", odTemplate);
            }
            req.getRequestDispatcher("orderForm.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("GuestOrder", 1);
            req.getRequestDispatcher("signin.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("CusSession") != null) {
            String shipName = req.getParameter("txtShipName");
            String shipAddress = req.getParameter("txtShipAddress");
            String shipCity = req.getParameter("txtShipCity");
            String shipRegion = req.getParameter("txtShipRegion");
            String shipPostalCode = req.getParameter("txtShipPostalCode");
            String shipCountry = req.getParameter("txtShipCountry");

            Cookie arr[] = req.getCookies();
            ArrayList<String> cookiesText = new ArrayList<>();
            if (arr != null) {
                for (Cookie arrCookies : arr) {
                    if (arrCookies.getName().contains("item")) {
                        cookiesText.add(arrCookies.getValue());
                        arrCookies.setMaxAge(0);
                        resp.addCookie(arrCookies);
                    }
                }
            }
            Cart cart = new Cart();
            ArrayList<Item> itemmm = cart.decryptionCookiesText(cookiesText);

            Customer cus = (Customer)req.getSession().getAttribute("CusSession");
            OrderDAO odDAO = new OrderDAO();

            try {
                Order od = new Order(1, cus.getCustomerID(), shipName, shipAddress, shipCity, shipRegion, shipPostalCode, shipCountry, 152);
                odDAO.createOrder(od);
                for (Item item : itemmm) {
                    OrderDetail odDetail = new OrderDetail(new OrderDAO().getNewOrderID(), item.getProduct().getProductID(), item.getQuantity(), item.getProduct().getUnitPrice() - item.getProduct().getUnitPrice() * 0, 0);
                    odDAO.createOrderDetail(odDetail);
                }
                
                SendMail sendMail = new SendMail();
                String subjectContent = "Your order " + new OrderDAO().getNewOrderID() + " has been confirmed!";
                String emailContent = "Shopee is preparing your order!\nOrder detail: .......";
                if(req.getSession().getAttribute("AccSession")!=null){
                    sendMail.sendAnnounce(((Account) req.getSession().getAttribute("AccSession")).getEmail(), subjectContent, emailContent);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(OrderAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            req.getSession().setAttribute("numberOfItemInCart", 0);
            req.setAttribute("successMsg", "Order successfuly!");
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
        } else {
            String companyName = req.getParameter("txtCompanyName");
            String contactName = req.getParameter("txtContactName");
            String contactTitle = req.getParameter("txtContactTitle");
            String address = req.getParameter("txtAddress");
            CustomerDAO cusDAO = new CustomerDAO();
            Customer guest = new Customer(cusDAO.randomString(5), companyName, contactName, contactTitle, address);
            cusDAO.createCustomer(guest);
            req.getSession().setAttribute("CusSession", guest);
            req.getRequestDispatcher("orderForm.jsp").forward(req, resp);
        }

    }

}
