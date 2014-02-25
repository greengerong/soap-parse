package green.github.greengerong.soap.parse;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class SoapParser {

    public <T> T getResult(String xml, Class<T> type) {
        final String soapBody = getSoapBody(xml);
        return getInstance(soapBody, type);
    }

    private String getSoapBody(String xml) {
        try {
            SOAPMessage message = getSoapMessage(xml);
            Node firstElement = getFirstElement(message);
            return toXml(firstElement);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String toXml(Node firstElement) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        final StringWriter sw = new StringWriter();
        transformer.transform(new DOMSource(firstElement), new StreamResult(sw));
        return sw.toString();
    }

    private SOAPMessage getSoapMessage(String xml) throws SOAPException, IOException {
        MessageFactory factory = MessageFactory.newInstance();
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        return factory.createMessage(new MimeHeaders(), byteArrayInputStream);
    }

    private Node getFirstElement(SOAPMessage message) throws SOAPException {
        final NodeList childNodes = message.getSOAPBody().getChildNodes();
        Node firstElement = null;
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i) instanceof Element) {
                firstElement = childNodes.item(i);
                break;
            }
        }
        return firstElement;
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
