import javax.swing.JPanel
import javax.swing.JToggleButton

class Simulation(private val optionsRef: Options, private val gridRef: Grid) : JPanel() {
    private var forReset: List<List<Square>> = emptyList()
    private var initialState: List<List<Square>> = emptyList()
    private var canSaveCopy: Boolean = false

    private val playButton: JToggleButton = JToggleButton("Play")

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
        println("start")

        forReset = gridRef.copyState()      // set initial state for sim
        initialState = gridRef.copyState()  // save an initial state copy for reset
        canSaveCopy = true

        optionsRef.isModifiable = false
        gridRef.isModifiable = false

        simulation()
    }

    private fun reset() {
        println("reset")

        gridRef.setState(initialState)
        initialState = emptyList() // give contents to GC
        forReset = emptyList()     // give contents to GC
        canSaveCopy = false

        optionsRef.isModifiable = true
        gridRef.isModifiable = true
    }

    private fun simulation() {
        // do the goods here!!!
    }
}