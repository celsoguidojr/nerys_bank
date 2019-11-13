package br.com.sd1.nerys_bank;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class MainApp extends Application {
    private static Stage stage;
    private static Scene mainScene;
    private static Scene contaScene;
    
    
    @Override
    public void start(Stage stagePrincipal) throws Exception {
        stage =stagePrincipal;
        
        Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("/fxml/frmPrincipal.fxml"));
        mainScene = new Scene(fxmlPrincipal);
        
        Parent fxmlConta = FXMLLoader.load(getClass().getResource("/fxml/frmConta.fxml"));
        contaScene = new Scene(fxmlConta);
        
        mainScene.getStylesheets().add("/styles/frmprincipal.css");
        contaScene.getStylesheets().add("/styles/frmconta.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void mudarTela(String tela){
        switch(tela){
            case "principal":
                stage.setScene(mainScene);
            case "conta":
                stage.setScene(contaScene);
                stage.show();
                System.out.println("tela da conta");
        }
    }
    
    public static void main(String[] args) {
        launch(args);
        
    }

}
