package co.edu.uptc.edamicrokafka.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import co.edu.uptc.edamicrokafka.model.order.Order;
import co.edu.uptc.edamicrokafka.model.order.OrderEvent;
import co.edu.uptc.edamicrokafka.model.order.EventOrderType;
import co.edu.uptc.edamicrokafka.utils.JsonUtils;

@Service
public class OrderEventProducer {
    private static final String TOPIC = "order_events";
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private JsonUtils jsonUtils = new JsonUtils();

    private void sendOrderEvent(EventOrderType eventType, String data) {
        OrderEvent event = new OrderEvent();
        event.setEventType(eventType);
        event.setData(data);
        kafkaTemplate.send(TOPIC, jsonUtils.toJson(event));
    }

    public void sendAddOrderEvent(Order order) {
        sendOrderEvent(EventOrderType.ADD_ORDER, jsonUtils.toJson(order));
    }

    public void sendEditOrderEvent(Order order) {
        sendOrderEvent(EventOrderType.EDIT_ORDER, jsonUtils.toJson(order));
    }

    public void sendFindByOrderIDEvent(String orderId) {
        sendOrderEvent(EventOrderType.FIND_ORDER_BY_ID, orderId);
    }

    public void sendFindAllOrdersEvent() {
        sendOrderEvent(EventOrderType.FIND_ALL_ORDERS, "");
    }
}