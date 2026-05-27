package com.akhdani.perdin_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/pegawai/dashboard")
    public String pegawaiDashboard() {
        return "pegawai-dashboard";
    }

    @GetMapping("/sdm/dashboard")
    public String sdmDashboard() {
        return "sdm-dashboard";
    }
}
