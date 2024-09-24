package br.ufac.sgcm;

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
    e1.setNome("Pediatria");

    p1.setEspecialidade(e1);

    System.out.println("Nome: "+p1.getNome());
    System.out.println("Especialidade: "+p1.getEspecialidade().getNome());
    System.out.println("Unidade: "+p1.getUnidade().getNome());
    }
}
