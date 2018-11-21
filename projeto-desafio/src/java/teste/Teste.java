package teste;

import com.google.gson.Gson;
import controle.Conexao;
import controle.CtrlItem;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import modelo.Item;

/**
 *
 * @author fabia
 */
public class Teste {

    public static void main(String[] args) {
        Connection conn = Conexao.getConexao();

        Date date = Date.valueOf(LocalDate.now());

        Gson gson = new Gson();
        Item item = gson.fromJson("{\n"
                + "  \"nome\": \"Teste\",\n"
                + "  \"un_medida\": \"Quilograma\",\n"
                + "  \"preco\": \"123\",\n"
                + "  \"perecivel\": true\n"
                + "}", Item.class);
        boolean altera = CtrlItem.setIncluiItem(conn, item);
        System.out.println(altera);
        

    }
}
