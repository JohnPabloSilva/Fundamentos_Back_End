package br.ufac.sgcm;

import java.io.PrintWriter;
import java.io.IOException;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;

public class PrimeiroServlet extends HttpServlet {

    public void service(ServletRequest req, ServletResponse res)throws IOException{
        PrintWriter saida = res.getWriter();

        saida.println("<html>");
        saida.println("<head>");
        saida.println("<title>Primeira Página com Servlet </title>");
        saida.println("</head>");
        saida.println("<body>");
        saida.println("<h1>Título muito top</h1>");
        saida.println("</body>");
        saida.println("</html>");
    }
    
}
