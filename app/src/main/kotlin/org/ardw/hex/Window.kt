package org.ardw.hex

import org.llschall.ardwloop.serial.ArdwPortSelector
import java.awt.BorderLayout
import java.io.StringWriter
import java.nio.file.Paths
import javax.swing.*

class Window : JFrame() {

    init {
        contentPane = Panel()
        title = "Ardwloop Hex Updater"
        pack()
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true;
        addKeyListener(Listener())
    }
}

class Panel : JPanel() {

    val portMdl = PortModel()

    init {
        layout = BorderLayout()
        val here = Paths.get("")

        val table = JTable(portMdl)
        table.autoCreateRowSorter = true

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