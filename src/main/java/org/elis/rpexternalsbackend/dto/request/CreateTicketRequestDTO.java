package org.elis.rpexternalsbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.elis.rpexternalsbackend.model.value.TicketType;

@Getter
@AllArgsConstructor
public class CreateTicketRequestDTO {
    @NotBlank(message = "Title cannot be null")
    private String title;
    @NotBlank(message = "Body cannot be null")
    private String body;
    @NotNull(message = "Ticket type cannot be null")
    private TicketType ticketType;
    @NotNull(message = "User id cannot be null")
    private Long userId;
}
