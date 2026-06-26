package co.istad.roth.ecommerce.features.order;


import co.istad.roth.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.roth.ecommerce.features.order.dto.OrderResponse;
import co.istad.roth.ecommerce.features.order.dto.PaymentStatusRequest;
import co.istad.roth.ecommerce.features.order.dto.SoftDeleteOrderRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface OrderService {
    OrderResponse createNew(CreateOrderRequest createOrderRequest);
    Page<OrderResponse> findAll(Integer pageNumber, Integer pageSize);

    OrderResponse findByUuid(UUID uuid);

    void softDelete(UUID uuid, SoftDeleteOrderRequest softDeleteOrderRequest);

    void hardDelete(UUID uuid);

    void changePaymentStatus(UUID uuid, PaymentStatusRequest paymentStatusRequest);
}
