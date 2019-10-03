/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DaoPrefeitura;
import javax.swing.JOptionPane;
import modelo.Prefeitura;
import tela.manutencao.ManutencaoPrefeitura;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Evelin
 */
public class ControladorPrefeitura {

   public static void inserir(ManutencaoPrefeitura man){
        Prefeitura objeto = new Prefeitura();
        objeto.setFuncionarios(Integer.parseInt(man.jtfFuncionarios.getText()));
        objeto.setEndereco(man.jtfEndereco.getText());
        objeto.setNome(man.jtfNome.getText());
        
        boolean resultado = DaoPrefeitura.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}

 public static void alterar(ManutencaoPrefeitura man){
        Prefeitura objeto = new Prefeitura ();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
        objeto.setFuncionarios(Integer.parseInt(man.jtfFuncionarios.getText()));
        objeto.setEndereco(man.jtfEndereco.getText());
        objeto.setNome(man.jtfNome.getText());
        
        
        boolean resultado = DaoPrefeitura.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    public static void excluir(ManutencaoPrefeitura man){
        Prefeitura objeto = new Prefeitura();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoPrefeitura.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Codigo");
        modelo.addColumn("Funcionários");
        modelo.addColumn("Endereço");
        modelo.addColumn("Nome");
        
        
        List<Prefeitura> resultados = DaoPrefeitura.consultar();
        for (Prefeitura objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getFuncionarios());
            linha.add(objeto.getEndereco());
            linha.add(objeto.getNome());
            
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
    public static void atualizaCampos(ManutencaoPrefeitura man, int pk){ 
        Prefeitura objeto = DaoPrefeitura.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        man.jtfFuncionarios.setText(objeto.getCodigo().toString());
        man.jtfEndereco.setText(objeto.getEndereco());
        man.jtfNome.setText(objeto.getNome());
        
        
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
    
}
