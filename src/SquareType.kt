import java.awt.Color

enum class SquareType(val color: Color) {
    BLANK(Color.LIGHT_GRAY),
    FIRE(Color.RED),
    WATER(Color.BLUE),
    GRASS(Color.GREEN),
    DIRT(Color(51,0,0)), // brown
    SUN(Color(255,255,0)) // yellow
}