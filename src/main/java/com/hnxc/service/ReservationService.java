package com.hnxc.service;

import com.hnxc.model.Reservation;
import com.hnxc.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation) {
        reservation.setStatus("待处理");
        reservation.setCreatedAt(LocalDateTime.now());
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation reservation) {
        Optional<Reservation> existingReservation = reservationRepository.findById(id);
        if (existingReservation.isPresent()) {
            Reservation updatedReservation = existingReservation.get();
            updatedReservation.setName(reservation.getName());
            updatedReservation.setPhone(reservation.getPhone());
            updatedReservation.setCar(reservation.getCar());
            updatedReservation.setLocation(reservation.getLocation());
            updatedReservation.setTime(reservation.getTime());
            updatedReservation.setStatus(reservation.getStatus());
            return reservationRepository.save(updatedReservation);
        }
        return null;
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
