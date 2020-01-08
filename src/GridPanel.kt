import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JPanel

class GridPanel(private val optionsPanelRef: OptionsPanel) : JPanel() {
    companion object {
        const val GRID_WIDTH: Int = 25  // amount of Square objects in a row
        const val GRID_HEIGHT: Int = 25 // amount of Square objects in a column
    }

    // data object for 2D grid of squares
    private val grid: Grid = Grid()

    // used as a guard for modification of the grid,
    // can be externally modified by Simulation
    var isModifiable: Boolean = true

    init {
        // set preferredSize for pack() in the JFrame
        preferredSize =
            Dimension(GRID_WIDTH * Square.SQUARE_WIDTH, GRID_HEIGHT * Square.SQUARE_HEIGHT)

        // register the mouse listener to allow filling in colors on grid
        addMouseListener(object: MouseListener {
            override fun mouseReleased(e: MouseEvent?) {
                if (e == null || !isModifiable) return

                // .first == x, as an index in the grid
                // .second == y, as an index in the grid
                val coordinates: Pair<Int, Int> = grid.findSquareIndex(e.x, e.y) ?: return
                grid.setAt(coordinates.first, coordinates.second, optionsPanelRef.getCurrentColor())

                revalidate()
                repaint()
            }
            override fun mouseEntered(e: MouseEvent?) {}
            override fun mouseClicked(e: MouseEvent?) {}
            override fun mouseExited(e: MouseEvent?) {}
            override fun mousePressed(e: MouseEvent?) {}
        })
    }

    override fun paintComponent(g: Graphics?) {
        if (g == null) return

        super.paintComponent(g)

        grid.draw(g)
    }

//    fun setState(grid: List<List<Square>>) {
//        this.grid.setState(grid) // copy into class member
//    }
}