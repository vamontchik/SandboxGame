import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class Simulation(private val gridPanelRef: GridPanel) {
    private val service: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
    private lateinit var future: ScheduledFuture<*>

    fun start() {
        // previous copy is sent to GC
        future = service.scheduleAtFixedRate({ update() }, 1L, 1L, TimeUnit.SECONDS)
    }

    fun reset() {
        var result = false
        while (!result) {
            result = future.cancel(false)
        }
    }

    private fun update() {
        // TODO: iterate through board and apply rules... how do the blocks interact?
        applyRules(gridPanelRef.grid)
        gridPanelRef.redraw()
    }

    private fun applyRules(grid: Grid) {
        val localCopy = grid.copy()
        val gridRefContents = grid.grid

        // modify contents of localCopy according to what squares are there
        for (i in 0 until gridRefContents.size) {
            for (j in 0 until gridRefContents[0].size) { // assume: (at least) rectangular
                val squareOnRef = gridRefContents[i][j]

                when (squareOnRef.type) {
                    SquareType.BLANK -> {} // do nothing
                    SquareType.FIRE -> {
                        if (i + 1 < GridPanel.GRID_HEIGHT) {
                            val squareBelow = gridRefContents[i + 1][j]
                            val newX = squareBelow.x
                            val newY = squareBelow.y
                            val newType = SquareType.FIRE
                            localCopy[i + 1][j] = Square(newX, newY, newType)
                        }
                    }
                    SquareType.WATER -> {
                        if (i > 0) {
                            val squareAbove = gridRefContents[i - 1][j]
                            val newX = squareAbove.x
                            val newY = squareAbove.y
                            val newType = SquareType.WATER
                            localCopy[i - 1][j] = Square(newX, newY, newType)
                        }
                    }
                    SquareType.GRASS -> {}
                    SquareType.DIRT -> {}
                    SquareType.SUN -> {}
                }
            }
        }

        // set the state of the grid to the state of the local copy
        // note: DON'T copy out of localCopy so that we use the memory allocated
        //       from the creation of the local copy, the reference from this method
        //       simply "vanishes" instead...
        for (i in 0 until gridRefContents.size) {
            for (j in 0 until gridRefContents[0].size) { // assume: (at least) rectangular
                gridRefContents[i][j] = localCopy[i][j]
            }
        }
    }
}