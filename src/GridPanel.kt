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
    val grid: Grid = Grid()

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

                val selected: Square = findSquare(e.x, e.y, grid.flatten()) ?: return
                selected.type = optionsPanelRef.currSelected.type

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

    fun copyState(): List<List<Square>> {
        return grid.copy()       // copy out of class member
    }

    fun setState(grid: List<List<Square>>) {
        this.grid.setState(grid) // copy into class member
    }
}