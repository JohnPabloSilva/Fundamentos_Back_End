package br.ufac.sgcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.model.Unidade;

public class UnidadeDao implements IDao<Unidade>  {

    private Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;

    public UnidadeDao(){
        conexao = new ConexaoDB().getConexao();
    }
    
    public Unidade get(Long id){
        String sql = "SELECT * FROM unidade WHERE id=?";
        Unidade registro = new Unidade();
        try {
            ps = conexao.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                registro.setId(rs.getLong("id"));
                registro.setNome(rs.getString("nome"));
                registro.setEndereco(rs.getString("endereco"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return registro;
    }

    @Override
    public int delete(Unidade objeto) {
        int linhasAfetadas = 0;
        String sql = "DELETE FROM unidade WHERE id=?";
        try{
            ps = conexao.prepareStatement(sql);
            ps.setLong(1, objeto.getId());
            linhasAfetadas = ps.executeUpdate();
            
        } 
        catch (SQLException e){
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

    @Override
    public List<Unidade> get() {
        List<Unidade> lista = new ArrayList<>();
        String sql = "SELECT * FROM unidade";
        try{
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Unidade obj = new Unidade();
                obj.setId(rs.getLong("id"));
                obj.setNome(rs.getString("nome"));
                obj.setEndereco(rs.getString("endereco"));

                lista.add(obj);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Unidade> get(String termoDeBusca) {
        List<Unidade> lista = new ArrayList<>();
        String sql = "SELECT * FROM unidade WHERE nome LIKE ?";
        try{
            ps = conexao.prepareStatement(sql);
            ps.setString(1, "%"+termoDeBusca+"%");
            rs = ps.executeQuery();

            while(rs.next()){
                Unidade obj = new Unidade();
                obj.setId(rs.getLong("id"));
                obj.setNome(rs.getString("nome"));
                obj.setEndereco(rs.getString("endereco"));

                lista.add(obj);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public int insert(Unidade objeto) {
        int linhasAfetadas = 0;
        String sql = "INSERT INTO unidade (nome, endereco) VALUES(?, ?)";
        try{
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getEndereco());
            linhasAfetadas = ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

    @Override
    public int update(Unidade objeto) {
        int linhasAfetadas = 0;
        String sql = "UPDATE unidade SET  nome=?, endereco=? WHERE id=? ";
        try{
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getEndereco());
            ps.setLong(3, objeto.getId());
            linhasAfetadas = ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return linhasAfetadas;
    }
    
}
