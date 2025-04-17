"use client"

import { useState, useEffect } from "react"
import { Button } from "@/components/ui/button"
import { Card, CardContent } from "@/components/ui/card"
import { PlayingCard } from "./playing-card"
import { RefreshCw } from "lucide-react"
import type { DealerInfo } from "@/lib/types"

const API_BASE = "http://localhost:8080/api/game"

interface DealerSectionProps {
  /** Toggled to force re-fetch */
  refreshTrigger?: boolean
}

export function DealerSection({ refreshTrigger }: DealerSectionProps) {
  const [dealerCards, setDealerCards] = useState<DealerInfo["cards"]>([])
  const [dealerValue, setDealerValue] = useState<number>(0)

  const fetchDealer = async () => {
    const res = await fetch(`${API_BASE}/dealer`)
    if (!res.ok) return
    const data: DealerInfo = await res.json()
    setDealerCards(data.cards)
    setDealerValue(data.handValue)
  }

  const handleShuffle = async () => {
    await fetch(`${API_BASE}/shuffle`, { method: "POST" })
    fetchDealer()
  }

  // Run on mount and whenever refreshTrigger flips
  useEffect(() => {
    fetchDealer()
  }, [refreshTrigger])

  return (
    <Card className="bg-emerald-900 border-emerald-600 shadow-lg">
      <CardContent className="p-6">
        <div className="flex flex-col items-center space-y-4">
          <h2 className="text-2xl font-bold text-white">Dealer</h2>

          <div className="flex flex-wrap justify-center gap-2">
            {dealerCards.map((card, idx) => (
              <PlayingCard key={idx} card={card} />
            ))}
          </div>

          <div className="text-xl font-semibold text-white">
            Hand Value: {dealerValue}
          </div>

          <Button
            variant="outline"
            className="bg-emerald-700 text-white hover:bg-emerald-600 border-emerald-500"
            onClick={handleShuffle}
          >
            <RefreshCw className="mr-2 h-4 w-4" />
            Shuffle / Reset Deck
          </Button>
        </div>
      </CardContent>
    </Card>
  )
}
