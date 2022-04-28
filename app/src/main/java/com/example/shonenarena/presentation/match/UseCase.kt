package com.example.shonenarena.presentation.match

abstract class UseCase<in Request, Response>() {
    internal abstract suspend  fun getRawSuspendFunction(params: Request): Response

    suspend fun call(request: Request): Response = getRawSuspendFunction(request)
}