package co.edu.uptc.edamicrokafka.service.customer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import co.edu.uptc.edamicrokafka.model.customer.Customer;
import co.edu.uptc.edamicrokafka.model.customer.CustomerEvent;
import co.edu.uptc.edamicrokafka.model.customer.EventCustomerType;
import co.edu.uptc.edamicrokafka.utils.JsonUtils;

@Service
public class CustomerEventProducer {
    private static final String TOPIC = "customer_events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private void sendCustomerEvent(EventCustomerType eventCustomerType, String data) {
        CustomerEvent customerEvent = new CustomerEvent();
        customerEvent.setEventType(eventCustomerType);
        customerEvent.setData(data);
        JsonUtils jsonUtils = new JsonUtils();
        kafkaTemplate.send(TOPIC, jsonUtils.toJson(customerEvent));
    }

    public void sendAddCustomerEvent(Customer customer, String password) {
        Map<String, Object> customerData = new HashMap<>();
        JsonUtils jsonUtils = new JsonUtils();
        customerData.put("customer", customer);
        customerData.put("password", password);
        sendCustomerEvent(EventCustomerType.ADD_CUSTOMER, jsonUtils.toJson(customerData));
    }

    public void sendEditCustomerEvent(Customer customer) {
        JsonUtils jsonUtils = new JsonUtils();
        sendCustomerEvent(EventCustomerType.EDIT_CUSTOMER, jsonUtils.toJson(customer));
    }

    public void sendFindByCustomerIDEvent(String document) {
        sendCustomerEvent(EventCustomerType.FIND_CUSTOMER_BY_ID, document);
    }

    public void sendFindAllOrdersEvent(String customers) {
        sendCustomerEvent(EventCustomerType.FIND_ALL_CUSTOMERS, customers);
    }
}
