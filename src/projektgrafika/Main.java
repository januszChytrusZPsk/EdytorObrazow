package projektgrafika;

import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("layout/MainView.fxml"));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("ico.jpg")));
        stage.setTitle("Filtrowanie Obrazów");
        stage.show();        
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
