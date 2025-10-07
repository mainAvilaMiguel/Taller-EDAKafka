package co.edu.uptc.edamicrokafka.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import co.edu.uptc.edamicrokafka.model.order.Order;
import co.edu.uptc.edamicrokafka.model.order.OrderEvent;
import co.edu.uptc.edamicrokafka.utils.JsonUtils;
import java.util.List;

@Service
public class OrderEventConsumer {

    @Autowired
    private OrderService orderService;
    private JsonUtils jsonUtils = new JsonUtils();

    @KafkaListener(topics = "order_events", groupId = "order_group")
    public void handleOrderEvents(String eventMessage) {
        OrderEvent event = jsonUtils.fromJson(eventMessage, OrderEvent.class);
        
        switch (event.getEventType()) {
            case ADD_ORDER:
                Order orderToAdd = jsonUtils.fromJson(event.getData(), Order.class);
                orderService.save(orderToAdd);
                break;
                
            case EDIT_ORDER:
                Order orderToEdit = jsonUtils.fromJson(event.getData(), Order.class);
                orderService.save(orderToEdit);
                break;
                
            case FIND_ORDER_BY_ID:
                String idToFind = event.getData();
                Order foundOrder = orderService.findById(Long.parseLong(idToFind));
                break;
                
            case FIND_ALL_ORDERS:
                List<Order> orders = orderService.findAll();
                break;
        }
    }
}