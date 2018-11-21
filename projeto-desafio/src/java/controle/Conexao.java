/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 5257
 */
public class Conexao {
    private static String driver = "org.postgresql.Driver";
    private static String host = "localhost";
    private static String banco = "desafio";
    private static String usuario = "postgres";
    private static String senha = "admin";
    private static String url = "jdbc:postgresql://"+host+":5432/"+banco;
    
    public static Connection getConexao(){
        Connection conexao = null;
        try{
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha); 
            System.out.println("Conexao Efetuada com Sucesso!");
        }
        catch(ClassNotFoundException erro){
            System.out.println("Erro de driver!");
        }
        catch(SQLException erro){
            System.out.println("Erro de conexão!");
        }
        finally{
            return conexao;
        }
    }
    
    public static void setFechaConexao(Connection conexao){
        try{
            if(conexao != null){
                conexao.close();
            }
        }
        catch(SQLException erro){
            System.out.println("Erro ao fechar conexão com o banco!");
        }
    }
    
}
