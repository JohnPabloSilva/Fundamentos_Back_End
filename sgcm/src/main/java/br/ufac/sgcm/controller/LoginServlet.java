package br.ufac.sgcm.controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{

    public void service(HttpServletRequest req, HttpServletResponse res){
        String tentou_logar = req.getParameter("submit");
        if (tentou_logar != null){
            try 
            {
                res.sendRedirect("index.jsp");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
}
