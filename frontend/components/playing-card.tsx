"use client"

import type { Card } from "@/lib/types"

interface PlayingCardProps {
  card: Card
  small?: boolean
}

export function PlayingCard({ card, small = false }: PlayingCardProps) {
  const { suit, value } = card
  const getSuitSymbol = (s: string) =>
    s === "hearts"
      ? "♥"
      : s === "diamonds"
      ? "♦"
      : s === "clubs"
      ? "♣"
      : s === "spades"
      ? "♠"
      : ""
  const getSuitColor = (s: string) =>
    ["hearts", "diamonds"].includes(s) ? "text-red-500" : "text-black"

  const suitSymbol = getSuitSymbol(suit)
  const suitColor = getSuitColor(suit)

  return (
    <div
      className={`
        bg-white rounded-md flex flex-col items-center justify-center
        ${small ? "w-10 h-14" : "w-14 h-20"} 
        shadow-md border border-gray-300
      `}
    >
      <div className={`font-bold ${suitColor} ${small ? "text-sm" : "text-lg"}`}>
        {value}
      </div>
      <div className={`${suitColor} ${small ? "text-lg" : "text-2xl"}`}>
        {suitSymbol}
      </div>
    </div>
  )
}
