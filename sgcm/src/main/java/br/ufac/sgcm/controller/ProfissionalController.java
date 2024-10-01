package br.ufac.sgcm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.dao.EspecialidadeDao;
import br.ufac.sgcm.dao.ProfissionalDao;
import br.ufac.sgcm.dao.UnidadeDao;
import br.ufac.sgcm.model.Profissional;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProfissionalController implements IController<Profissional> {

    ProfissionalDao pDao = new ProfissionalDao();
    UnidadeDao uDao = new UnidadeDao();
    EspecialidadeDao eDao = new EspecialidadeDao();

    public ProfissionalController(){}

    @Override
    public int delete(Profissional objeto) {
        int registro = this.pDao.delete(objeto);
        return registro;
    }

    @Override
    public List<Profissional> get() {
        List<Profissional> registros = pDao.get();
        return registros;
    }

    @Override
    public Profissional get(Long id) {
        Profissional registroProfissional = pDao.get(id);
        return registroProfissional;
    }

    @Override
    public List<Profissional> get(String termoDeBusca) {
        List<Profissional> registros = pDao.get(termoDeBusca);
        return registros;
    }

    @Override
    public int save(Profissional objeto) {
        int registro = 0;
        if (objeto.getId() == null){
            registro = pDao.insert(objeto);
        }
        else {
            registro = pDao.update(objeto);
        }
        return registro;
    }

    public Profissional processFormRequest(HttpServletRequest req, HttpServletResponse res){
        Profissional p = new Profissional();

        //Pegando dados passados através da requisão do tipo Http
        //Se o botão foi apertado
        String paramSubmit = req.getParameter("submit");
        String paramId = req.getParameter("id");
        String paramNome =  req.getParameter("nome");
        String paramRegistro = req.getParameter("registro");
        String paramEmail = req.getParameter("email");
        String paramTelefone = req.getParameter("telefone");
        String paramEspecialidade = req.getParameter("Especialidade");
        String paramUnidade = req.getParameter("Unidade"); //é o id ou é o nome da especialidade?

        if (paramId != null && !paramId.isEmpty()){ //Se existe ID, o usuário está editando
            Long id = Long.parseLong(paramId);
            p = this.get(id);
        }
        if (paramSubmit != null){
            p.setNome(paramNome);
            p.setRegistro(paramRegistro);
            p.setEmail(paramEmail);
            p.setTelefone(paramTelefone);

            Long id_unidade = Long.parseLong(paramUnidade);
            Long id_especialidade = Long.parseLong(paramEspecialidade);

            p.setEspecialidade(eDao.get(id_especialidade));
            p.setUnidade(uDao.get(id_unidade));

            try {
                res.sendRedirect("profissional.jsp");
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        return p;
    }

    public List<Profissional> processListRequest(HttpServletRequest req, HttpServletResponse res){
        List<Profissional> registros = new ArrayList<>();

        String paramExcluir = req.getParameter("excluir");
        if (paramExcluir != null && !paramExcluir.isEmpty()){
            Long id = Long.parseLong(paramExcluir);
            Profissional registro = this.get(id);
            this.delete(registro);
        }
        registros = this.get();
        return registros;
    }
    
}
