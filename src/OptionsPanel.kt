import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JPanel

class OptionsPanel : JPanel() {
    companion object {
        const val SQUARE_PADDING = 10
        const val WIDTH = 3 * Square.SQUARE_WIDTH
        const val TOP_PADDING = 25
    }

    // data object for different selection squares
    private val options: Options = Options()

    // used as a guard for modification of the options,
    // can be externally modified by Simulation
    var isModifiable: Boolean = true

    init {
        // set preferredSize for pack() in the JFrame
        preferredSize = Dimension(
            WIDTH, (SquareType.values().size)*(Square.SQUARE_HEIGHT)
        )

        // register mouse listener to select which color
        addMouseListener(object: MouseListener {
            override fun mouseReleased(e: MouseEvent?) {
                if (e == null || !isModifiable) return
                val index: Int = options.findSquareIndex(e.x, e.y) ?: return

                options.setNewToggle(index)

                revalidate()
                repaint()
            }
            override fun mouseEntered(e: MouseEvent?) {}
            override fun mouseClicked(e: MouseEvent?) {}
            override fun mouseExited(e: MouseEvent?) {}
            override fun mousePressed(e: MouseEvent?) {}
        })
    }

    fun getCurrentColor() = options.getCurrentColor()

    override fun paintComponent(g: Graphics?) {
        if (g == null) return

        super.paintComponent(g)

        options.draw(g)
    }
}