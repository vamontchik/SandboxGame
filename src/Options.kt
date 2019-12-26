import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JPanel

class Options : JPanel() {
    companion object {
        const val SQUARE_PADDING = 10
        const val WIDTH = 3 * Square.SQUARE_WIDTH
        const val TOP_PADDING = 25
    }

    private val options: List<SelectionSquare> = List(SquareType.values().size) {
        SelectionSquare(
            WIDTH / 2 - Square.SQUARE_WIDTH / 2,
            TOP_PADDING + (SQUARE_PADDING + Square.SQUARE_HEIGHT) * it,
            SquareType.values()[it],
            false
        )
    }

    // used by Grid to determine which color to fill in squares with
    var currSelected: SelectionSquare = options[0].also { it.isToggled = true } // 0 == BLANK
        private set

    // used as a guard for modification of the options,
    // can be externally modified by Simulation
    var isModifiable: Boolean = true

    init {
        // set preferredSize for pack() in the JFrame
        preferredSize = Dimension(
            WIDTH, (options.size)*(Square.SQUARE_HEIGHT)
        )

        // register mouse listener to select which color
        addMouseListener(object: MouseListener {
            override fun mouseReleased(e: MouseEvent?) {
                if (e == null || !isModifiable) return
                val selected: SelectionSquare = findSquare(e.x, e.y, options) as SelectionSquare? ?: return

                currSelected.isToggled = false
                selected.isToggled = true
                currSelected = selected

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

        for (square in options) {
            square.draw(g)
        }
    }
}