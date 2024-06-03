package com.example.bookstore.controller;

import com.example.bookstore.exception.CustomValidException;
import com.example.bookstore.model.Order;
import com.example.bookstore.model.dto.OrderCreateDto;
import com.example.bookstore.service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") @Parameter Long id) {
        log.info("IN getUserById ");
        Optional<Order> order = orderService.getOrderById(id);
        if (order.isPresent()) {
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<HttpStatus> createOrder(@RequestBody @Valid OrderCreateDto order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomValidException(bindingResult.getAllErrors().toString());
        }
        return new ResponseEntity<>(orderService.createOrder(order) ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.updateOrder(order) ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderService.deleteOrderById(id) ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }
}
