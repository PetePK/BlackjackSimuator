export interface Card {
  suit: string
  value: string
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
}

export interface DealerInfo {
  cards: Card[]
  handValue: number
}
