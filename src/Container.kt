import javax.swing.BoxLayout
import javax.swing.JPanel

class Container : JPanel() {
    private val optionsPanel: OptionsPanel = OptionsPanel()
    private val gridPanel: GridPanel = GridPanel(optionsPanel)
    private val simulationPanel: SimulationPanel = SimulationPanel(optionsPanel, gridPanel)

    init {
        layout = BoxLayout(this, BoxLayout.X_AXIS)
        add(gridPanel)
        add(optionsPanel)
        add(simulationPanel)
    }
}