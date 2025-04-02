package com.example.calllengthchecker

import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class CallCheckHandlerTest {

    private lateinit var checker: CallStateChecker
    private lateinit var storage: CallStateStorage
    private lateinit var handler: CallCheckHandler

    @Before
    fun setUp() {
        checker = mockk()
        storage = mockk(relaxed = true)
        handler = CallCheckHandler(checker, storage)
    }

    @Test
    fun `when call just started then should save active state with timestamp`() {
        every { checker.isInCall() } returns true
        every { storage.getCallActive() } returns false

        handler.checkAndStoreCallState()

        verify { storage.saveCallState(true, any()) }

    }

    @Test
    fun `when call ended then should save inactive state and zero timestamp`() {
        every { checker.isInCall() } returns false
        every { storage.getCallActive() } returns true

        handler.checkAndStoreCallState()

        verify { storage.saveCallState(false, 0L) }
        assertEquals(0L, storage.getCallTimestamp())
    }

    @Test
    fun `when no change in state then should not save anything`() {
        every { checker.isInCall() } returns false
        every { storage.getCallActive() } returns false

        handler.checkAndStoreCallState()

        verify(exactly = 0) { storage.saveCallState(any(), any()) }
    }

    @Test
    fun `when call still active then should not re-save state`() {
        every { checker.isInCall() } returns true
        every { storage.getCallActive() } returns true

        handler.checkAndStoreCallState()

        verify(exactly = 0) { storage.saveCallState(any(), any()) }
    }
}