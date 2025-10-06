package co.edu.uptc.edamicrokafka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import co.edu.uptc.edamicrokafka.model.Customer;
import co.edu.uptc.edamicrokafka.utils.JsonUtils;

@Service
public class CustomerEventConsumer {
    @Autowired
    private CustomerService customerService;
        @KafkaListener(topics = "addcustomer_events", groupId = "customer_group")
    public void handleAddCustomerEvent(String customer) {
        JsonUtils jsonUtils = new JsonUtils();
        Customer receiveAddCustomer = jsonUtils.fromJson(customer, Customer.class);
        customerService.save(receiveAddCustomer);
    }
    @KafkaListener(topics = "editcustomer_events", groupId = "customer_group")
    public void handleEditCustomerEvent(String customer) {
        JsonUtils jsonUtils = new JsonUtils();
        Customer receiveEditCustomer = jsonUtils.fromJson(customer, Customer.class);
        customerService.save(receiveEditCustomer);
    }
        @KafkaListener(topics = "findcustomerbyid_events", groupId = "customer_group")
    public Customer handleFindCustomerByIDEvent(String customer) {
        Customer customerReceived = customerService.findById(customer);
        return customerReceived;
    }

    @KafkaListener(topics = "findallcustomers_events", groupId = "customer_group")
    public List<Customer> handleFindAllCustomers() {
        List<Customer> customersReceived = customerService.findAll();
        return customersReceived;
    }

}
