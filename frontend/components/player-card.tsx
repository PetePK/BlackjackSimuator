"use client"

import { useState } from "react"
import type { Player } from "@/lib/types"
import { Card, CardContent, CardFooter } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Switch } from "@/components/ui/switch"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { PlayingCard } from "./playing-card"
import { Edit, Trash2, Eye, Power } from "lucide-react"
import { Collapsible, CollapsibleContent, CollapsibleTrigger } from "@/components/ui/collapsible"

interface PlayerCardProps {
  player: Player
  onRemove: (id: string) => void
  onToggleActive: (id: string) => void
  onUpdate: (player: Player) => void
}

export function PlayerCard({ player, onRemove, onToggleActive, onUpdate }: PlayerCardProps) {
  const [isEditing, setIsEditing] = useState(false)
  const [editedPlayer, setEditedPlayer] = useState<Player>(player)
  const [historyOpen, setHistoryOpen] = useState(false)

  const handleSave = () => {
    onUpdate(editedPlayer)
    setIsEditing(false)
  }

  const handleCancel = () => {
    setEditedPlayer(player)
    setIsEditing(false)
  }

  // Sample cards for display
  const playerCards = [
    { suit: "diamonds", value: "7" },
    { suit: "clubs", value: "K" },
  ]

  return (
    <Card
      className={`shadow-lg transition-all ${player.active ? "bg-emerald-900 border-emerald-500" : "bg-gray-800 border-gray-600 opacity-75"}`}
    >
      <CardContent className="p-4 space-y-4">
        {isEditing ? (
          <div className="space-y-3">
            <div>
              <Label htmlFor={`name-${player.id}`} className="text-white">
                Name
              </Label>
              <Input
                id={`name-${player.id}`}
                value={editedPlayer.name}
                onChange={(e) => setEditedPlayer({ ...editedPlayer, name: e.target.value })}
                className="bg-emerald-800 border-emerald-600 text-white"
              />
            </div>

            <div>
              <Label htmlFor={`money-${player.id}`} className="text-white">
                Starting Money
              </Label>
              <Input
                id={`money-${player.id}`}
                type="number"
                value={editedPlayer.money}
                onChange={(e) => setEditedPlayer({ ...editedPlayer, money: Number(e.target.value) })}
                className="bg-emerald-800 border-emerald-600 text-white"
              />
            </div>

            <div>
              <Label htmlFor={`strategy-${player.id}`} className="text-white">
                Strategy
              </Label>
              <Select
                value={editedPlayer.strategy}
                onValueChange={(value) => setEditedPlayer({ ...editedPlayer, strategy: value })}
              >
                <SelectTrigger className="bg-emerald-800 border-emerald-600 text-white">
                  <SelectValue placeholder="Select strategy" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="Conservative">Conservative</SelectItem>
                  <SelectItem value="Aggressive">Aggressive</SelectItem>
                  <SelectItem value="Random">Random</SelectItem>
                </SelectContent>
              </Select>
            </div>

            <div className="flex items-center justify-between">
              <Label htmlFor={`card-counting-${player.id}`} className="text-white">
                Card Counting
              </Label>
              <Switch
                id={`card-counting-${player.id}`}
                checked={editedPlayer.cardCounting}
                onCheckedChange={(checked) => setEditedPlayer({ ...editedPlayer, cardCounting: checked })}
              />
            </div>

            <div className="flex items-center justify-between">
              <Label htmlFor={`betting-strategy-${player.id}`} className="text-white">
                Betting Strategy
              </Label>
              <Switch
                id={`betting-strategy-${player.id}`}
                checked={editedPlayer.bettingStrategy}
                onCheckedChange={(checked) => setEditedPlayer({ ...editedPlayer, bettingStrategy: checked })}
              />
            </div>

            <div className="flex space-x-2 pt-2">
              <Button onClick={handleSave} className="bg-emerald-700 hover:bg-emerald-600 text-white">
                Save
              </Button>
              <Button
                variant="outline"
                onClick={handleCancel}
                className="bg-transparent text-white hover:bg-emerald-800 border-emerald-600"
              >
                Cancel
              </Button>
            </div>
          </div>
        ) : (
          <>
            <div className="flex justify-between items-center">
              <h3 className="text-xl font-bold text-white">{player.name}</h3>
              <div className="flex space-x-1">
                <Button
                  variant="ghost"
                  size="icon"
                  onClick={() => setIsEditing(true)}
                  className="h-8 w-8 text-white hover:bg-emerald-800"
                >
                  <Edit className="h-4 w-4" />
                </Button>
                <Button
                  variant="ghost"
                  size="icon"
                  onClick={() => onRemove(player.id)}
                  className="h-8 w-8 text-white hover:bg-emerald-800"
                >
                  <Trash2 className="h-4 w-4" />
                </Button>
                <Button
                  variant="ghost"
                  size="icon"
                  onClick={() => onToggleActive(player.id)}
                  className={`h-8 w-8 ${player.active ? "text-green-400" : "text-gray-400"} hover:bg-emerald-800`}
                >
                  <Power className="h-4 w-4" />
                </Button>
              </div>
            </div>

            <div className="grid grid-cols-2 gap-2 text-sm">
              <div className="text-gray-300">Strategy:</div>
              <div className="text-white font-medium">{player.strategy}</div>

              <div className="text-gray-300">Card Counting:</div>
              <div className="text-white font-medium">{player.cardCounting ? "Enabled" : "Disabled"}</div>

              <div className="text-gray-300">Betting Strategy:</div>
              <div className="text-white font-medium">{player.bettingStrategy ? "Enabled" : "Disabled"}</div>
            </div>

            <div className="bg-emerald-950 rounded-md p-3 space-y-2">
              <div className="flex justify-between">
                <span className="text-gray-300">Current Bet:</span>
                <span className="text-white font-bold">${player.currentBet}</span>
              </div>

              <div className="flex justify-between">
                <span className="text-gray-300">Money Left:</span>
                <span className="text-white font-bold">${player.money}</span>
              </div>

              <div className="flex justify-between">
                <span className="text-gray-300">Record:</span>
                <span className="text-white font-bold">
                  W: {player.wins} / L: {player.losses}
                </span>
              </div>
            </div>

            <div>
              <div className="text-gray-300 mb-1">Current Hand:</div>
              <div className="flex justify-center gap-1">
                {playerCards.map((card, index) => (
                  <PlayingCard key={index} card={card} small />
                ))}
              </div>
              <div className="text-center mt-1 text-white font-bold">Hand Value: {player.handValue || 17}</div>
            </div>
          </>
        )}
      </CardContent>

      {!isEditing && (
        <CardFooter className="p-2 bg-emerald-950 rounded-b-lg">
          <Collapsible open={historyOpen} onOpenChange={setHistoryOpen} className="w-full">
            <CollapsibleTrigger asChild>
              <Button
                variant="ghost"
                className="w-full text-white hover:bg-emerald-800 flex items-center justify-center"
              >
                <Eye className="h-4 w-4 mr-2" />
                {historyOpen ? "Hide History" : "View Hand History"}
              </Button>
            </CollapsibleTrigger>
            <CollapsibleContent className="mt-2 p-2 bg-emerald-900 rounded text-sm text-white">
              <div className="space-y-1">
                <div className="flex justify-between">
                  <span>Hand #1:</span>
                  <span className="text-green-400">Win (+$50)</span>
                </div>
                <div className="flex justify-between">
                  <span>Hand #2:</span>
                  <span className="text-red-400">Loss (-$50)</span>
                </div>
                <div className="flex justify-between">
                  <span>Hand #3:</span>
                  <span className="text-green-400">Win (+$100)</span>
                </div>
              </div>
            </CollapsibleContent>
          </Collapsible>
        </CardFooter>
      )}
    </Card>
  )
}
