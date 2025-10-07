package co.edu.uptc.edamicrokafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.uptc.edamicrokafka.model.order.Order;
import co.edu.uptc.edamicrokafka.service.order.OrderEventProducer;
import co.edu.uptc.edamicrokafka.utils.JsonUtils;

@RestController
public class OrderController {
    @Autowired
    private OrderEventProducer orderEventProducer;
    private JsonUtils jsonUtils = new JsonUtils();
   
     @PostMapping("/addOrder")
    public String createOrder(@RequestBody String orderJson) {
        Order order = jsonUtils.fromJson(orderJson, Order.class);
        orderEventProducer.sendAddOrderEvent(order);
        return "Solicitiud de creación de orden enviada";
    }

     @PostMapping("/editOrder")
    public String updateOrder(@RequestBody String orderJson) {
        Order order = jsonUtils.fromJson(orderJson, Order.class);
        orderEventProducer.sendEditOrderEvent(order);
        return "Solicitiud de actualización de orden enviada";
    }

    @GetMapping("/order/{orderId}")
    public String findOrder(@PathVariable String orderId) {
        orderEventProducer.sendFindByOrderIDEvent(orderId);
        return "Solicitiud de una orden por id enviada";
    }

    @GetMapping("/orders")
    public String findAllOrders() {
        orderEventProducer.sendFindAllOrdersEvent();
        return "Solicitiud de todas las ordenes enviada";
    }
}
