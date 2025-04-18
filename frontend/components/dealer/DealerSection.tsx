"use client";

import { useState, useEffect } from "react";
import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { PlayingCard } from "@/components/players/PlayingCard";
import { RefreshCw, Eye } from "lucide-react";
import { Collapsible, CollapsibleContent, CollapsibleTrigger } from "@/components/ui/collapsible";
import { shuffleDeck, getDealerHistory } from "@/lib/api";
import type { Card as PlayingCardType } from "@/lib/types";

interface DealerSectionProps {
  dealer: {
    cards: PlayingCardType[];
    handValue: number;
  };
}

export function DealerSection({ dealer }: DealerSectionProps) {
  const [historyOpen, setHistoryOpen] = useState(false);
  const [dealerHistory, setDealerHistory] = useState<{ round: number; netProfit: number }[]>([]);

  const fetchHistory = async () => {
    const data = await getDealerHistory();
    setDealerHistory(data.history || []);
  };

  useEffect(() => {
    if (historyOpen) fetchHistory();
  }, [historyOpen]);

  const handleShuffle = async () => {
    await shuffleDeck();
    location.reload();
  };

  return (
    <Card className="bg-emerald-900 border-emerald-600 shadow-lg">
      <CardContent className="p-6">
        <div className="flex flex-col items-center space-y-4">
          <div className="flex items-center justify-between w-full">
            <h2 className="text-2xl font-bold text-white">Dealer</h2>
          </div>

          <div className="flex flex-wrap justify-center gap-2">
            {dealer.cards.map((card, index) => (
              <PlayingCard key={index} card={card} />
            ))}
          </div>

          <div className="text-xl font-semibold text-white">
            Hand Value: {dealer.handValue}
          </div>

          <div className="flex gap-2 w-full">
            <Button
              onClick={handleShuffle}
              variant="outline"
              className="bg-emerald-700 text-white hover:bg-emerald-600 border-emerald-500 flex-1"
            >
              <RefreshCw className="mr-2 h-4 w-4" />
              Shuffle / Reset Deck
            </Button>

            <Collapsible open={historyOpen} onOpenChange={setHistoryOpen} className="flex-1">
              <CollapsibleTrigger asChild>
                <Button
                  variant="outline"
                  className="bg-emerald-700 text-white hover:bg-emerald-600 border-emerald-500 w-full"
                >
                  <Eye className="mr-2 h-4 w-4" />
                  {historyOpen ? "Hide History" : "View History"}
                </Button>
              </CollapsibleTrigger>
              <CollapsibleContent className="w-full">
                <div className="bg-emerald-950 rounded-md p-3 mt-2 text-white">
                  <h3 className="font-semibold mb-2 text-center">Dealer Profit/Loss History</h3>
                  <div className="space-y-1">
                    {dealerHistory.map((round) => (
                      <div key={round.round} className="flex justify-between">
                        <span>Round #{round.round}:</span>
                        <span className={round.netProfit >= 0 ? "text-green-400" : "text-red-400"}>
                          {round.netProfit >= 0 ? "+" : ""}
                          {round.netProfit}
                        </span>
                      </div>
                    ))}
                  </div>
                </div>
              </CollapsibleContent>
            </Collapsible>
          </div>
        </div>
      </CardContent>
    </Card>
  );
}
