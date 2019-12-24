import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JPanel

class Grid(private val optionsPanelRef: Options) : JPanel() {
    companion object {
        const val GRID_WIDTH: Int = 25  // amount of Square objects in a row
        const val GRID_HEIGHT: Int = 25 // amount of Square objects in a column
    }

    private val grid: List<List<Square>> = List(GRID_WIDTH) { columnIndex: Int ->
        List(GRID_HEIGHT) {
            Square(
                it * Square.SQUARE_WIDTH,
                columnIndex * Square.SQUARE_HEIGHT,
                SquareType.BLANK
            )
        }
    }

    init {
        // set preferredSize for pack() in the JFrame
        preferredSize =
            Dimension(GRID_WIDTH * Square.SQUARE_WIDTH, GRID_HEIGHT * Square.SQUARE_HEIGHT)

        // register the mouse listener
        addMouseListener(object: MouseListener {
            override fun mouseReleased(e: MouseEvent?) {
                if (e == null) return

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

        for (row in grid) {
            for (square in row) {
                square.draw(g)
            }
        }
    }
}