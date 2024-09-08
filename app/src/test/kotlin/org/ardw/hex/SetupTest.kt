package org.ardw.hex

import org.junit.jupiter.api.Test
import org.llschall.ardwloop.ArdwloopStarter
import kotlin.test.assertEquals

class SetupTest {

    @Test
    fun testVersions() {
        assertEquals(ArdwloopStarter.VERSION, "0.1.6")
    }
}