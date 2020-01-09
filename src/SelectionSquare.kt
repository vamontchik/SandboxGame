import java.awt.Color
import java.awt.Graphics

data class SelectionSquare(val square: Square, val isToggled: Boolean) {
    fun draw(g: Graphics) {
        square.draw(g)
        if (isToggled) {
            g.color = Color.BLACK
            g.drawRect(square.x, square.y, Square.SQUARE_WIDTH, Square.SQUARE_HEIGHT)
        }
    }
}