package org.elis.rpexternalsbackend.repository;

import org.elis.rpexternalsbackend.model.Menu;
import org.elis.rpexternalsbackend.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM ticket t JOIN FETCH t.user")
    List<Ticket> findAllTicketsWithUser();
}
