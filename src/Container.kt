import javax.swing.BoxLayout
import javax.swing.JPanel

class Container : JPanel() {
    private val optionsPanel: Options = Options()
    private val gridPanel: Grid = Grid(optionsPanel)

    init {
        layout = BoxLayout(this, BoxLayout.X_AXIS)
        add(gridPanel)
        add(optionsPanel)
    }
}