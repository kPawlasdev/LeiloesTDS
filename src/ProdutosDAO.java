/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        try{
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
        }catch(SQLException erro){
            erro.printStackTrace();
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar produto.");
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        try{
            String sql = "SELECT * FROM produtos";
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while(resultset.next()){
                ProdutosDTO  p = new ProdutosDTO();
                p.setId(resultset.getInt("id"));
                p.setNome(resultset.getString("nome"));
                p.setValor(resultset.getInt("valor"));
                p.setStatus(resultset.getString("status"));
                
                listagem.add(p);
            }
        }catch(SQLException erro){
            erro.printStackTrace();
        }
        
        return listagem;
    }
    
    public void venderProduto(int id){
        try{
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        }catch(SQLException erro){
            erro.printStackTrace();
            JOptionPane.showMessageDialog(null, "Falha ao vender produto.");
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        try{
            String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while(resultset.next()){
                ProdutosDTO  p = new ProdutosDTO();
                p.setId(resultset.getInt("id"));
                p.setNome(resultset.getString("nome"));
                p.setValor(resultset.getInt("valor"));
                p.setStatus(resultset.getString("status"));
                
                listagem.add(p);
            }
        }catch(SQLException erro){
            erro.printStackTrace();
        }
        return listagem;
    }
    
        
}

