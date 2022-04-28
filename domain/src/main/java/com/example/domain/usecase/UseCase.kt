package com.example.domain.usecase

abstract class UseCase<in Request, Response>() {
    internal abstract suspend  fun getRawSuspendFunction(params: Request): Response

    suspend fun call(request: Request): Response = getRawSuspendFunction(request)
}