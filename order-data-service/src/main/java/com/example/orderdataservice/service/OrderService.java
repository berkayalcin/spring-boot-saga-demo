package com.example.orderdataservice.service;

import com.example.orderdataservice.entity.Order;
import com.example.orderdataservice.entity.OrderItem;
import com.example.orderdataservice.model.OrderDTO;
import com.example.orderdataservice.model.OrderItemDTO;
import com.example.orderdataservice.repository.OrderItemRepository;
import com.example.orderdataservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderDTO getById(final String id) {
        final var order = orderRepository.getById(id);
        final var orderItems = orderItemRepository.findByOrderId(id);
        return buildOrderDTO(order, orderItems);
    }

    public List<OrderDTO> getAll() {
        final var orders = orderRepository.findAll();
        return orders.stream().map(order -> OrderDTO.builder()
                .id(order.getId())
                .address(order.getAddress())
                .buyer(order.getBuyer())
                .build()).collect(Collectors.toList());

    }

    public OrderDTO create(final OrderDTO orderDTO) {
        final var order = Order.builder()
                .address(orderDTO.getAddress())
                .buyer(orderDTO.getBuyer())
                .build();
        orderRepository.save(order);

        final var orderItems = orderDTO.getOrderItems().stream()
                .map(orderItemDTO -> OrderItem.builder()
                        .orderId(order.getId())
                        .productName(orderItemDTO.getProductName())
                        .quantity(orderItemDTO.getQuantity())
                        .unitPrice(orderItemDTO.getUnitPrice())
                        .build())
                .collect(Collectors.toList());
        orderItemRepository.saveAll(orderItems);

        return buildOrderDTO(order, orderItems);
    }

    public OrderDTO update(final OrderDTO orderDTO, final String id) {
        final var order = orderRepository.getById(id);
        updateOrder(orderDTO, order);
        updateOrderItems(orderDTO, order);
        return orderDTO;
    }

    public void delete(final String id) {
        orderItemRepository.deleteAllByOrderId(id);
        orderRepository.deleteById(id);
    }

    private void updateOrder(final OrderDTO orderDTO, final Order order) {
        order.setAddress(orderDTO.getAddress());
        order.setBuyer(orderDTO.getBuyer());
        orderRepository.save(order);
    }

    private void updateOrderItems(final OrderDTO orderDTO, final Order order) {
        orderItemRepository.deleteAllByOrderId(order.getId());
        final var orderItems = orderDTO.getOrderItems().stream()
                .map(orderItemDTO -> OrderItem.builder()
                        .orderId(order.getId())
                        .productName(orderItemDTO.getProductName())
                        .quantity(orderItemDTO.getQuantity())
                        .unitPrice(orderItemDTO.getUnitPrice())
                        .build())
                .collect(Collectors.toList());
        orderItemRepository.saveAll(orderItems);
    }

    private OrderDTO buildOrderDTO(final Order order, final List<OrderItem> orderItems) {
        final var orderItemDTOs = orderItems.stream().map(orderItem -> OrderItemDTO.builder()
                .id(orderItem.getId())
                .orderId(orderItem.getOrderId())
                .productName(orderItem.getProductName())
                .quantity(orderItem.getQuantity())
                .unitPrice(orderItem.getUnitPrice())
                .build()).collect(Collectors.toSet());


        return OrderDTO.builder()
                .id(order.getId())
                .address(order.getAddress())
                .buyer(order.getBuyer())
                .orderItems(orderItemDTOs)
                .build();
    }
}