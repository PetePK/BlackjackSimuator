
package com.blackjack.controller;

import com.blackjack.model.Dealer;
import com.blackjack.service.DealerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dealer")
public class DealerController {
    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @GetMapping
    public ResponseEntity<Dealer> getDealer() {
        return ResponseEntity.ok(dealerService.getDealer());
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> resetHand() {
        dealerService.resetHand();
        return ResponseEntity.ok().build();
    }
}
