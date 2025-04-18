
package com.blackjack.service;

import com.blackjack.model.Dealer;
import org.springframework.stereotype.Service;

@Service
public class DealerService {
    private final Dealer dealer;

    public DealerService() {
        this.dealer = new Dealer();
    }

    public void resetHand() {
        dealer.resetHand();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
