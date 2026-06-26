package co.istad.roth.ecommerce.features.order.dto;

import jakarta.validation.constraints.NotNull;

public record PaymentStatusRequest (
        @NotNull(message = "Status is required.")
        Boolean status

){
}
