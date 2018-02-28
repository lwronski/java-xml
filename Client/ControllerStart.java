import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerStart implements Initializable {

    private Base base;

    @FXML private ComboBox<XMLFile> comboBox;
    @FXML private ComboBox<XMLFile.DateStamp> comboBoxData;
    @FXML private TextField nameBaseField;

    private static Client client;

    static {
        try {
            client = new Client();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        FilesList filesList = null;
        try {
            filesList = Client.getFilesList();
            comboBox.getItems().addAll(filesList.files);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addDate(){
        comboBoxData.getItems().addAll(comboBox.getValue().dates);
    }


    public void ButtonClicked(ActionEvent event) throws Exception{

        base = new Base();
        XMLFile xmlFile = comboBox.getValue();
        XMLFile.DateStamp dateStamp = comboBoxData.getValue();
        if ( xmlFile != null && dateStamp != null ) {
            String baseXml = Client.getFile(xmlFile.filename, dateStamp.datestamp);
            base = Client.synchronizeBase(baseXml);

            openNewStage(base, xmlFile.filename,event);
        }

    }

    public void addNewBase(ActionEvent event) throws IOException {
        Base base = new Base();
        openNewStage(base, nameBaseField.getText(),event);
    }

    private void openNewStage(Base base, String nameBase, ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();

        Stage st = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));

        Parent sceneMain = loader.load();

        Controller controller = loader.<Controller>getController();
        controller.initBase(base);
        controller.initClient(client);
        controller.initName(nameBase);

        Scene scene = new Scene(sceneMain);
        st.setScene(scene);
        st.setOnCloseRequest(new EventHandler<WindowEvent>() {

            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        st.show();
    }

}
