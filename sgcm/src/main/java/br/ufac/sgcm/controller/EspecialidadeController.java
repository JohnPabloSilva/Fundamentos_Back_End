package br.ufac.sgcm.controller;

import java.util.List;
import java.util.ArrayList;

import br.ufac.sgcm.dao.EspecialidadeDao;
import br.ufac.sgcm.model.Especialidade;

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

    
    
}
