import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"name", "surname", "pesel", "email", "register"}
)
@XmlRootElement(
        name = "Person"
)
public class Person {
    @XmlElement(
            name = "Name",
            required = true
    )
    private String name;
    @XmlElement(
            name = "Surname",
            required = true
    )
    private String surname;
    @XmlElement(
            name = "Pesel",
            required = true
    )
    private String pesel;
    @XmlElement(
            name = "Email",
            required = true
    )
    private String email;
    @XmlElement(
            name = "register",
            required = true
    )
    private List<Car> register = new ArrayList();

    public Person() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return this.pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Car> getRegister() {
        return this.register;
    }

    public void setRegister(List<Car> register) {
        this.register = register;
    }

    public void registerCar(Car car) {
        this.register.add(car);
    }

    public String getTotalName(){
        return this.name + " " + this.surname;
    }

    @Override
    public String toString(){
        return "name: " + name + " surname: " + surname + " email: " + email + " pesel: " + pesel;
    }
}
