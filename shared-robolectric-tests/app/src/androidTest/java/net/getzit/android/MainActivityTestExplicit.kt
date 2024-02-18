package net.getzit.android

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTestExplicit {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val context: Context
        get() = composeRule.activity.applicationContext

    private fun getString(@StringRes id: Int) = context.getString(id)

    @Test
    fun testButton() = with(composeRule) {
        composeRule.waitForIdle()
        onNode(hasClickAction()).assertTextEquals(getString(R.string.test))
        onNode(hasClickAction()).performClick()
        assertEquals(2, 1 + 1)
    }
}
