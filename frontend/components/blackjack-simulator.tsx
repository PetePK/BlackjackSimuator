"use client"

import { useState } from "react"
import { DealerSection } from "./dealer-section"
import { GlobalControls } from "./global-controls"
import { PlayerGrid } from "./player-grid"
import { AddPlayerButton } from "./add-player-button"
import type { Player } from "@/lib/types"

export function BlackjackSimulator() {
  const [players, setPlayers] = useState<Player[]>([
    {
      id: "1",
      name: "Player 1",
      money: 1000,
      strategy: "Conservative",
      cardCounting: false,
      bettingStrategy: true,
      currentBet: 50,
      cards: [],
      handValue: 0,
      active: true,
      wins: 0,
      losses: 0,
    },
    {
      id: "2",
      name: "Player 2",
      money: 1500,
      strategy: "Aggressive",
      cardCounting: true,
      bettingStrategy: true,
      currentBet: 100,
      cards: [],
      handValue: 0,
      active: true,
      wins: 5,
      losses: 3,
    },
  ])

  const [simulationSpeed, setSimulationSpeed] = useState(1)

  const addPlayer = () => {
    if (players.length >= 8) return

    const newPlayer: Player = {
      id: Date.now().toString(),
      name: `Player ${players.length + 1}`,
      money: 1000,
      strategy: "Conservative",
      cardCounting: false,
      bettingStrategy: false,
      currentBet: 50,
      cards: [],
      handValue: 0,
      active: true,
      wins: 0,
      losses: 0,
    }

    setPlayers([...players, newPlayer])
  }

  const removePlayer = (id: string) => {
    setPlayers(players.filter((player) => player.id !== id))
  }

  const togglePlayerActive = (id: string) => {
    setPlayers(players.map((player) => (player.id === id ? { ...player, active: !player.active } : player)))
  }

  const updatePlayer = (updatedPlayer: Player) => {
    setPlayers(players.map((player) => (player.id === updatedPlayer.id ? updatedPlayer : player)))
  }

  const resetAllPlayers = () => {
    setPlayers(
      players.map((player) => ({
        ...player,
        money: 1000,
        currentBet: 50,
        cards: [],
        handValue: 0,
        wins: 0,
        losses: 0,
      })),
    )
  }

  return (
    <div className="min-h-screen bg-emerald-800 p-4 md:p-6 lg:p-8">
      <div className="max-w-7xl mx-auto space-y-6">
        <h1 className="text-3xl font-bold text-white text-center mb-6">Blackjack Simulator</h1>

        <DealerSection />

        <GlobalControls
          simulationSpeed={simulationSpeed}
          setSimulationSpeed={setSimulationSpeed}
          resetAllPlayers={resetAllPlayers}
        />

        <PlayerGrid
          players={players}
          removePlayer={removePlayer}
          togglePlayerActive={togglePlayerActive}
          updatePlayer={updatePlayer}
        />

        <AddPlayerButton addPlayer={addPlayer} disabled={players.length >= 8} />
      </div>
    </div>
  )
}
