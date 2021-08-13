package com.dindinn.app

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing
import org.mockito.stubbing.Stubber

inline fun <reified T> mock(): T{
    return Mockito.mock(T::class.java)
}

inline fun  <reified T> whenever(methodCall: T): OngoingStubbing<T>{
    return Mockito.`when`(methodCall)
}


fun <T> Stubber.whenever(mock: T): T{
    return `when`(mock)
}


infix fun <T> OngoingStubbing<T>.doReturn(t: T): OngoingStubbing<T> = thenReturn(t)

inline fun <reified T> anyNotNull(): T{
    return Mockito.any(T::class.java)
}
inline fun <reified T> anyOrNull(): T{
    return Mockito.any()
}

fun <T> eqMock(value: T): T = Mockito.eq(value) ?: value

