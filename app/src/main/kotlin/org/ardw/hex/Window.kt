package org.ardw.hex

import org.llschall.ardwloop.serial.ArdwPortSelector
import java.awt.BorderLayout
import java.nio.file.Paths
import javax.swing.*

class Window : JFrame() {

    init {
        contentPane = Panel()
        title = "Ardwloop Hex Updater"
        pack()
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
        addKeyListener(Listener())
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
    }
}