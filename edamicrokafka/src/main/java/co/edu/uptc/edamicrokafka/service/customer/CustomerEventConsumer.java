package co.edu.uptc.edamicrokafka.service.customer;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import co.edu.uptc.edamicrokafka.model.customer.Customer;
import co.edu.uptc.edamicrokafka.model.customer.CustomerEvent;
import co.edu.uptc.edamicrokafka.model.login.Login;
import co.edu.uptc.edamicrokafka.service.login.LoginService;
import co.edu.uptc.edamicrokafka.utils.JsonUtils;

@Service
public class CustomerEventConsumer {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private LoginService loginService;
    JsonUtils jsonUtils = new JsonUtils();

    @KafkaListener(topics = "customer_events", groupId = "customer_group")
    public void handleCustomerEvents(String message) {
        CustomerEvent customerEvent = jsonUtils.fromJson(message, CustomerEvent.class);

        switch (customerEvent.getEventType()) {
            case ADD_CUSTOMER:
                    addCustomer(customerEvent);
                break;
            case EDIT_CUSTOMER:
                    editCustomer(customerEvent);
                break;
            case FIND_CUSTOMER_BY_ID:
                    findCustomerByID(customerEvent);
                break;
            case FIND_ALL_CUSTOMERS:
                    findAllCustomers();
                break;
            default:
                break;
        }
    }

    public void addCustomer(CustomerEvent customerEvent){
        Map<String, Object> data = jsonUtils.fromJson(customerEvent.getData(), Map.class);
        Customer receiveAddCustomer = jsonUtils.fromJson(jsonUtils.toJson(data.get("customer")), Customer.class);
        customerService.save(receiveAddCustomer);

        String password = (String) data.get("password");
        Login login  = new Login();
        login.setCustomerId(receiveAddCustomer.getDocument());
        login.setPassword(password);
        loginService.save(login);
    }

    public void editCustomer(CustomerEvent customerEvent) {
        Customer receiveEditCustomer = jsonUtils.fromJson(customerEvent.getData(), Customer.class);
        customerService.save(receiveEditCustomer);
    }

    public Customer findCustomerByID(CustomerEvent customerEvent) {
        Customer customerReceived = customerService.findById(customerEvent.getData());
        return customerReceived;
    }

    public List<Customer> findAllCustomers() {
        List<Customer> customersReceived = customerService.findAll();
        return customersReceived;
    }

}
