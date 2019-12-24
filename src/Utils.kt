fun findSquare(x: Int, y: Int, squares: List<Square>): Square? {
    for (square in squares) {
        val xLowerBound: Int = square.x
        val xUpperBound: Int = square.x + Square.SQUARE_WIDTH
        val yLowerBound: Int = square.y
        val yUpperBound: Int = square.y + Square.SQUARE_HEIGHT
        if (x in xLowerBound..xUpperBound && y in yLowerBound..yUpperBound) {
            return square
        }
    }
    return null
}