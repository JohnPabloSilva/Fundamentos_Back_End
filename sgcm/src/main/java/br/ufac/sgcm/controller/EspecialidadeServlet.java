package br.ufac.sgcm.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufac.sgcm.model.Especialidade;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;

public class EspecialidadeServlet extends HttpServlet{
    public void service(ServletRequest req, ServletResponse res){
        String termoDeBusca = req.getParameter("busca");
        List<Especialidade> registros = new ArrayList<>();
        EspecialidadeController controller = new EspecialidadeController();
        registros = controller.get(termoDeBusca);
        //Transformando registros em um arquivo JSON
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            String json = mapper.writeValueAsString(registros);
            PrintWriter saida = res.getWriter();
            saida.println(json);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
