"use client"

import { Button } from "@/components/ui/button"
import { Card, CardContent } from "@/components/ui/card"
import { Slider } from "@/components/ui/slider"
import { Play, FastForward, RotateCcw } from "lucide-react"

interface GlobalControlsProps {
  simulationSpeed: number
  setSimulationSpeed: (speed: number) => void
  resetAllPlayers: () => void
}

export function GlobalControls({ simulationSpeed, setSimulationSpeed, resetAllPlayers }: GlobalControlsProps) {
  return (
    <Card className="bg-emerald-900 border-emerald-600 shadow-lg">
      <CardContent className="p-6">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div className="flex flex-wrap gap-2 justify-center md:justify-start">
            <Button className="bg-emerald-700 hover:bg-emerald-600 text-white">
              <Play className="mr-2 h-4 w-4" />
              Start Simulation
            </Button>

            <Button variant="outline" className="bg-emerald-800 text-white hover:bg-emerald-700 border-emerald-500">
              <Play className="mr-2 h-4 w-4" />
              Play One Round
            </Button>

            <Button variant="outline" className="bg-emerald-800 text-white hover:bg-emerald-700 border-emerald-500">
              <FastForward className="mr-2 h-4 w-4" />
              Fast Forward x100 Hands
            </Button>

            <Button variant="destructive" onClick={resetAllPlayers}>
              <RotateCcw className="mr-2 h-4 w-4" />
              Reset All Players
            </Button>
          </div>

          <div className="flex flex-col space-y-2">
            <label className="text-white text-sm font-medium">Simulation Speed: {simulationSpeed}s per hand</label>
            <div className="flex items-center space-x-2">
              <span className="text-white text-xs">0.5s</span>
              <Slider
                value={[simulationSpeed]}
                min={0.5}
                max={3}
                step={0.5}
                onValueChange={(value) => setSimulationSpeed(value[0])}
                className="flex-1"
              />
              <span className="text-white text-xs">3s</span>
            </div>
          </div>
        </div>
      </CardContent>
    </Card>
  )
}
