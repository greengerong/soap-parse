package green.github.greengerong.soap.parse;


import green.github.greengerong.soap.parse.model.GetOrdersHistoryResponse;
import green.github.greengerong.soap.parse.model.OrderStatus;
import green.github.greengerong.soap.parse.model.Statue;
import org.apache.commons.io.IOUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
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
    public void shouldGetNominationHistoryRes() throws IOException {
        //given
        final InputStream stream = SoapParserTest.class.getResourceAsStream("/GetOrdersHistoryResponse.xml");
        String xml = IOUtil.toString(stream);
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
}
