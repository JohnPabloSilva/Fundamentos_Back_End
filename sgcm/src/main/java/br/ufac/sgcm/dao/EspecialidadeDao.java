package br.ufac.sgcm.dao;

import br.ufac.sgcm.model.Especialidade;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EspecialidadeDao {

    Connection conexao;
    PreparedStatement ps;
    ResultSet rs;
    //Construtor 
    public EspecialidadeDao(){
        conexao = new ConexaoDB().getConexao();
    }
    //Metódos de acesso aos dados das Especialidades (mySQL)
    //Listar todas as especialidades
    public List<Especialidade> get(){
        List<Especialidade> registros = new ArrayList<>();
        try {
            //Conexão se trata de uma variável que conecta por meio do JBDC o SQL ao JAVA
            //PrepareStatement recebe uma instrução em SQL
            ps = conexao.prepareStatement("SELECT * FROM especialidade");
            //executeQuery retorna um resultSet que contém o registro de todas as linhas na tabela do Banco de dados
            rs = ps.executeQuery();
            //Laço de repetição que pergunta se haverá um proxímo dado para ser lido
            while(rs.next()) {//Passa para o próximo
                Especialidade obj = new Especialidade();
                //Como rs é um objeto, então para pegar seus valores usamos gets
                //O nome do get varia de acordo com os tipos de valores que se tem na coluna da tabela
                obj.setId(rs.getLong("id"));
                obj.setNome(rs.getString("nome"));

                registros.add(obj);   
            }
        }
        catch (SQLException e){
            e.printStackTrace(); //Pilha de erros
        }
        return registros; //Retorno da lista 
    }
    public List<Especialidade> get(String termoDeBusca){
        List<Especialidade> registros = new ArrayList<>();
        try {
            String sql = "SELECT * FROM especialidade WHERE nome LIKE ?";
            //Conexão se trata de uma variável que conecta por meio do JBDC o SQL ao JAVA
            //PrepareStatement recebe uma instrução em SQL
            ps = conexao.prepareStatement(sql);
            ps.setString(1, "%"+termoDeBusca+"%");
            //executeQuery retorna um resultSet que contém o registro de todas as linhas na tabela do Banco de dados
            rs = ps.executeQuery();
            //Laço de repetição que pergunta se haverá um proxímo dado para ser lido
            while(rs.next()) {//Passa para o próximo
                Especialidade obj = new Especialidade();
                //Como rs é um objeto, então para pegar seus valores usamos gets
                //O nome do get varia de acordo com os tipos de valores que se tem na coluna da tabela
                obj.setId(rs.getLong("id"));
                obj.setNome(rs.getString("nome"));

                registros.add(obj);   
            }
        }
        catch (SQLException e){
            e.printStackTrace(); //Pilha de erros
        }
        return registros; //Retorno da lista 
    }
    //Inserir Especialidade
    public int insert(Especialidade objeto){
        int registrosAfetados = 0;
        //O comando SQL não está completo pois é necessário saber o nome do objeto para a inserção
        String sql = "INSERT INTO especialidade (nome) VALUES (?)";
        try{
            ps = conexao.prepareStatement(sql);
            //Este comando preenche o ponto de interrogação da string SQL
            ps.setString(1, objeto.getNome());
            registrosAfetados = ps.executeUpdate();
            
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return registrosAfetados;
    }
    //Atualizar Especialidade 
    public int update(Especialidade objeto){
        int registrosAfetados = 0;
        String sql = "UPDATE especialidade SET nome=? WHERE id=?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setLong(2, objeto.getId());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }

    //Excluir Especialidade
    public int delete(Especialidade objeto){
        int registrosAfetados = 0;
        String sql = "DELETE FROM especialidade WHERE id=?";
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
