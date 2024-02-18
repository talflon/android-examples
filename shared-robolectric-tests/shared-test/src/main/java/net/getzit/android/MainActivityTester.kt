package net.getzit.android

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

abstract class MainActivityTester {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val context: Context
        get() = composeRule.activity.applicationContext

    protected fun getString(@StringRes id: Int) = context.getString(id)

    @Test
    fun testButton() = with(composeRule) {
        onNode(hasClickAction()).assertTextEquals(getString(R.string.test))
        onNode(hasClickAction()).performClick()
        assertEquals(2, 1 + 1)
    }
}
