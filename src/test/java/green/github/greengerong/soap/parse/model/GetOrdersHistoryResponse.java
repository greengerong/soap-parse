package green.github.greengerong.soap.parse.model;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "GetOrdersHistoryResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetOrdersHistoryResponse {

    private List<OrderStatus> orderStatuses;

    public List<OrderStatus> getOrderStatuses() {
        return orderStatuses;
    }

    public void setOrderStatuses(List<OrderStatus> orderStatuses) {
        this.orderStatuses = orderStatuses;
    }
}
