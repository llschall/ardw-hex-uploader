package org.ardw.hex

import java.awt.BorderLayout
import java.nio.file.Path
import java.nio.file.Paths
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

    init {
        layout = BorderLayout()
        val here = Paths.get("")
        add(JLabel("Current path is " + here.toAbsolutePath()))
    }
}