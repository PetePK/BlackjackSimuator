"use client"

import { useState, useEffect } from "react"
import { DealerSection } from "./dealer-section"
import { GlobalControls } from "./global-controls"
import { PlayerGrid } from "./player-grid"
import { AddPlayerButton } from "./add-player-button"
import type { Player } from "@/lib/types"

const API_BASE = "http://localhost:8080/api/game"

export function BlackjackSimulator() {
  const [players, setPlayers] = useState<Player[]>([])
  const [simulationSpeed, setSimulationSpeed] = useState(1)

  /** Fetch players from backend */
  const fetchPlayers = async () => {
    const res = await fetch(`${API_BASE}/players`)
    if (!res.ok) return
    setPlayers(await res.json())
  }

  useEffect(() => {
    fetchPlayers()
  }, [])

  /** Add a new player */
  const addPlayer = async () => {
    if (players.length >= 8) return
    const newP: Partial<Player> = {
      id: Date.now().toString(),
      name: `Player ${players.length + 1}`,
      money: 1000,
      strategy: "Conservative",
      cardCounting: false,
      bettingStrategy: false,
      currentBet: 50,
    }
    const res = await fetch(`${API_BASE}/players`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newP),
    })
    if (res.ok) fetchPlayers()
  }

  /** Remove player */
  const removePlayer = async (id: string) => {
    await fetch(`${API_BASE}/players/${id}`, { method: "DELETE" })
    fetchPlayers()
  }

  /** Toggle active */
  const togglePlayerActive = async (id: string) => {
    await fetch(`${API_BASE}/players/${id}/toggle`, { method: "PUT" })
    fetchPlayers()
  }

  /** Update player */
  const updatePlayer = async (p: Player) => {
    await fetch(`${API_BASE}/players`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(p),
    })
    fetchPlayers()
  }

  /** Reset all */
  const resetAllPlayers = async () => {
    await fetch(`${API_BASE}/reset`, { method: "POST" })
    fetchPlayers()
  }

  /** Play one round */
  const playOneRound = async () => {
    await fetch(`${API_BASE}/round`, { method: "POST" })
    fetchPlayers()
  }

  /** Fast‑forward 100 rounds */
  const fastForward = async () => {
    for (let i = 0; i < 100; i++) {
      await fetch(`${API_BASE}/round`, { method: "POST" })
    }
    fetchPlayers()
  }

  return (
    <div className="min-h-screen bg-emerald-800 p-4 md:p-6 lg:p-8">
      <div className="max-w-7xl mx-auto space-y-6">
        <h1 className="text-3xl font-bold text-white text-center mb-6">
          Blackjack Simulator
        </h1>

        <DealerSection />

        <GlobalControls
          simulationSpeed={simulationSpeed}
          setSimulationSpeed={setSimulationSpeed}
          resetAllPlayers={resetAllPlayers}
          playOneRound={playOneRound}
          fastForward={fastForward}
        />

        <PlayerGrid
          players={players}
          removePlayer={removePlayer}
          togglePlayerActive={togglePlayerActive}
          updatePlayer={updatePlayer}
        />

        <AddPlayerButton
          addPlayer={addPlayer}
          disabled={players.length >= 8}
        />
      </div>
    </div>
  )
}
