
package controllers;

import DAL.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.AccountDAO;
import models.CustomerDAO;

public class AccountSignIn extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession")!=null) {
            req.getSession().removeAttribute("AccSession");
            req.getSession().removeAttribute("CusSession");
            resp.sendRedirect("../account/signin");
        }else if(req.getSession().getAttribute("AccAdminSession")!=null){
            req.getSession().removeAttribute("AccAdminSession");
            resp.sendRedirect("../index");
        }else{
            req.getRequestDispatcher("../signin.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Nhan du lieu tu login.jsp
        String email = req.getParameter("txtEmail");
        String pass = req.getParameter("txtPass");
        String msgEmail = "", msgPass="";
        
        if (email.equals("")) {
            msgEmail = "Email is required";
            req.setAttribute("msgEmail", msgEmail);
        }
        if(email.matches("^[A-Za-z0-9+_.-]+@(.+)$")==false && (!email.equals(""))){
            msgEmail = "Format Email is wrong!";
            req.setAttribute("msgEmail", msgEmail);
        }
        if(pass.equals("")){
            msgPass = "Password is required";
            req.setAttribute("msgPass", msgPass);
        }
        
        if(!msgEmail.equals("") || !msgPass.equals("")){
            req.getRequestDispatcher("../signin.jsp").forward(req, resp);
        }else{
            Account acc = new AccountDAO().getAccount(email, pass);
            if (acc!=null) {
                if(acc.getRole()==1){
                    req.getSession().setAttribute("AccAdminSession", acc);
                    resp.sendRedirect(req.getContextPath()+"/dashboard_admin");
                }else if(acc.getRole()==2){
                    req.getSession().setAttribute("AccSession", acc);
                    req.getSession().setAttribute("CusSession", new CustomerDAO().getCustomerByID(acc.getCustomerID()));
                    resp.sendRedirect(req.getContextPath()+"/index");
                }
            }else{
                req.setAttribute("msg", "This account does not exist");
                req.getRequestDispatcher("../signin.jsp").forward(req, resp);
            }
        }
    }
    
}
