// frontend/lib/types.ts

/** A single hand’s outcome for history viewing */
export interface HandHistoryEntry {
  /** "WIN" for a winning hand, "LOSS" for a losing hand */
  result: "WIN" | "LOSS"
  /** Money change for that hand (positive for wins, negative for losses) */
  change: number
}

export interface Card {
  suit: string        // "hearts" | "diamonds" | "clubs" | "spades"
  value: string       // "A" | "2" … "10" | "J" | "Q" | "K"
}

export interface Player {
  id: string
  name: string
  money: number
  strategy: string            // "Conservative" | "Aggressive" | "Random"
  cardCounting: boolean
  bettingStrategy: boolean
  currentBet: number
  cards: Card[]
  handValue: number
  active: boolean
  wins: number
  losses: number
  /** Full history of each hand this player has played */
  handsHistory?: HandHistoryEntry[]
}

export interface DealerInfo {
  cards: Card[]
  handValue: number
}
