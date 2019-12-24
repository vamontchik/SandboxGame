import java.awt.Color
import java.awt.Graphics

class SelectionSquare(x: Int, y: Int, type: SquareType, var isToggled: Boolean) : Square(x, y, type) {
    override fun draw(g: Graphics) {
        super.draw(g)
        if (isToggled) {
            g.color = Color.BLACK
            g.drawRect(x, y, SQUARE_WIDTH, SQUARE_HEIGHT)
        }
    }

    override fun toString(): String {
        return type.toString()
    }
}