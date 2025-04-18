"use client"

import { useEffect, useState } from "react"
import { DealerSection } from "./dealer/DealerSection"
import { GlobalControls } from "./global/GlobalControls"
import { PlayerGrid } from "./players/PlayerGrid"
import { AddPlayerButton } from "./players/AddPlayerButton"
import type { Player, Card as PlayingCardType } from "@/lib/types"
import {
  getGameState,
  addPlayer,
  updatePlayer,
  deletePlayer,
  togglePlayer,
  resetPlayers,
} from "@/lib/api"

export function BlackjackSimulator() {
  const [players, setPlayers] = useState<Player[]>([])
  const [simulationSpeed, setSimulationSpeed] = useState(1)
  const [currentCardCount, setCurrentCardCount] = useState(0)
  const [dealerCards, setDealerCards] = useState<PlayingCardType[]>([])
  const [dealerHandValue, setDealerHandValue] = useState(0)

  const fetchState = async () => {
    const data = await getGameState()
    console.log("🎯 Full GameState from backend:", data)

    setPlayers(data.players || [])
    setCurrentCardCount(data.deck?.cardCount || 0)
    setDealerCards(data.dealer?.cards || [])
    setDealerHandValue(data.dealer?.handValue || 0)
  }

  useEffect(() => {
    fetchState()
  }, [])

  const handleAddPlayer = async () => {
    if (players.length >= 8) return

    const name = `Player ${players.length + 1}`
    const money = 1000
    const playerType = "default"

    try {
      await addPlayer(name, money, playerType)
      fetchState()
    } catch (err) {
      console.error("Failed to add player:", err)
    }
  }

  const handleRemovePlayer = async (id: string) => {
    await deletePlayer(id)
    fetchState()
  }

  const handleTogglePlayer = async (id: string) => {
    await togglePlayer(id)
    fetchState()
  }

  const handleUpdatePlayer = async (updated: Player) => {
    await updatePlayer(updated.id, {
      name: updated.name,
      money: updated.money,
      playerType: updated.playerType,
    })
    fetchState()
  }

  const handleResetPlayers = async () => {
    await resetPlayers()
    fetchState()
  }

  return (
    <div className="min-h-screen bg-emerald-800 p-4 md:p-6 lg:p-8">
      <div className="max-w-7xl mx-auto space-y-6">
        <h1 className="text-3xl font-bold text-white text-center mb-6">Blackjack Simulator</h1>

        <DealerSection
          currentCardCount={currentCardCount}
          dealer={{
            cards: dealerCards,
            handValue: dealerHandValue,
          }}
        />

        <GlobalControls
          simulationSpeed={simulationSpeed}
          setSimulationSpeed={setSimulationSpeed}
          resetAllPlayers={handleResetPlayers}
          refreshGameState={fetchState}
        />

        <PlayerGrid
          players={players}
          removePlayer={handleRemovePlayer}
          togglePlayerActive={handleTogglePlayer}
          updatePlayer={handleUpdatePlayer}
        />

        <AddPlayerButton addPlayer={handleAddPlayer} disabled={players.length >= 8} />
      </div>
    </div>
  )
}
