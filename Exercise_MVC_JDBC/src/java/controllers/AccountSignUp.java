/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.Account;
import DAL.Customer;
import DAL.SendMail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import models.AccountDAO;

/**
 *
 * @author user
 */
public class AccountSignUp extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("txtEmail");
        String pass = req.getParameter("txtPass");
        String rePass = req.getParameter("txtRePass");
        String companyName = req.getParameter("txtCompanyName");
        String contactName = req.getParameter("txtContactName");
        String contactTitle = req.getParameter("txtContactTitle");
        String address = req.getParameter("txtAddress");
        String msgEmail="", msgPass="",msgRePass="", msgCompanyName="", msgContactName="", msgContactTitle="", msgAddress="";
        int countError=0;
        if(email.equals("")){ countError++;
            msgEmail = "Email is required";
            req.setAttribute("msgEmail", msgEmail);
        }
         if(email.matches("^[A-Za-z0-9+_.-]+@(.+)$")==false && (!email.equals(""))){countError++;
            msgEmail = "Format Email is wrong!";
            req.setAttribute("msgEmail", msgEmail);
        }
        if(pass.equals("")){ countError++;
            msgPass = "Password is required";
            req.setAttribute("msgPass", msgPass);
            
        }
        if(rePass.equals("")){ countError++;
            msgRePass = "Re-Password is required";
            req.setAttribute("msgRePass", msgRePass);
        }
        if(companyName.equals("")){ countError++;
            msgCompanyName = "Company name is required";
            req.setAttribute("msgCompanyName", msgCompanyName);
        }
        if(contactName.equals("")){ countError++;
            msgContactName = "Contact name is required";
            req.setAttribute("msgContactName", msgContactName);
        }
        if(contactTitle.equals("")){ countError++;
            msgContactTitle = "Contact Title is required";
            req.setAttribute("msgContactTitle", msgContactTitle);
        }
        if(address.equals("")){ countError++;
            msgAddress = "Address is required";
            req.setAttribute("msgAddress", msgAddress);
        }
        
        req.setAttribute("txtEmail", email);
        req.setAttribute("txtPass", pass);
        req.setAttribute("txtRePass", rePass);
        req.setAttribute("txtCompanyName", companyName);
        req.setAttribute("txtContactName", contactName);
        req.setAttribute("txtContactTitle", contactTitle);
        req.setAttribute("txtAddress", address);

        if(countError>0){
            req.getRequestDispatcher("../signup.jsp").forward(req, resp);
            return;
        }
            
        if(!pass.equals(rePass)){
            req.setAttribute("msgRePass", "Password and Re-Password are not match!");
            req.getRequestDispatcher("../signup.jsp").forward(req, resp);
        }else{
            AccountDAO accdao = new AccountDAO();
            Account checkAcc = accdao.checkAccountExist(email);
            if(checkAcc==null){
                Customer cust = new Customer("", companyName, contactName, contactTitle, address);
                Account acc = new Account(0, email, pass, "", 0, 0);
                if(new AccountDAO().createAccount(cust, acc)>0){
                    SendMail sendEmail = new SendMail(); 
                    try {
                        sendEmail.sendAnnounce(email, "Your account created successfuly!", "Dear "+contactName+"!\nYour Email has been sign up on Shoppee! Let's buy something.");
                    } catch (MessagingException ex) {
                        Logger.getLogger(ForgotPasswordController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    resp.sendRedirect(req.getContextPath()+"/account/signin");
                }else{
                    req.setAttribute("msg", "Signup fail!");
                    req.getRequestDispatcher("../signup.jsp").forward(req, resp);
                }
            }else{
                req.setAttribute("msgExisted", "This account is already existed!");
                req.getRequestDispatcher("../signup.jsp").forward(req, resp);
            }
        }
        
        
    }
 

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("../signup.jsp").forward(req, resp);
    }
    
}
