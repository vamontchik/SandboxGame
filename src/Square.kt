import java.awt.Color
import java.awt.Graphics

open class Square(val x: Int, val y: Int, var type: SquareType) {
    companion object {
        const val SQUARE_WIDTH = 25   // amount of pixels, width
        const val SQUARE_HEIGHT = 25  // amount of pixels, height
    }

    open fun draw(g: Graphics) {
        g.color = type.color
        g.fillRect(x, y, SQUARE_WIDTH, SQUARE_HEIGHT)
        g.color = Color.WHITE
        g.drawRect(x, y, SQUARE_WIDTH, SQUARE_HEIGHT)
    }
}