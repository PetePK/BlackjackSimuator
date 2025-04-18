export interface Player {
  id: string
  name: string
  money: number
  playerType: string
  currentBet: number
  cards: { suit: string; value: string }[]
  handValue: number
  active: boolean
  wins: number
  losses: number
}


export interface Card {
  suit: string        // "hearts" | "diamonds" | "clubs" | "spades"
  value: string       // "A" | "2" … "10" | "J" | "Q" | "K"
}
