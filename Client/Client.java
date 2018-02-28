import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.InputStreamContentProvider;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;

public class Client {
    public static final HttpClient httpClient = new HttpClient();

    static {
        httpClient.setFollowRedirects(false);
        // Start HttpClient
        try {
            httpClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getList() throws Exception {
        ContentResponse response = httpClient.GET("http://127.0.0.1:2501/list");
        return response.getContentAsString();
    }

    public static String getFile(String filename) throws Exception {
        ContentResponse response = httpClient.GET("http://127.0.0.1:2501/get/" + filename);
        return response.getContentAsString();
    }

    public static String getFile(String filename, String datestamp) throws Exception {
        ContentResponse response = httpClient.GET("http://127.0.0.1:2501/get/" + filename + "-" + datestamp);
        return response.getContentAsString();
    }

    public static void putFile(String filename, InputStream content) throws Exception {
        ContentResponse response = httpClient.POST("http://127.0.0.1:2501/put/" + filename)
                .content(new InputStreamContentProvider(content))
                .send();
    }

    public static FilesList getFilesList() throws Exception {
        String content = getList();

        JAXBContext jaxbContext = JAXBContext.newInstance(FilesList.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(content);
        FilesList list = (FilesList) unmarshaller.unmarshal(reader);


//        for ( XMLFile f : list.files ) {
//            System.out.println(f.filename);
//            System.out.println(f.lastModifyDate);
//            System.out.println(f.lastChecksum);
//            System.out.println(f.dates.size());
//
//            for(XMLFile.DateStamp date : f.dates) {
//                System.out.println(date.datestamp);
//                System.out.println(date.checksum);
//            }
//        }

        return list;
    }

    public static Base synchronizeBase(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Base.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        Base base = (Base)unmarshaller.unmarshal(reader);
        return base;
    }

    public static String saveToXmlPerson(Person person){
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

            StringWriter sw = new StringWriter();
            m.marshal(person, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlString;
    }

    public static String saveToXmlCar(Car car){
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Car.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

            StringWriter sw = new StringWriter();
            m.marshal(car, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlString;
    }

    public static String saveToXmlBase(Base base){
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Base.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

            StringWriter sw = new StringWriter();
            m.marshal(base, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlString;
    }
}
