import javax.swing.JPanel
import javax.swing.JToggleButton

class SimulationPanel(private val optionsPanelRef: OptionsPanel, private val gridPanelRef: GridPanel) : JPanel() {
    private val playButton: JToggleButton = JToggleButton("Play")
    private val simulation: Simulation = Simulation()

    init {
        // add action to play button
        playButton.addActionListener {
            if (it == null) return@addActionListener // ??? necessary ???

            if (playButton.isSelected) {
                start()
            } else {
                reset()
            }
        }

        // add button
        add(playButton)
    }

    private fun start() {
        optionsPanelRef.isModifiable = false
        gridPanelRef.isModifiable = false

        simulation.start(gridPanelRef.copyState())
    }

    private fun reset() {
        gridPanelRef.setState(simulation.initialState)
        simulation.reset()

        optionsPanelRef.isModifiable = true
        gridPanelRef.isModifiable = true
    }
}