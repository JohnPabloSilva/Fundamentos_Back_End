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
        String sql = "SELECT * FROM profissional WHERE id=?";
        Profissional p = new Profissional();
        try{
            ps = conexao.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                p.setEmail(rs.getString("email"));
                p.setRegistro(rs.getString("registro_conselho"));
                p.setTelefone(rs.getString("telefone"));
                p.setEspecialidade(eDao.get(rs.getLong("especialidade_id")));      
                p.setUnidade(uDao.get(rs.getLong("unidade_id")));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return p;
    }

    //Listar todos os profissionais de acordo com um termo de busca
    @Override
    public List<Profissional> get(String termoDeBusca) {
        List<Profissional> registros = new ArrayList<>();
         String sql = "SELECT * FROM profissional WHERE nome LIKE ?";
        try{
            ps = conexao.prepareStatement(sql);
            ps.setString(1, "%"+termoDeBusca+"%");
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
    
    //Inserir
    @Override
    public int insert(Profissional objeto) {
        //Tem que pensar em caso a Unidade e a Especialidade não esteja setado
        int linhasAfetadas = 0;
        String sql = "INSERT INTO profissional (nome, email, registro_conselho, telefone, especialidade_id, unidade_id ) values(?, ?, ?, ?, ?, ?)";
        try{
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getEmail());
            ps.setString(3, objeto.getRegistro());
            ps.setString(4, objeto.getTelefone());
            ps.setLong(5, objeto.getEspecialidade().getId());
            ps.setLong(6, objeto.getUnidade().getId());
            linhasAfetadas = ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return linhasAfetadas;
    }
    
    //Atualizar
    @Override
    public int update(Profissional objeto) {
        int linhasAfetadas = 0;
        String sql = "UPDATE profissional SET nome=?, email=?, telefone=?, registro_conselho=?, unidade_id=?, especialidade_id=?  WHERE id=?";
        try{
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getEmail());
            ps.setString(3, objeto.getTelefone());
            ps.setString(4, objeto.getRegistro());
            ps.setLong(5, objeto.getUnidade().getId());
            ps.setLong(6, objeto.getEspecialidade().getId());
            ps.setLong(7, objeto.getId());
            linhasAfetadas = ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

    //Deletar
    @Override
    public int delete(Profissional objeto) {
        int registrosAfetados = 0;
        String sql = "DELETE FROM profissional WHERE id=?";
        try{
            ps = conexao.prepareStatement(sql);
            ps.setLong(1,objeto.getId());
            registrosAfetados = ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return registrosAfetados;
    }
    
}
