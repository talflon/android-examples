package net.getzit.android

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

abstract class MainActivityTester {
    @get:Rule(order = 0)
    val composeRule = createComposeRule()

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    protected val context: Context
        get() = ApplicationProvider.getApplicationContext<MainActivity>()

    protected fun getString(@StringRes id: Int) = context.getString(id)

    @Test
    fun testButton() = with(composeRule) {
        onNode(hasClickAction()).assertTextEquals(getString(R.string.test))
        onNode(hasClickAction()).performClick()
        assertEquals(2, 1 + 1)
    }
}
