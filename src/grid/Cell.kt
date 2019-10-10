package grid

abstract class Cell {

    var children = mutableListOf<Cell>()

    fun hasChildren(): Boolean{
        return children.size > 0
    }
}