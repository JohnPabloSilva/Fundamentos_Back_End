package br.ufac.sgcm;

import java.sql.Connection;
import java.util.List;

import br.ufac.sgcm.dao.ConexaoDB;
import br.ufac.sgcm.dao.EspecialidadeDao;
import br.ufac.sgcm.model.Especialidade;
import br.ufac.sgcm.model.Unidade;
import br.ufac.sgcm.model.Profissional;
public class Teste {
    public static void main(String[] args) {
    
    //Criando um objeto profissional
    Profissional p1 = new Profissional();
    p1.setNome("Limeira");
    p1.setRegistro("123");
    p1.setTelefone("(68)9999-99999");
    p1.setEmail("Limeira@ufac.br");

    Unidade u1 = new Unidade();
    u1.setNome("Laboratório");
    u1.setEndereco("Av. Getúlio Vargas, nº100");
    
    p1.setUnidade(u1);

    Especialidade e1 = new Especialidade();
    e1.setNome("Otorrinolaringologia");

    p1.setEspecialidade(e1);

    System.out.println("Nome: "+p1.getNome());
    System.out.println("Especialidade: "+p1.getEspecialidade().getNome());
    System.out.println("Unidade: "+p1.getUnidade().getNome());

    //objeto da classe ConexaoDB

    ConexaoDB conexao = new ConexaoDB();
    Connection instancia = conexao.getConexao();
    // if (instancia != null){
    //     System.out.println("Conectou");
    // }
    // else{
    //     System.out.println("Falha na conexão");
    // }
    EspecialidadeDao edao = new EspecialidadeDao();
    //Inserindo uma especialidade
    // if (edao.insert(e1) == 1){
    //     System.out.println("Especialidade inserida com sucesso");
    // }
    // else{
    //     System.out.println("Falha ao inserir");
    //}

    // Especialidade e2 = new Especialidade();
    // e2.setNome("Teste");
    // e2.setId(10L);
    // edao.insert(e2);

    // //Deletanto
    // if (edao.delete(e2)==1){
    //     System.out.println("Deletado com sucesso");
    // }
    // else{
    //     System.out.println("Erro ao excluir");
    // }
    
    //Atualizar 
    // e1.setNome("TESTE");;
    // e1.setId(9L);
    // if (edao.update(e1)==1){
    //         System.out.println("Atualizado com sucesso");
    //     }
    //     else{
    //         System.out.println("Erro ao Atualizar");
    //     }

    //Lista das especialidades
    List<Especialidade> lista = edao.get("gia");
    for (Especialidade e: lista){
        // System.out.println(e.getId()+": "+e.getNome());
        System.out.println(e);
    
    }
    }
}
