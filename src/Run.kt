import javax.swing.JFrame
import javax.swing.WindowConstants

// TODO: move Grid, Options, and Simulation "data" classes into a "model,"
//       so they're not attached to their respective UI elements as instances.
//       Also, give all UI elements a reference to this "model" during initialization
//       inside of the Container class.
fun main() {
    val frame = JFrame("Sandbox")
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    frame.add(Container())
    frame.pack()
    frame.setLocationRelativeTo(null)
    frame.isResizable = false
    frame.isVisible = true
}