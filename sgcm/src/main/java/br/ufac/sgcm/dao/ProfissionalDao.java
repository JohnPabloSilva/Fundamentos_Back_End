package br.ufac.sgcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.model.Especialidade;
import br.ufac.sgcm.model.Profissional;
//Como a interface trata de um tipo genérico, então é necessário especificar o tipo tratado na classe que implementa Idao
public class ProfissionalDao implements IDao<Profissional>{
    private Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;
    private EspecialidadeDao eDao = new EspecialidadeDao();
    private UnidadeDao uDao = new UnidadeDao();

    //Construtor
    public ProfissionalDao(){
        conexao = new ConexaoDB().getConexao();
    }

    //Listar todos os profissionais
    public List<Profissional> get(){
        List<Profissional> registros = new ArrayList<>();
        String sql = "SELECT * FROM profissional";
        try{
            ps = conexao.prepareStatement(sql);
            //Executando o script do SQL
            rs = ps.executeQuery();
            while(rs.next()){
                Profissional p = new Profissional();
                p.setId(rs.getLong("id"));
                p.setEmail(rs.getString("email"));
                p.setNome(rs.getString("nome"));
                p.setRegistro(rs.getString("registro_conselho"));
                p.setTelefone(rs.getString("telefone"));

                //Como especialidade se trata de um objeto, é necessário usar o Dao para pegar a especialidade em espécifico             
                Long id = rs.getLong("especialidade_id");
                Especialidade esp = eDao.get(id);
                p.setEspecialidade(esp);

                p.setUnidade(uDao.get(rs.getLong("unidade_id")));

                registros.add(p);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return registros;
        
    }
    
    @Override
    public Profissional get(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    //Listar todos os profissionais de acordo com um termo de busca
    @Override
    public List<Profissional> get(String termoDeBusca) {
        // TODO Auto-generated method stub
        return null;
    }
    
    //Inserir
    @Override
    public int insert(Profissional objeto) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    //Atualizar
    @Override
    public int update(Profissional objeto) {
        // TODO Auto-generated method stub
        return 0;
    }

    //Deletar
    @Override
    public int delete(Profissional objeto) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
