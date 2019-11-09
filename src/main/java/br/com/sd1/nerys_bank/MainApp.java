package br.com.sd1.nerys_bank;

import br.com.sd1.nerys_bank.Modelo.Conta;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        ContaDAOImplementacao contaDAO = new ContaDAOImplementacao();

		Conta conta = new Conta();
		conta.setId(3);
		conta.setNome("Celso Gomes");
		conta.setSaldo(new BigDecimal(50000));
		
		contaDAO.inserir(conta);
		
		List<Conta> listaContas = contaDAO.listar("Celso Gomes");
	       
	       for (Iterator<Conta> i = listaContas.iterator(); i.hasNext();) {
	       	    Conta c = i.next();
	    	   System.out.println(c.toString());
	    	}
    }

}
