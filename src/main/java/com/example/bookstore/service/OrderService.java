package com.example.bookstore.service;


import com.example.bookstore.model.Order;
import com.example.bookstore.model.dto.OrderCreateDto;
import com.example.bookstore.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.customGetAllOrders();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Boolean deleteOrderById(Long id) {
        orderRepository.deleteById(id);
        return getOrderById(id).isEmpty();
    }

    public Boolean createOrder(@Valid OrderCreateDto orderFromDto) {
        Order order = new Order();
        order.setStatus(orderFromDto.getStatus());
        Order createdOrder = orderRepository.save(order);
        return getOrderById(createdOrder.getId()).isPresent();
    }

    public Boolean updateOrder(Order order) {
        Optional<Order> orderFromDBOptional = orderRepository.findById(order.getId());
        if (orderFromDBOptional.isPresent()) {
            Order orderFromDB = orderFromDBOptional.get();
            if (order.getDateSale() != null) {
                orderFromDB.setDateSale(order.getDateSale());
            }

            if (order.getStatus() != null) {
                orderFromDB.setStatus(order.getStatus());
            }

            Order updatedOrder = orderRepository.saveAndFlush(orderFromDB);
            return orderFromDB.equals(updatedOrder);
        }
        return false;
    }

}

