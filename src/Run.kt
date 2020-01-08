import javax.swing.JFrame
import javax.swing.UIManager
import javax.swing.WindowConstants

fun main() {
    // set look & feel from windows 10, not java default
    // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

    val frame = JFrame("Sandbox")
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    frame.add(Container())
    frame.pack()
    frame.setLocationRelativeTo(null)
    frame.isResizable = false
    frame.isVisible = true
}