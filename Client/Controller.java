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
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static Base base = new Base();
    private static Client client;
    private static String nameBase;
    private Person actualPerson;

    @FXML public  TableView<Person> tablePerson;
    @FXML public  TableColumn<Person, String> Name;
    @FXML public  TableColumn<Person, String> LastName;
    @FXML public  TableColumn<Person, String> Email;
    @FXML public  TableColumn<Person, String> Pesel;

    @FXML public  TableView<Car> tableCar;
    @FXML public  TableColumn<Car, String> type;
    @FXML public  TableColumn<Car, String> validity;
    @FXML public  TableColumn<Car, String> registrationNumber;
    @FXML public  TableColumn<Car, String>  dateRegistration;
    @FXML public  TableColumn<Car, String> carBody;
    @FXML public  TableColumn<Car, String> numberDocument;

    @FXML private ComboBox<Person> comboBoxPerson;




    public void addPersonButtonClicked(ActionEvent event) throws IOException{

        closeStage(event);
        openStage("addPerson.fxml", null,null);

    }

    private void openStage(String link, Person person, Car car) throws IOException {
        Stage st = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(link));

        Parent sceneMain = loader.load();

        if ( link.contains("addPerson")) {
            ControllerAdd controller = loader.getController();
            controller.initBase(base);
        }
        if (link.contains("editPerson")){
            ControllerEditPerson controller = loader.getController();
            controller.initPerson(person);
            controller.initBase(base);
        }
        if (link.contains("registerCar")){
            ControllerRegisterCar controller = loader.getController();
            controller.initBase(base);
            controller.initPerson(person);
        }
        if (link.contains("start")){
            ControllerStart controller = loader.getController();
        }

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

    private void closeStage(ActionEvent event){
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    public void back(ActionEvent event) throws IOException {
        closeStage(event);
        openStage("start.fxml", null, null);
    }

    public void initBase(Base base){
        this.base = base;
        setItems();
    }

    public void initClient(Client client){
        this.client = client;
    }

    public void initName(String nameBase){
        this.nameBase = nameBase;
    }

    private ObservableList<Person> getPerson() {
        ObservableList<Person> people = FXCollections.observableArrayList();
        people.addAll(base.getPersonList());
        return people;
    }

    public void editPerson(ActionEvent event) throws IOException {
        Person p = comboBoxPerson.getValue();
        if ( p != null) {
            closeStage(event);
            openStage("editPerson.fxml", p, null);
        }
    }



    public void registerCar(ActionEvent event) throws IOException {
        if ( actualPerson != null) {
            closeStage(event);
            openStage("registerCar.fxml", actualPerson, null);
        }
    }

    public void showPerson(ActionEvent event) throws IOException {
        Person p = comboBoxPerson.getValue();
        if ( p != null) {
            closeStage(event);
            openStage("showPerson.fxml", p, null);
        }
    }

    public void sendToSerwerPerson(ActionEvent event) throws Exception {
        String xml = client.saveToXmlBase(base);
        try {
            Client.putFile(nameBase, new ByteArrayInputStream(xml.getBytes("UTF-8")));
        }
        catch ( Exception e){
            showAlert("cos nie dziala");
        }
    }

    private void showAlert(String messege){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message Here...");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("Valid data!");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }

    public void synchronizeAll(ActionEvent event) throws Exception {
        System.out.println("ok");
        String baseXml = Client.getFile(nameBase);
        base = Client.synchronizeBase(baseXml);
        setItems();

    }

    public void  setItems(){
        tablePerson.getItems().clear();
        tablePerson.setItems(getPerson());
        tableCar.getItems().clear();
        tableCar.setItems(getCar());

        comboBoxPerson.getItems().clear();
        comboBoxPerson.getItems().addAll(getPerson());
    }


    public void initialize(URL location, ResourceBundle resources) {

        Name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        LastName.setCellValueFactory(new PropertyValueFactory<Person, String>("surname"));
        Email.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        Pesel.setCellValueFactory(new PropertyValueFactory<Person, String>("pesel"));

        type.setCellValueFactory(new PropertyValueFactory<Car, String>("type"));
        validity.setCellValueFactory(new PropertyValueFactory<Car, String>("validity"));
        registrationNumber.setCellValueFactory(new PropertyValueFactory<Car, String>("registrationNumber"));
        dateRegistration.setCellValueFactory(new PropertyValueFactory<Car, String>("dateRegistration"));
        carBody.setCellValueFactory(new PropertyValueFactory<Car, String>("carBody"));
        numberDocument.setCellValueFactory(new PropertyValueFactory<Car, String>("numberDoc"));


        tablePerson.setRowFactory( tv -> {
            TableRow<Person> row = new TableRow<Person>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    actualPerson = row.getItem();
                    tableCar.getItems().setAll(actualPerson.getRegister());
                }
            });
            return row ;
        });

    }

    private ObservableList<Car> getCar() {
        ObservableList<Car> car = FXCollections.observableArrayList();
        car.addAll(base.getUnregistered());
        return  car;
    }

}
