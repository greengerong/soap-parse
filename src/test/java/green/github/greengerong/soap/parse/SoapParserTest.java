package green.github.greengerong.soap.parse;


import com.google.common.collect.Lists;
import green.github.greengerong.soap.parse.model.GetOrdersHistoryResponse;
import green.github.greengerong.soap.parse.model.OrderStatus;
import green.github.greengerong.soap.parse.model.Statue;
import org.apache.commons.io.IOUtil;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.*;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SoapParserTest {

    private SoapParser soapParser;

    @Before
    public void setUp() throws Exception {
        soapParser = new SoapParser();
    }

    @Test
    public void shouldGetOrdersHistoryRes() throws IOException {
        //given
        String xml = getResponse();
        //when
        GetOrdersHistoryResponse result = soapParser.getResult(xml, GetOrdersHistoryResponse.class);

        //when
        final List<OrderStatus> orderStatuses = result.getOrderStatuses();
        assertThat(orderStatuses.size(), is(2));
        final OrderStatus first = orderStatuses.get(0);
        assertThat(first.getCustomerId(), is("customerId"));
        assertThat(first.getActionCode(), is("actionCode"));
        assertThat(first.getCreateBy().toString(), is("2010-12-1"));
        assertThat(first.getStatue(), is(Statue.None));
    }

    private String getResponse() throws IOException {
        final InputStream stream = SoapParserTest.class.getResourceAsStream("/GetOrdersHistoryResponse.xml");
        return IOUtil.toString(stream);
    }
}
