package green.github.greengerong.soap.parse;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class SoapParser {

    public static final String SOAP_ENV_BODY = "SOAP-ENV:Body";

    public <T> T getResult(String xml, Class<T> type) {
        return getInstance(getSoapBody(xml), type);
    }

    private String getSoapBody(String xml) {
        final String startTag = String.format("<%s>", SOAP_ENV_BODY);
        final int start = xml.indexOf(startTag) + startTag.length();
        final int end = xml.indexOf(String.format("</%s>", SOAP_ENV_BODY));
        return xml.substring(start + 1, end);
    }


    private <T> T getInstance(String body, Class<T> type) {
        T instance = null;
        final StringReader reader = new StringReader(body.trim());
        try {
            JAXBContext jc = JAXBContext.newInstance(type);
            Unmarshaller u = jc.createUnmarshaller();
            instance = (T) u.unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return instance;
    }

}
