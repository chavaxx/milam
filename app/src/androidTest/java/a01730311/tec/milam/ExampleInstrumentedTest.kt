package a01730311.tec.milam

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented inicio_sesion.xml, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under inicio_sesion.xml.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("a01730311.tec.milam", appContext.packageName)
    }
}