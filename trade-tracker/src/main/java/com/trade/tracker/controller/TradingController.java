package com.trade.tracker.controller;

import com.trade.tracker.service.TradingSystemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trading")
public class TradingController {

    private final TradingSystemService tradingSystemService;

    public TradingController(TradingSystemService tradingSystemService) {
        this.tradingSystemService = tradingSystemService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startTrading() {
        boolean started = tradingSystemService.startTrading();
        if (started) {
            return ResponseEntity.ok("Trading started successfully.");
        }
        return ResponseEntity.badRequest().body("Trading is already running.");
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopTrading() {
        boolean stopped = tradingSystemService.stopTrading();
        if (stopped) {
            return ResponseEntity.ok("Trading stopped successfully.");
        }
        return ResponseEntity.badRequest().body("Trading is not running.");
    }
}
