package green.github.greengerong.soap.parse.model;


import javax.xml.bind.annotation.*;
import java.util.List;

//TODO: same name, we can remove name=xxx;

@XmlRootElement(name = "getNominationHistoryRes")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetNominationHistoryResponse {

    private List<NominationStatu> nominationStatus;

    public List<NominationStatu> getNominationStatus() {
        return nominationStatus;
    }

    public void setNominationStatus(List<NominationStatu> nominationStatus) {
        this.nominationStatus = nominationStatus;
    }
}
