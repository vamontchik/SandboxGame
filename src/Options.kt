import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.ActionEvent
import java.awt.event.MouseEvent
import javax.swing.JPanel
import javax.swing.JToggleButton
import java.awt.event.MouseListener
import javax.swing.AbstractAction
import javax.swing.KeyStroke

class Options : JPanel() {
    companion object {
        const val TOP_PADDING = 100   // 100 down from the top
        const val SQUARE_PADDING = 25 // 25 between each square
    }

    private val options: List<SelectionSquare> = List(SquareType.values().size) {
        SelectionSquare(
            Grid.GRID_WIDTH * Square.SQUARE_WIDTH / 2 - Square.SQUARE_WIDTH / 2,
            TOP_PADDING + (SQUARE_PADDING + Square.SQUARE_HEIGHT) * it,
            SquareType.values()[it],
            false
        )
    }

    var currSelected: SelectionSquare = options[0].also { it.isToggled = true } // 0 == BLANK
        private set

    private val playButton: JToggleButton = JToggleButton("Play")
    private val playButtonStr: String = "PLAY"

    init {
        // set preferredSize for pack() in the JFrame
        preferredSize = Dimension(
            Grid.GRID_WIDTH * Square.SQUARE_WIDTH, Grid.GRID_HEIGHT * Square.SQUARE_HEIGHT
        )

        // register mouse listener
        addMouseListener(object: MouseListener {
            override fun mouseReleased(e: MouseEvent?) {
                if (e == null) return
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

        // register key bindings
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), playButtonStr)
        actionMap.put(playButtonStr, object: AbstractAction() {
            override fun actionPerformed(e: ActionEvent?) {
                if (e == null) return
                playButton.isSelected = !playButton.isSelected
            }
        })

        // add button
        add(playButton)
    }

    override fun paintComponent(g: Graphics?) {
        if (g == null) return

        super.paintComponent(g)

        for (square in options) {
            square.draw(g)
        }
    }
}