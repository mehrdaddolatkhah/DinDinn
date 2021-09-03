package com.dindinn.app.util

import com.dindinn.domain.util.Utils.toMilliSeconds
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class Util {

    @Test
    fun `check toMilliSeconds get string numbers and return long value`() {

        // GIVEN
        val sampleTimeAsString = "2021-06-10T15:00+00Z"
        var timeAsMilliseconds: Long = 0

        // WHEN
        timeAsMilliseconds = sampleTimeAsString.toMilliSeconds()

        // THEN
        MatcherAssert.assertThat(timeAsMilliseconds, CoreMatchers.not(0))
        MatcherAssert.assertThat(timeAsMilliseconds, IsInstanceOf(Long::class.java))
    }
}