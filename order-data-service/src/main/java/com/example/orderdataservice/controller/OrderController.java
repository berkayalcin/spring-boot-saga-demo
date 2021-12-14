package com.example.orderdataservice.controller;

import com.example.orderdataservice.model.OrderDTO;
import com.example.orderdataservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO create(@RequestBody final OrderDTO orderDTO) {
        return orderService.create(orderDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public OrderDTO getById(@PathVariable final String id) {
        return orderService.getById(id);
    }

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<OrderDTO> getAll() {
        return orderService.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public OrderDTO update(@PathVariable final String id, @RequestBody final OrderDTO orderDTO) {
        return orderService.update(orderDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable final String id) {
        orderService.delete(id);
    }
}