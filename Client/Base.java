import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"personList", "unregistered"}
)
@XmlRootElement(
        name = "Base"
)
public class Base {
    @XmlElement(
            name = "personList",
            required = true
    )
    private List<Person> personList = new ArrayList();
    @XmlElement(
            name = "unregistered",
            required = true
    )
    private List<Car> unregistered = new ArrayList();

    public Base() {
    }

    public void addPerson(Person person) {
        if (this.personList.contains(person)) {
            throw new RuntimeException();
        } else {
            this.personList.add(person);
        }
    }

    public void unregisterCar(Car car) {
        this.unregistered.add(car);
    }

    public List<Person> getPersonList() {
        return this.personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<Car> getUnregistered() {
        return this.unregistered;
    }

    public void setUnregistered(List<Car> unregistered) {
        this.unregistered = unregistered;
    }
}
