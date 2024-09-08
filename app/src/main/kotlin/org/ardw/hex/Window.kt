package org.ardw.hex

import org.llschall.ardwloop.serial.ArdwPortSelector
import java.awt.GridLayout
import java.io.StringWriter
import java.nio.file.Paths
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

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

    val portLbl0: JLabel
    val portLbl1: JLabel
    val portLbl2: JLabel
    val portLbl3: JLabel

    init {
        layout = GridLayout(0, 1)
        val here = Paths.get("")
        add(JLabel("Current path is " + here.toAbsolutePath()))
        portLbl0 = JLabel()
        portLbl1 = JLabel()
        portLbl2 = JLabel()
        portLbl3 = JLabel()

        val listBtn = JButton("list")

        listBtn.addActionListener {
            val writer0 = StringWriter()
            val writer1 = StringWriter()
            val writer2 = StringWriter()
            val writer3 = StringWriter()
            val selector = ArdwPortSelector()
            for (descriptor in selector.list()) {
                writer0.append(descriptor.name)
                writer0.append(';')
                writer1.append(descriptor.systemName)
                writer1.append(';')
                writer2.append(descriptor.description)
                writer2.append(';')

                if (selector.select(descriptor)) {
                    writer3.append("S")
                } else {
                    writer3.append("-")
                }
                writer3.append(";")
            }

            portLbl0.text = writer0.toString()
            portLbl1.text = writer1.toString()
            portLbl2.text = writer2.toString()
            portLbl3.text = writer3.toString()
        }

        add(portLbl0)
        add(portLbl1)
        add(portLbl2)
        add(portLbl3)
        add(listBtn)
    }
}