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
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class SoapParser {

    public <T> T getResult(String xml, Class<T> type) {
        final Node soapBody = getSoapBody(xml);
        return getInstance(soapBody, type);
    }

    private Node getSoapBody(String xml) {
        try {
            SOAPMessage message = getSoapMessage(xml);
            Node firstElement = getFirstElement(message);
            return firstElement;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    private <T> T getInstance(Node body, Class<T> type) {
        try {
            JAXBContext jc = JAXBContext.newInstance(type);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal(body);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
