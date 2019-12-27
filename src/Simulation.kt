class Simulation {
    var initialState: List<List<Square>> = emptyList()
        private set

    private var state: List<List<Square>> = emptyList()

    fun start(initialState: List<List<Square>>) {
        state = initialState              // set initial state for sim
        this.initialState = initialState  // save an initial state copy for reset

        // TODO: run simulation!!!
    }

    fun reset() {
        state = emptyList()               // send to GC
        this.initialState = emptyList()   // send to GC
    }
}