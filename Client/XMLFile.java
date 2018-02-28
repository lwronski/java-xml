import javax.xml.bind.annotation.*;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class XMLFile {
    @XmlAttribute(name="filename")
    public String filename;

    @XmlAttribute(name="lastModifyDate")
    public String lastModifyDate;

    @XmlAttribute(name="lastChecksum")
    public String lastChecksum;

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class DateStamp {
        @XmlAttribute(name="datestamp")
        public String datestamp;

        @XmlAttribute(name="checksum")
        public String checksum;

        @XmlTransient
        public File file;

        public DateStamp() {}

        public DateStamp(String datestamp, String checksum, File file) {
            this.datestamp = datestamp;
            this.checksum = checksum;
            this.file = file;
        }

        @Override
        public String toString(){
            return datestamp;
        }
    }

    @XmlElement(name = "history")
    public ArrayList<DateStamp> dates = new ArrayList<DateStamp>();

    @XmlTransient
    public File file;

    public XMLFile() {}

    public File getDatestamp(String datestamp) {
        for ( DateStamp ds : this.dates ) {
            if ( ds.datestamp.equals(datestamp) ) {
                return ds.file;
            }
        }
        return null;
    }

    public XMLFile(File base) throws ParseException {
        this.file = base;

        String[] parts = base.getName().split("-");

        this.filename = parts[0];
        this.lastModifyDate = parts[1];
        this.lastChecksum = parts[2].substring(0, parts[2].length()-4);

        this.dates.add(new DateStamp(this.lastModifyDate, this.lastChecksum, this.file));
    }

    public void addDate(String datestamp, String checksum, File file) {
        this.dates.add(new DateStamp(datestamp, checksum, file));

        if (Long.parseLong(datestamp) > Long.parseLong(this.lastModifyDate)) {
            this.lastModifyDate = datestamp;
            this.lastChecksum = checksum;
            this.file = file;
        }
    }

    @Override
    public String toString(){
        return filename;
    }
}