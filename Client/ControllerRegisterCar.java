import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ResourceBundle;

public class ControllerRegisterCar  {

    private  Base base;


    @FXML private Text personText;
    @FXML private Text succesAdd;

    private Person personAddCar;

    @FXML private TextField type;
    @FXML private TextField validity;
    @FXML private TextField registrationNumber;
    @FXML private TextField dateRegistration;
    @FXML private TextField carBody;
    @FXML private TextField numberDocument;
    @FXML private Text addCar;

    public void deleteSucces(){
        addCar.setText("");
    }


    public void resetAll(){
        type.clear();
        validity.clear();
        registrationNumber.clear();
        dateRegistration.clear();
        carBody.clear();
        numberDocument.clear();
        deleteSucces();
    }


    public void initBase(Base base){
        this.base = base;
    }

    public  void initPerson(Person person){
        this.personAddCar = person;
        personText.setText(person.toString());
    }

    public void addCar(ActionEvent event) throws IOException {
        Car car = new Car();
        car.setType(type.getText());
        car.setValidity(validity.getText());
        car.setRegistrationNumber(registrationNumber.getText());
        car.setDateRegistration(dateRegistration.getText());
        car.setCarBody(carBody.getText());
        car.setNumberDoc(numberDocument.getText());
        personAddCar.registerCar(car);
        BackButtonClicked(event);
    }

    public void pressedCombo(){
        succesAdd.setText("");
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



}
