package com.example.calllengthchecker

class CallCheckHandler(
    private val checker: CallStateChecker,
    private val storage: CallStateStorage
) {
    fun checkAndStoreCallState() {
        val wasActive = storage.getCallActive()
        val inCall = checker.isInCall()

        if (inCall && !wasActive) {
            storage.saveCallState(true, System.currentTimeMillis())
        } else if (!inCall && wasActive) {
            storage.saveCallState(false, 0L)
        }
    }
}