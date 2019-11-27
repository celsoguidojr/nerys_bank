package br.com.sd1.nerys_bank;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp extends Application {
    private static Stage stage;
    private static Scene loginScene;
    private static Scene mainScene;
    private static Scene contaScene;
    private static Scene saldoScene;
    
        
    @Override
    public void start(Stage stagePrincipal) throws Exception {
        stage =stagePrincipal;
        
        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/Caixa/frmLogin.fxml"));
        loginScene = new Scene(fxmlLogin);
        
        Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("/Caixa/frmPrincipal.fxml"));
        mainScene = new Scene(fxmlPrincipal);
        
        Parent fxmlConta = FXMLLoader.load(getClass().getResource("/Administrativo/frmCriarConta.fxml"));
        contaScene = new Scene(fxmlConta);
        
        Parent fxmlSaldo = FXMLLoader.load(getClass().getResource("/Caixa/frmSaldo.fxml"));
        saldoScene = new Scene(fxmlSaldo);
        
        mainScene.getStylesheets().add("/styles/frmprincipal.css");
        contaScene.getStylesheets().add("/styles/frmconta.css");       
        
        stage.setTitle("Nerys Bank - Caixa");
        stage.setScene(loginScene);
        stage.show();
    }

    public static void mudarTela(String tela){
        switch(tela){
        	case "login":
        		stage.setScene(loginScene);
            case "principal":
                stage.setScene(mainScene);
            case "conta":
                stage.setScene(contaScene);
                System.out.println("tela da conta");
            case "saldo":
            	stage.setScene(saldoScene);
        }
    }
    
    public static void main(String[] args) {
    	SpringApplication.run(MainApp.class, args);
    	launch(args);
        
    }

}
