export interface Player {
  id: string
  name: string
  money: number
  strategy: string
  cardCounting: boolean
  bettingStrategy: boolean
  currentBet: number
  cards: any[] // In a real app, you'd define a Card type
  handValue: number
  active: boolean
  wins: number
  losses: number
}
