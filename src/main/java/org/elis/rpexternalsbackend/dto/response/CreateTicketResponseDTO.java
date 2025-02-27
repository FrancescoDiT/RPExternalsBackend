package org.elis.rpexternalsbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.elis.rpexternalsbackend.model.User;
import org.elis.rpexternalsbackend.model.value.TicketType;

@Builder
public class CreateTicketResponseDTO {
    private Long id;
    private String title;
    private String body;
    private TicketType ticketType;
    private User user;
}
