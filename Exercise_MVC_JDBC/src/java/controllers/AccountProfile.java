/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.Account;
import DAL.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.AccountDAO;
import models.CustomerDAO;

/**
 *
 * @author user
 */
public class AccountProfile extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("txtEmail");
        String oldPass = req.getParameter("txtOldPass");
        String pass = req.getParameter("txtPass");
        String rePass = req.getParameter("txtRePass");
        String companyName = req.getParameter("txtCompanyName");
        String ContactName = req.getParameter("txtContactName");
        String ContactTitle = req.getParameter("txtContactTitle");
        String Address = req.getParameter("txtAddress");
        String msgEmail="", msgPass="",msgRePass="", msgCompanyName="", msgContactName="", msgContactTitle="", msgAdress="",msgOldPass="";
        int countError=0;
        if (email.equals("")) { countError++;
            msgEmail = "Email is required";
            req.setAttribute("msgEmail", msgEmail);
        }
        if(oldPass.equals("")){ countError++;
            msgOldPass = "Password is required";
            req.setAttribute("msgOldPass", msgOldPass);
        }
        
        if(companyName.equals("")){ countError++;
            msgCompanyName = "Company name is required";
            req.setAttribute("msgCompanyName", msgCompanyName);
        }
        if(ContactName.equals("")){ countError++;
            msgContactName = "Contact name is required";
            req.setAttribute("msgContactName", msgContactName);
        }
        if(ContactTitle.equals("")){ countError++;
            msgContactTitle = "Contact Title is required";
            req.setAttribute("msgContactTitle", msgContactTitle);
        }
        if(Address.equals("")){ countError++;
            msgAdress = "Address is required";
            req.setAttribute("msgAdress", msgAdress);
        }
        
        if( ((!pass.equals("")) && rePass.equals("")) || (pass.equals("") && (!rePass.equals(""))) || (!pass.equals(rePass))    ){ 
            countError++;
            msgPass = "Password and RePassword are not match";
            msgRePass = "Password and RePassword are not match";
            req.setAttribute("msgPass", msgPass);
            req.setAttribute("msgRePass", msgRePass);
        }
        
        if(countError>0){
            req.getRequestDispatcher("../profileEdit.jsp").forward(req, resp);
        }
        
        AccountDAO accdao = new AccountDAO();
        Account checkOldPass = accdao.getAccount(email,oldPass);
        if(checkOldPass==null){
            req.setAttribute("msgOldPass", "Old password or email is wrong!");
            req.getRequestDispatcher("../profileEdit.jsp").forward(req, resp);
        }else{
            Account checkAcc = accdao.checkAccountExist(email);
            if(checkAcc!=null){
                Customer cust = new Customer(((Customer)req.getSession().getAttribute("cusSession")).getCustomerID(), companyName, ContactName, ContactTitle, Address);
                Account acc = null;
                if((!pass.isEmpty()) && (!rePass.isEmpty())){
                    acc=new Account(0, email, pass, "", 0, 0);
                }
                if(accdao.editProfile(cust,acc)>0){
                    req.getSession().setAttribute("msg", "Edit successful!");
                    doGet(req, resp);
                }else{
                    req.setAttribute("msg", "Edit fail!");
                    req.getRequestDispatcher("../profileEdit.jsp").forward(req, resp);
                }
            }else{
                req.setAttribute("msgExisted", "This account is not existed!");
                req.getRequestDispatcher("../profileEdit.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("AccSession")==null){
            resp.getWriter().print("Access Denied!");
            return;
        }
        if(req.getParameter("editProfile")!=null){
            Account acc = (Account)req.getSession().getAttribute("AccSession");
            Customer cus = new CustomerDAO().getCustomerByEmail(acc.getEmail());
            req.getSession().setAttribute("cusSession", cus);
            //req.setAttribute("cus", cus);
            req.getRequestDispatcher("../profileEdit.jsp").forward(req, resp);
        }else if(req.getSession().getAttribute("AccSession")!=null){
            req.getSession().removeAttribute("cusSession");
            String accEmail = ((Account)req.getSession().getAttribute("AccSession")).getEmail();
            Customer cus = new CustomerDAO().getCustomerByEmail(accEmail);
            req.getSession().setAttribute("cusSession", cus);
            if(req.getSession().getAttribute("msg")!=null){
                req.getSession().removeAttribute("msg");
                req.setAttribute("msg","Edit successful!");
            }
            req.getRequestDispatcher("../profile.jsp").forward(req, resp);
        }
        
    }

}
