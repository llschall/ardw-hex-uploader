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

    init {
        layout = BorderLayout()
        val here = Paths.get("")

        val portMdl = PortModel()
        val table = JTable(portMdl)
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)

        val listBtn = JButton("list")


        val focus = FocusMonitor()

        listBtn.addActionListener {
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
            table.revalidate()
        }

        add(JLabel("Current path is " + here.toAbsolutePath()), BorderLayout.NORTH)
        add(JScrollPane(table), BorderLayout.CENTER)
        add(listBtn, BorderLayout.SOUTH)
        add(focus.button, BorderLayout.WEST)

        focus.button.addKeyListener(Listener())
        focus.button.requestFocus()
    }
}

class FocusMonitor : FocusListener {

    val button = JButton("Focus")

    init {
        button.isFocusable = true
        button.isOpaque = true
        button.background = Color.WHITE
        button.addFocusListener(this)
    }


    override fun focusGained(e: FocusEvent?) {
        button.background = Color.WHITE
        button.revalidate()
    }

    override fun focusLost(e: FocusEvent?) {
        button.background = Color.DARK_GRAY
        button.revalidate()
    }
}