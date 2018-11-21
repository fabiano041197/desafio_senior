/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Item;

/**
 *
 * @author fabia
 */
public class CtrlItem {
    public static Item getItem(Connection conn, int id){
        Item aux_item = null;        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from itens where id = "+id);
            
            while(rs.next()){
                String nome =rs.getString("nome");
                String un_medida  = rs.getString("un_medida");
                float preco = rs.getFloat("preco");
                boolean per = rs.getBoolean("perecivel");
                String data_validade = rs.getString("data_validade");
                int quantidade = rs.getInt("quantidade");
                String data_fabricacao = rs.getString("data_fab");
                
                aux_item = new Item(id, nome, un_medida, preco, per,quantidade, data_validade, data_fabricacao);
            }
        } catch (Exception e) {
        }
        return aux_item;
    }
    
    
    public static boolean setIncluiItem(Connection conn, Item item){
        boolean incluiu = false;
        Date aux_date_validade = null;
        Date aux_date_fab = null;
        if(item.getData_fabricacao() != null){
            aux_date_fab = Date.valueOf(item.getData_fabricacao());
        }       
        if(item.getData_validade()!= null){
            aux_date_validade = Date.valueOf(item.getData_validade());
        }  
        try {
            PreparedStatement pst = conn.prepareStatement("insert into itens(id, nome, un_medida, preco, perecivel, quantidade, data_validade, data_fab) values ((select coalesce(max(id),0)+1 from itens), ?,?,?,?,?,?,?)");
            pst.setString(1, item.getNome());
            pst.setString(2, item.getUn_medida());
            pst.setFloat(3, item.getPreco());
            pst.setBoolean(4, item.isPerecivel());
            pst.setInt(5, item.getQuantidade());
            pst.setDate(6, aux_date_validade);
            pst.setDate(7,aux_date_fab);
            if(!pst.execute()){
                incluiu = true;
            }            
        } catch (Exception e) {
            e.getMessage();
        }
        return incluiu;
    }
    
    public static boolean setAlteraItem(Connection conn, Item item){
        boolean alterou = false;
        try {
            PreparedStatement pst = conn.prepareStatement("update itens  set nome = ?, un_medida = ?, preco = ?, perecivel  = ?, quantidade = ? where id ="+item.getId());
            pst.setString(1, item.getNome());
            pst.setString(2, item.getUn_medida());
            pst.setFloat(3, item.getPreco());
            pst.setBoolean(4, item.isPerecivel());
            pst.setInt(5, item.getQuantidade());
            if(!pst.execute()){
                alterou = true;
            }            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
        return alterou;
    };
    
    public static boolean setExcluirItem(Connection conn, int id){
        boolean removeu = false;
        try {
            PreparedStatement pst = conn.prepareStatement("delete from itens where id="+id);
            if(!pst.execute()){
                removeu = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
        return removeu;
    }
    
    
    public static ArrayList<Item> getListItens(Connection conn){
        ArrayList<Item> aux_lista = new ArrayList<Item>();
        Item item = null;
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from itens order by id");
            while(rs.next()){
                int id = rs.getInt("id");
                String nome =rs.getString("nome");
                String un_medida  = rs.getString("un_medida");
                float preco = rs.getFloat("preco");
                boolean per = rs.getBoolean("perecivel");
                String data_validade = rs.getString("data_validade");
                String data_fabricacao = rs.getString("data_fab");
                int quantidade = rs.getInt("quantidade");
                
                item = new Item(id, nome, un_medida, preco, per, quantidade, data_validade, data_fabricacao);
                aux_lista.add(item);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
        return aux_lista;
    }
    
}
