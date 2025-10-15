
package com.example.ecommerce.service;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Order createOrder(String username, List<OrderItem> items) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        List<OrderItem> persistedItems = new ArrayList<>();
        double total = 0.0;
        for (OrderItem it : items) {
            Product p = productRepository.findById(it.getProduct().getId()).orElseThrow(() -> new RuntimeException("Produto não encontrado: " + it.getProduct().getId()));
            if (p.getStock() < it.getQuantity()) throw new RuntimeException("Estoque insuficiente para produto " + p.getId());
            p.setStock(p.getStock() - it.getQuantity());
            productRepository.save(p);

            OrderItem ni = new OrderItem();
            ni.setProduct(p);
            ni.setQuantity(it.getQuantity());
            ni.setPrice(p.getPrice());
            persistedItems.add(ni);
            total += p.getPrice() * it.getQuantity();
        }
        Order order = new Order();
        order.setUser(user);
        order.setItems(persistedItems);
        order.setTotal(total);
        return orderRepository.save(order);
    }
}
