import javax.swing.JFrame
import javax.swing.WindowConstants

fun main() {
    val frame = JFrame("Sandbox")
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    frame.add(Container())
    frame.pack()
    frame.setLocationRelativeTo(null)
    frame.isResizable = false
    frame.isVisible = true
}