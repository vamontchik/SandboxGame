import javax.swing.JFrame
import javax.swing.WindowConstants

// TODO: how do different square types interact?

fun main() {
    val frame: JFrame = JFrame("Sandbox")
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    frame.add(Container())
    frame.pack()
    frame.setLocationRelativeTo(null)
    frame.isResizable = false
    frame.isVisible = true
}