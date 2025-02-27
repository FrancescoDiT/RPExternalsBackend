package org.elis.rpexternalsbackend.controller;

import jakarta.validation.Valid;
import org.elis.rpexternalsbackend.dto.request.CreateTicketRequestDTO;
import org.elis.rpexternalsbackend.dto.request.CreateUserRequestDTO;
import org.elis.rpexternalsbackend.dto.response.CreateTicketResponseDTO;
import org.elis.rpexternalsbackend.model.User;
import org.elis.rpexternalsbackend.service.definition.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.elis.rpexternalsbackend.model.Ticket;

import java.util.List;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/all/tickets/create")
    public ResponseEntity<Void> createTicket(@RequestBody @Valid CreateTicketRequestDTO createTicketRequestDTO){
        Ticket ticket = ticketService.createTicket(createTicketRequestDTO);
        if(ticket != null) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/all/tickets/createList")
    public ResponseEntity<Void> createTickets(@RequestBody @Valid List<CreateTicketRequestDTO> createTicketRequestDTO){
        List<Ticket> tickets = ticketService.createTickets(createTicketRequestDTO);
        if(!tickets.isEmpty()) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/all/tickets/readAll")
    public ResponseEntity<List<CreateTicketResponseDTO>> readAllTickets() {
        List<CreateTicketResponseDTO> createTicketResponseDTOS = ticketService.readAllTickets();
        return ResponseEntity.ok(createTicketResponseDTOS);
    }

    @GetMapping("/all/tickets/readAll/with/user")
    public ResponseEntity<List<Ticket>> readAllTicketsWithUser() {
        List<Ticket> tickets = ticketService.readAllTicketsWithUser();
        return ResponseEntity.ok(tickets);
    }
}
