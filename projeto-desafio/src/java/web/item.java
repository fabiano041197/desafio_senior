/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import com.google.gson.Gson;
import controle.Conexao;
import controle.CtrlItem;
import java.sql.Connection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Item;
import modelo.Status;

/**
 * REST Web Service
 *
 * @author fabia
 */
@Path("item")
public class item {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of item
     */
    public item() {
    }

    /**
     * Retrieves representation of an instance of web.item
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getListJson() {
        Connection conn = Conexao.getConexao();
        Gson gson = new Gson();

        String dados = "[]";

        dados = gson.toJson(CtrlItem.getListItens(conn));

        return dados;

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("id") int id) {
        Connection conn = Conexao.getConexao();
        Gson gson = new Gson();
        String dados = "[]";

        dados = gson.toJson(CtrlItem.getItem(conn, id));
        return dados;

    }

    /**
     * PUT method for updating or creating an instance of item
     *
     * @param content representation for the resource
     */
    @PUT
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String putJson(String content) {
        Item item = null;
        Gson gson = new Gson();
        Connection conn = Conexao.getConexao();

        item = gson.fromJson(content, Item.class);
        Status status = new Status("NAO FOI POSSIVEL ALTERAR");

        if (CtrlItem.setAlteraItem(conn, item)) {
            status.setStatus("ALTERADO COM SUCESSO");
        }

            
        return gson.toJson(status);
    }
    
    @POST
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postJson(String content) {
        Item item = null;
        Gson gson = new Gson();
        Connection conn = Conexao.getConexao();

        item = gson.fromJson(content, Item.class);
        Status status = new Status("NAO FOI POSSIVEL CADASTRAR");

        if (CtrlItem.setIncluiItem(conn, item)) {
            status.setStatus("INCLUIDO COM SUCESSO");
        }
   
        return gson.toJson(status);
    }
    
    @DELETE
    @Path("/{id}")
     public String deleteJson(@PathParam("id") int id) {
        Gson gson = new Gson();
        Connection conn = Conexao.getConexao();

        Status status = new Status("NAO FOI POSSIVEL EXCLUIR");

        if (CtrlItem.setExcluirItem(conn, id)) {
            status.setStatus("EXCLUIDO COM SUCESSO");
        }
   
        return gson.toJson(status);
    }
    
}
