package com.akhdani.perdin_app.controller;

import com.akhdani.perdin_app.dto.CityRequest;
import com.akhdani.perdin_app.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/city")
public class CityController {
    private final CityService cityService;

    @GetMapping
    public String cityPage(Model model) {
        model.addAttribute("cities", cityService.getAllCity());
        return "city-list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("city", new CityRequest());
        return "city-form";
    }

    @PostMapping("/save")
    public String saveCity(@ModelAttribute CityRequest request) {
        cityService.saveCity(request);
        return "redirect:/admin/city";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        model.addAttribute("city", cityService.getCityById(id));
        return "city-edit";
    }

    @PostMapping("/update/{id}")
    public String updateCity(@PathVariable Long id, @ModelAttribute CityRequest request) {
        cityService.updateCity(id, request);
        return "redirect:/admin/city";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return "redirect:/admin/city";
    }
}
