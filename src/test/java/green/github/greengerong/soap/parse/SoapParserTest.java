package green.github.greengerong.soap.parse;


import green.github.greengerong.soap.parse.model.GetNominationHistoryResponse;
import green.github.greengerong.soap.parse.model.NominationStatu;
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
        final InputStream stream = SoapParserTest.class.getResourceAsStream("/getNominationHistoryRes.xml");
        String xml = IOUtil.toString(stream);
        //when
        GetNominationHistoryResponse result = soapParser.getResult(xml, GetNominationHistoryResponse.class);

        //when
        final List<NominationStatu> nominationStatus = result.getNominationStatus();
        assertThat(nominationStatus.size(), is(2));
        final NominationStatu first = nominationStatus.get(0);
        assertThat(first.getAgentId(), is("agentId"));
        assertThat(first.getActionCode(), is("actionCode"));
        assertThat(first.getDateTime().toString(), is("2010-12-1"));
        assertThat(first.getStatue(), is(Statue.None));
    }
}
