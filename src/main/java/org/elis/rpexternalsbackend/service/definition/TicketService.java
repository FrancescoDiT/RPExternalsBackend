package org.elis.rpexternalsbackend.service.definition;

import org.elis.rpexternalsbackend.dto.request.CreateTicketRequestDTO;
import org.elis.rpexternalsbackend.dto.response.CreateTicketResponseDTO;
import org.elis.rpexternalsbackend.model.Ticket;

import java.util.List;

public interface TicketService {
    Ticket createTicket(CreateTicketRequestDTO createTicketRequestDTO);
    List<Ticket> createTickets(List<CreateTicketRequestDTO> createTicketRequestDTOList);
    List<CreateTicketResponseDTO> readAllTickets();
    List<Ticket> readAllTicketsWithUser();
}
