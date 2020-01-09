import javax.swing.JPanel
import javax.swing.JToggleButton

class SimulationPanel(private val optionsPanelRef: OptionsPanel, private val gridPanelRef: GridPanel) : JPanel() {
    private val playButton: JToggleButton = JToggleButton("Play")
    private val simulation: Simulation = Simulation(gridPanelRef)

    init {
        playButton.addActionListener {
            if (it == null) return@addActionListener // ??? necessary ???

            if (playButton.isSelected) {
                start()
            } else {
                reset()
            }
        }

        add(playButton)
    }

    private fun start() {
        optionsPanelRef.isModifiable = false
        gridPanelRef.isModifiable = false

        simulation.start()
    }

    private fun reset() {
        simulation.reset()

        optionsPanelRef.isModifiable = true
        gridPanelRef.isModifiable = true
    }
}