package br.ufac.sgcm.controller;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import br.ufac.sgcm.dao.EspecialidadeDao;
import br.ufac.sgcm.model.Especialidade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EspecialidadeController implements IController<Especialidade> {

    EspecialidadeDao eDao;

    public EspecialidadeController(){
        eDao = new EspecialidadeDao();
    }

    @Override
    public int delete(Especialidade objeto) {
        int registrosAfetados = 0;
        registrosAfetados = eDao.delete(objeto);
        return registrosAfetados;
    }

    @Override
    public List<Especialidade> get() {
        List<Especialidade> registros = new ArrayList<>();
        registros =  eDao.get();
        return registros;
    }

    @Override
    public Especialidade get(Long id) {

        Especialidade esp = new Especialidade();
        esp = eDao.get(id);
        return esp;
    }

    @Override
    public List<Especialidade> get(String termoDeBusca) {
        List<Especialidade> registros = new ArrayList<>();
        registros = eDao.get(termoDeBusca);
        return registros;
    }

    @Override
    public int save(Especialidade objeto) {
        int registrosAfetados = 0;
        if (objeto.getId() == null){//Se o objeto não tem id, quer dizer que ele não está no banco, então inserimos
            registrosAfetados = eDao.insert(objeto);
        }
        else{
            registrosAfetados = eDao.update(objeto);
        }
        return registrosAfetados;
    }

    //Todos os metódos da camada Dao devem estar disponíveis aqui
    //Metódos das regras de negócio da aplicação

    //Metódos do Servlet

    public Especialidade processFormRequest(HttpServletRequest req, HttpServletResponse res){
        String paramSubmit = req.getParameter("submit");
        String paramNome = req.getParameter("nome");
        String paramId = req.getParameter("id");
        Especialidade registro = new Especialidade();
        //o Usuário está editando?
        if (paramId != null && !paramId.isEmpty()){
            Long id = Long.parseLong(paramId);
            registro = this.get(id);
        }
        if (paramSubmit != null){ //Se o usuário está inserindo
            registro.setNome(paramNome);
            this.save(registro); // Chama o save da classe Especialidade
        }
        try {
        res.sendRedirect("especialidade.jsp");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return registro;
    }

    public List<Especialidade> processListRequest(HttpServletRequest req, HttpServletResponse res){
        List<Especialidade> registros = new ArrayList<>();
        String paramExcluir = req.getParameter("excluir");
        if (paramExcluir != null && !paramExcluir.isEmpty()){
            Long id = Long.parseLong(paramExcluir);
            Especialidade registro =  this.get(id);
            this.delete(registro);
        }
        registros = this.get();
        return registros;
    }
    
}
