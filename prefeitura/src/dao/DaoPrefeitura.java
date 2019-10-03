/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Prefeitura;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Evelin
 */
public class DaoPrefeitura {
    
     public static boolean inserir(Prefeitura objeto) {
        String sql = "INSERT INTO prefeitura (funcionarios, endereco nome) VALUES (?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getFuncionarios());
            ps.setString(2, objeto.getEndereco());
            ps.setString(3, objeto.getNome());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
      public static void main(String[] args) {
        Prefeitura objeto = new Prefeitura();
        
        objeto.setFuncionarios(15);
        objeto.setEndereco("BR");
        objeto.setNome("Brasil");
        
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
       public static boolean alterar(Prefeitura objeto) {
        String sql = "UPDATE prefeitura SET funcionarios = ?, endereco = ?, nome = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getFuncionarios());
            ps.setString(2, objeto.getNome()); 
            ps.setString(3, objeto.getEndereco());
            ps.setInt(4, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
       public static boolean excluir(Prefeitura objeto) {
        String sql = "DELETE FROM prefeitura WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
       
       public static List<Prefeitura> consultar() {
        List<Prefeitura> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, funcionarios, endereco, nome FROM prefeitura";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prefeitura objeto = new Prefeitura();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setFuncionarios(rs.getInt("funcionarios"));
                objeto.setEndereco(rs.getString("endereco"));
                objeto.setNome(rs.getString("nome"));
                
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
        public static Prefeitura consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, funcionarios, endereco, nome FROM prefeitura WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prefeitura objeto = new Prefeitura();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setFuncionarios(rs.getInt("funcionarios"));
                objeto.setEndereco(rs.getString("endereco"));
                objeto.setNome(rs.getString("nome"));
                
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
       
}
