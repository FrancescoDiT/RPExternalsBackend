package org.elis.rpexternalsbackend.service.implementation;

import org.elis.rpexternalsbackend.dto.request.CreateTicketRequestDTO;
import org.elis.rpexternalsbackend.dto.response.CreateTicketResponseDTO;
import org.elis.rpexternalsbackend.exception.DatabaseInconsistencyException;
import org.elis.rpexternalsbackend.model.Ticket;
import org.elis.rpexternalsbackend.model.User;
import org.elis.rpexternalsbackend.repository.TicketRepository;
import org.elis.rpexternalsbackend.repository.UserRepository;
import org.elis.rpexternalsbackend.service.definition.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;

    private static Ticket checkTicket(Map<String, String> errors, CreateTicketRequestDTO createTicketRequestDTO, User user){

        if(user == null){
            errors.put("UserNotFound", "User not found");
        }

        if(!errors.isEmpty()){
            throw new DatabaseInconsistencyException(errors);
        }

        return Ticket.builder()
                .title(createTicketRequestDTO.getTitle())
                .body(createTicketRequestDTO.getBody())
                .ticketType(createTicketRequestDTO.getTicketType())
                .user(user)
                .build();
    }

    @Override
    public Ticket createTicket(CreateTicketRequestDTO createTicketRequestDTO) {
        Map<String, String> errors = new TreeMap<>();
        User user = userRepository.findById(createTicketRequestDTO.getUserId()).orElse(null);
        Ticket ticket = checkTicket(errors, createTicketRequestDTO, user);
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> createTickets(List<CreateTicketRequestDTO> createTicketRequestDTOList) {
        Map<String, String> errors = new TreeMap<>();
        List<Ticket> tickets = new java.util.ArrayList<>();
        createTicketRequestDTOList.forEach(createTicketRequestDTO -> {
             User user = userRepository.findById(createTicketRequestDTO.getUserId()).orElse(null);
             Ticket ticket = checkTicket(errors, createTicketRequestDTO, user);
             tickets.add(ticket);
        });
        return ticketRepository.saveAll(tickets);
    }

    @Override
    public List<CreateTicketResponseDTO> readAllTickets() {
        return ticketRepository.findAll().stream().map(ticket -> CreateTicketResponseDTO.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .body(ticket.getBody())
                .ticketType(ticket.getTicketType())
                .user(ticket.getUser())
                .build()).toList();
    }

    @Override
    public List<Ticket> readAllTicketsWithUser() {
        return ticketRepository.findAllTicketsWithUser();
    }
}
