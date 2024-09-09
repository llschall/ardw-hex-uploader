package org.ardw.hex

import org.llschall.ardwloop.serial.ArdwPortSelector
import java.awt.BorderLayout
import java.awt.Color
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
import java.nio.file.Paths
import javax.swing.*

class Window : JFrame() {

    init {
        contentPane = Panel()
        title = "Ardwloop Hex Updater"
        pack()
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }
}

class Panel : JPanel() {

    val portMdl = PortModel()

    init {
        layout = BorderLayout()
        val here = Paths.get("")

        val table = JTable(portMdl)
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)

        val listBtn = JButton("list")

        val focus = FocusMonitor(this)

        listBtn.addActionListener {
            listPorts()
            table.revalidate()
            requestFocus()
        }

        add(JLabel("Current path is " + here.toAbsolutePath()), BorderLayout.NORTH)
        add(JScrollPane(table), BorderLayout.CENTER)
        add(listBtn, BorderLayout.SOUTH)

        FocusMonitor(this)

        addKeyListener(Listener {
            listPorts()
            table.revalidate()
            requestFocus()
        })

        requestFocus()
    }

    private fun listPorts() {
        val selector = ArdwPortSelector()

        portMdl.values.clear()

        for (descriptor in selector.list()) {

            val list = ArrayList<String>()
            list.add(descriptor.name)
            list.add(descriptor.description)
            list.add(descriptor.systemName)

            if (selector.select(descriptor)) {
                list.add("S")
            } else {
                list.add("-")
            }
            portMdl.values.add(list)
        }
    }

}

class FocusMonitor(val panel: JPanel) : FocusListener {

    init {
        panel.isFocusable = true
        panel.addFocusListener(this)
    }


    override fun focusGained(e: FocusEvent?) {
        panel.border = BorderFactory.createLineBorder(Color.BLUE, 4)
        panel.revalidate()
    }

    override fun focusLost(e: FocusEvent?) {
        panel.border = BorderFactory.createLineBorder(Color.BLACK, 4)
        panel.revalidate()
    }
}