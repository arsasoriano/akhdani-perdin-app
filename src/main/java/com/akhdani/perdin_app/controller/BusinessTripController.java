package com.akhdani.perdin_app.controller;

import com.akhdani.perdin_app.dto.BusinessTripRequest;
import com.akhdani.perdin_app.service.BusinessTripService;
import com.akhdani.perdin_app.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pegawai/trip")
public class BusinessTripController {
    private final BusinessTripService businessTripService;
    private final CityService cityService;

    @GetMapping
    public String tripPage(Authentication auth, Model model) {
        model.addAttribute("trips", businessTripService.getMyTrip(auth.getName()));
        return "trip-list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("trip", new BusinessTripRequest());
        model.addAttribute("cities", cityService.getAllCity());
        return "trip-form";
    }

    @PostMapping("/save")
    public String saveTrip(@ModelAttribute BusinessTripRequest request, Authentication auth) {
        businessTripService.submitTrip(request, auth.getName());
        return "redirect:/pegawai/trip";
    }
}
