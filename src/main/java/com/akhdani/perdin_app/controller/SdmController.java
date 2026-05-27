package com.akhdani.perdin_app.controller;

import com.akhdani.perdin_app.service.BusinessTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sdm/trip")
public class SdmController {
    private final BusinessTripService businessTripService;

    @GetMapping
    public String tripApproval(Model model) {
        model.addAttribute("trips", businessTripService.getAllTrip());
        return "sdm-trip-list";
    }

    @GetMapping("/approve/{id}")
    public String approveTrip(@PathVariable Long id) {
        businessTripService.approveTrip(id);
        return "redirect:/sdm/trip";
    }

    @GetMapping("/reject/{id}")
    public String rejectTrip(@PathVariable Long id) {
        businessTripService.rejectTrip(id);
        return "redirect:/sdm/trip";
    }
}
