package co.istad.roth.ecommerce.features.order.dto;

import jakarta.validation.constraints.NotNull;

public record SoftDeleteOrderRequest(
        @NotNull(message = "isDeleted is required.")
        Boolean isDeleted
) {


}