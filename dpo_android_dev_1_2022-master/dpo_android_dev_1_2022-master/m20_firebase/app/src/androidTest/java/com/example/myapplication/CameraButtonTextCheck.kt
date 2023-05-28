package com.example.myapplication

import android.widget.FrameLayout
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CameraButtonTextCheck {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun cameraButtonTextCheck() {
        val button = Espresso.onView(
            Matchers.allOf(
                withId(R.id.bPhoto) , withText(R.string.photo) ,
                ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(FrameLayout::class.java))) ,
                ViewMatchers.isDisplayed()
            )
        ).check(ViewAssertions.matches(withText(R.string.photo)))
        button.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}