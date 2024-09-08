package org.ardw.hex

import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import kotlin.system.exitProcess

class Listener : KeyListener {
    override fun keyTyped(e: KeyEvent) {
        // do nothing
    }

    override fun keyPressed(e: KeyEvent) {

        println(e)

        if (KeyEvent.VK_ESCAPE.toChar() == e.keyChar) {
            exitProcess(0)
        }

    }

    override fun keyReleased(e: KeyEvent) {
        // do nothing
    }
}