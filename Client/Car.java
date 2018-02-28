import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"type", "carBody", "dateRegistration", "numberDoc", "validity", "registrationNumber"}
)
@XmlRootElement(
        name = "Person"
)
public class Car {
    @XmlElement(
            name = "type",
            required = true
    )
    private String type;
    @XmlElement(
            name = "validity",
            required = true
    )
    private String validity;
    @XmlElement(
            name = "registrationNumber",
            required = true
    )
    private String registrationNumber;
    @XmlElement(
            name = "dateRegistration",
            required = true
    )
    private String dateRegistration;
    @XmlElement(
            name = "carBody",
            required = true
    )
    private String carBody;
    @XmlElement(
            name = "numberDoc",
            required = true
    )
    private String numberDoc;

    public Car() {
    }

    public String getNumberDoc() {
        return this.numberDoc;
    }

    public void setNumberDoc(String numberDoc) {
        this.numberDoc = numberDoc;
    }

    public String getCarBody() {
        return this.carBody;
    }

    public void setCarBody(String carBody) {
        this.carBody = carBody;
    }

    public String getDateRegistration() {
        return this.dateRegistration;
    }

    public void setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getValidity() {
        return this.validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return type + " " + validity;
    }
}
