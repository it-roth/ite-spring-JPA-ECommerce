package co.istad.roth.ecommerce.features.order;

import co.istad.roth.ecommerce.features.order.dto.*;
import co.istad.roth.ecommerce.features.product.Product;
import co.istad.roth.ecommerce.features.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createNew(CreateOrderRequest createOrderRequest) {

        final Order order = orderMapper.mapCreateOrderRequestToOrder(createOrderRequest);

        List<OrderLine> orderLines = new ArrayList<>();

        //Validate order lines (LIST)
        boolean isValidOrder = createOrderRequest.orderLines()
                .stream()
                .allMatch(orderLineDto -> {

                    Optional<Product> productOptional = productRepository
                            .findByCode(orderLineDto.code());

                    if (productOptional.isPresent()) {
                        OrderLine orderLine = new OrderLine();
                        orderLine.setProduct(productOptional.get());
                        orderLine.setQty(orderLineDto.qty());
                        orderLine.setUnitPrice(orderLineDto.unitPrice());
                        orderLine.setOrder(order);
                        orderLines.add(orderLine);

                        return true;
                    }

                    return false;

                });
        if (!isValidOrder) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid order line");
        }

        order.setOrderLines(orderLines);

        //security related
        order.setCustomerId("ISTAD");

        order.setIsDeleted(false);
        order.setOrderedAt(LocalDateTime.now());
        order.setStatus(false);

        Order savedOrder = orderRepository.save(order);

        return orderMapper.mapOrderToOrderResponse(order);
    }
    @Override
    public Page<OrderResponse> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest
                .of(pageNumber,pageSize, Sort.by(Sort.Direction.DESC,"orderedAt"));
        return orderRepository.findAll(pageable)
                .map(orderMapper::mapOrderToOrderResponse);
    }

    @Override
    public OrderResponse findByUuid(UUID uuid) {
        return orderRepository.findById(uuid)
                .map(orderMapper::mapOrderToOrderResponse)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Order has not been found."));
    }

    @Override
    public void softDelete(UUID uuid, SoftDeleteOrderRequest softDeleteOrderRequest) {
        Order order = orderRepository.findById(uuid)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Order has not been found."));

        order.setIsDeleted(softDeleteOrderRequest.isDeleted());
        orderRepository.save(order);
    }

    @Override
    public void hardDelete(UUID uuid) {
        orderRepository.delete(orderRepository
                .findById(uuid).orElseThrow(()->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order has not been found.")));
    }

    @Override
    public void changePaymentStatus(UUID uuid, PaymentStatusRequest paymentStatusRequest) {
        Order order = orderRepository
                .findById(uuid).orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Order has not been found."));

        order.setStatus(paymentStatusRequest.status());
        orderRepository.save(order);
    }

}
