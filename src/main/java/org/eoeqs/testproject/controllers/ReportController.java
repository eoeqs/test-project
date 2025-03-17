package org.eoeqs.testproject.controllers;


import org.eoeqs.testproject.services.ReportService;
import org.eoeqs.testproject.utils.DailyReport;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/daily/{userId}/{date}")
    public DailyReport getDailyReport(
            @PathVariable Long userId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return reportService.getDailyReport(userId, date);
    }

    @GetMapping("/check-norm/{userId}/{date}")
    public boolean checkDailyNorm(
            @PathVariable Long userId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return reportService.checkDailyNorm(userId, date);
    }

    @GetMapping("/history/{userId}")
    public List<DailyReport> getHistory(@PathVariable Long userId) {
        return reportService.getHistory(userId);
    }
}