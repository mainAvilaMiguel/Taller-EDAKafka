package co.edu.uptc.edamicrokafka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.edamicrokafka.model.Customer;
import co.edu.uptc.edamicrokafka.service.CustomerEventProducer;
import co.edu.uptc.edamicrokafka.utils.JsonUtils;


@RestController
public class CustomerController {
    @Autowired
    private CustomerEventProducer customerEventProducer;
    private static JsonUtils jsonUtils= new JsonUtils();

    @PostMapping("/addcustomer")
    public String sendMessageAddCustomer(@RequestBody String customer) {
        Customer customerObj = new Customer();
        customerObj = jsonUtils.fromJson(customer, Customer.class);
        customerEventProducer.sendAddCustomerEvent(customerObj);        
        return customerEventProducer.toString();
    }
    @PostMapping("/editcustomer")
    public String sendMessageEditCustomer(@RequestBody String customer) {
        Customer customerObj = new Customer();
        customerObj = jsonUtils.fromJson(customer, Customer.class);
        customerEventProducer.sendEditCustomerEvent(customerObj);        
        return customerEventProducer.toString();
    }
    @GetMapping("/findcustomerbyid/{document}")
    public String sendMessageFindCustomer(@PathVariable String document) {
        customerEventProducer.sendFindByCustomerIDEvent(document);
        return customerEventProducer.toString();
    }
    @GetMapping("/findallcustomers")
    public List<Customer> sendMessageFindAllCustomers() {
            customerEventProducer.sendFindAllOrdersEvent("findall");
            return null;
    }
}
