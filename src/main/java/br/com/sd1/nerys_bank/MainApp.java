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
    private static Scene saqueScene;
    private static Scene depositoScene;
    private static Scene transferenciaScene; 
    private static Scene extratoScene; 
    
    @Override
    public void start(Stage stagePrincipal) throws Exception {
        stage = stagePrincipal;
        
        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/Caixa/frmLogin.fxml"));
        loginScene = new Scene(fxmlLogin);
        
        Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("/Caixa/frmPrincipal.fxml"));
        mainScene = new Scene(fxmlPrincipal);
        
        Parent fxmlConta = FXMLLoader.load(getClass().getResource("/Administrativo/frmCriarConta.fxml"));
        contaScene = new Scene(fxmlConta);
        
        Parent fxmlSaldo = FXMLLoader.load(getClass().getResource("/Caixa/frmSaldo.fxml"));
        saldoScene = new Scene(fxmlSaldo);
        
        Parent fxmlSaque = FXMLLoader.load(getClass().getResource("/Caixa/frmSaque.fxml"));
        saqueScene = new Scene(fxmlSaque);
        
        Parent fxmlDeposito = FXMLLoader.load(getClass().getResource("/Caixa/frmDeposito.fxml"));
        depositoScene = new Scene(fxmlDeposito);
        
        Parent fxmlTransferencia = FXMLLoader.load(getClass().getResource("/Caixa/frmTransferencia.fxml"));
        transferenciaScene = new Scene(fxmlTransferencia);
        
        Parent fxmlExtrato = FXMLLoader.load(getClass().getResource("/Caixa/frmExtrato.fxml"));
        extratoScene = new Scene(fxmlExtrato);
        
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
        		break;
            case "principal":
                stage.setScene(mainScene);
                break;
            case "conta":
                stage.setScene(contaScene);
                break;
            case "saldo":
            	stage.setScene(saldoScene);
            	break;
            case "saque":
            	stage.setScene(saqueScene);
            	break;
            case "deposito":
            	stage.setScene(depositoScene);
            	break;
            case "transferencia":
            	stage.setScene(transferenciaScene);
            	break;
            case "extrato":
            	stage.setScene(extratoScene);
            	break;
        }
    }
    
    public static void main(String[] args) {
    	SpringApplication.run(MainApp.class, args);
    	launch(args);        
    }

}
