package co.istad.roth.ecommerce.features.order.dto;

import co.istad.roth.ecommerce.features.order.OrderLine;
import jakarta.validation.constraints.*;
import org.apache.logging.log4j.message.Message;

import java.util.List;

public record CreateOrderRequest(
        @NotBlank(message = "Address is required")
        String address,
        @NotNull(message = "Discount is required")
        @Min(0)
        @Max(100)
        Float discount,
        @Size(max = 255)
        String remark,

        @NotEmpty(message = "Order line is required")
        List<OrderLineDto> orderLines
) {
}
