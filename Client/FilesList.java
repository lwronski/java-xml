import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "files")
@XmlAccessorType(XmlAccessType.FIELD)
public class FilesList {
    @XmlElement(name = "xmlfield")
    public ArrayList<XMLFile> files;
}
