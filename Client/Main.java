
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Client client = new Client();
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        primaryStage.setTitle("Client");
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws JAXBException {

        launch(args);

    }
}
