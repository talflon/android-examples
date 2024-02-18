package net.getzit.android

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

abstract class MainActivityTester {
    @get:Rule
    val composeRule = createEmptyComposeRule()

    private val context: Context
        get() = ApplicationProvider.getApplicationContext<MainActivity>()

    protected fun getString(@StringRes id: Int) = context.getString(id)

    protected inline fun inActivity(f: (ActivityScenario<MainActivity>) -> Unit): Unit =
        ActivityScenario.launch(MainActivity::class.java).use(f)

    @Test
    fun testButton() = with(composeRule) {
        inActivity {
            onNode(hasClickAction()).assertTextEquals(getString(R.string.test))
            onNode(hasClickAction()).performClick()
            assertEquals(2, 1 + 1)
        }
    }
}
