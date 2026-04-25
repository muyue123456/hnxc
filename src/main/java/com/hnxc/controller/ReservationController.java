package com.hnxc.controller;

import com.hnxc.model.Reservation;
import com.hnxc.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/reserve")
    public String reserveForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reserve";
    }

    @PostMapping("/reserve")
    public String createReservation(@ModelAttribute Reservation reservation, Model model) {
        reservationService.createReservation(reservation);
        model.addAttribute("success", true);
        model.addAttribute("reservation", new Reservation());
        return "reserve";
    }

    @GetMapping("/history")
    public String reservationHistory(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "history";
    }

    @GetMapping("/flow")
    public String flow() {
        return "flow";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
