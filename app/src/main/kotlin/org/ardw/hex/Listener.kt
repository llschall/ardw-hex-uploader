package org.ardw.hex

import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.util.LinkedList
import javax.swing.event.TableModelListener
import javax.swing.table.TableModel
import kotlin.system.exitProcess

class Listener : KeyListener {
    override fun keyTyped(e: KeyEvent) {
        // do nothing
    }

    override fun keyPressed(e: KeyEvent) {
        if (KeyEvent.VK_ESCAPE.toChar() == e.keyChar) {
            exitProcess(0)
        }
    }

    override fun keyReleased(e: KeyEvent) {
        // do nothing
    }
}

class PortModel : TableModel {

    private val columns = listOf("Name", "Description", "System", "Selectable");
    val values = LinkedList<ArrayList<String>>()

    override fun getRowCount(): Int {
        val size = values.size
        println(size)
        return size
    }

    override fun getColumnCount(): Int {
        return columns.size
    }

    override fun getColumnName(columnIndex: Int): String {
        return columns[columnIndex]
    }

    override fun getColumnClass(columnIndex: Int): Class<*> {
        return String::class.java;
    }

    override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean {
        return false
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): String {
        return values[rowIndex][columnIndex]
    }

    override fun setValueAt(aValue: Any?, rowIndex: Int, columnIndex: Int) {
        values[rowIndex][columnIndex] = aValue.toString()
    }

    override fun addTableModelListener(l: TableModelListener?) {
        // no listener
    }

    override fun removeTableModelListener(l: TableModelListener?) {
        // no listener
    }
}