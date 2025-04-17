"use client"

import { Button } from "@/components/ui/button"
import { Card, CardContent } from "@/components/ui/card"
import { PlayingCard } from "./playing-card"
import { RefreshCw } from "lucide-react"

export function DealerSection() {
  // In a real app, these would come from your game state
  const dealerCards = [
    { suit: "hearts", value: "A" },
    { suit: "spades", value: "10" },
  ]
  const dealerValue = 21

  return (
    <Card className="bg-emerald-900 border-emerald-600 shadow-lg">
      <CardContent className="p-6">
        <div className="flex flex-col items-center space-y-4">
          <h2 className="text-2xl font-bold text-white">Dealer</h2>

          <div className="flex flex-wrap justify-center gap-2">
            {dealerCards.map((card, index) => (
              <PlayingCard key={index} card={card} />
            ))}
          </div>

          <div className="text-xl font-semibold text-white">Hand Value: {dealerValue}</div>

          <Button variant="outline" className="bg-emerald-700 text-white hover:bg-emerald-600 border-emerald-500">
            <RefreshCw className="mr-2 h-4 w-4" />
            Shuffle / Reset Deck
          </Button>
        </div>
      </CardContent>
    </Card>
  )
}
