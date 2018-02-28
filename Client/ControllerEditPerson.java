import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEditPerson {

    private  Base base;

    @FXML private TextField name;
    @FXML private TextField surname;
    @FXML private TextField email;
    @FXML private TextField pesel;
    @FXML private Text editSucces;

    private Person personEdit;

    public void deleteSucces(){
        editSucces.setText("");
    }

    public void resetAll(){
        name.clear();
        surname.clear();
        email.clear();
        pesel.clear();
        deleteSucces();
    }

    public void initBase(Base base){
        this.base = base;
    }

    public  void initPerson(Person person){
        this.personEdit = person;
        personEdit.getEmail();
        name.setText(personEdit.getName());
        surname.setText(personEdit.getSurname());
        pesel.setText(personEdit.getPesel());
        email.setText(personEdit.getEmail());
    }

    public void editSuccesContinueClicked() throws InterruptedException {
        personEdit.setEmail(email.getText());
        personEdit.setName(name.getText());
        personEdit.setSurname(surname.getText());
        personEdit.setPesel(pesel.getText());
        editSucces.setText("Edit Person");
    }


    public void BackButtonClicked(ActionEvent event) throws IOException{

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();

        Stage st = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));

        Parent sceneMain = loader.load();

        Controller controller = loader.<Controller>getController();
        controller.initBase(base);

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

    public void stop() throws Exception {
        Client.httpClient.stop();
    }
}
