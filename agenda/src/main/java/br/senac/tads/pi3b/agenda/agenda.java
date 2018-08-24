/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3b.agenda;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rubens.fhuneke
 */
public class agenda {
    
    private Connection obterConexao () throws ClassNotFoundException, SQLException {
        
        Connection conn = null;
        //passo1: registrar driver JDBC
        Class.forName("com.mysql.jdbc.Driver");
        
        //passo2>: obter a conexao
        
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/agendabd",
                "root",
                "");
        
        return conn;
        
    }
    
     public void executar(){
        String querySql = "SELECT ID, NOME, DTNASCIMENTO FROM PESSOA";
        
        try(Connection conn = obterConexao();
            PreparedStatement stmt = conn.prepareStatement(querySql);
                ResultSet resultado = stmt.executeQuery();){
            
            while(resultado.next()){
                long id = resultado.getLong("ID");
                String nome = resultado.getString("NOME");
                Date dtNascimento = resultado.getDate("DTNASCIMENTO");
                System.out.println(id + " " + nome + " " + dtNascimento);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(agenda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
    
    public static void main(String[] args){
        agenda agenda = new agenda();
        agenda.executar();
    }
}
