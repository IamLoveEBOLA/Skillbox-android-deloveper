import kotlinx.coroutines.*

suspend fun main() {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Handle [ $throwable ] in CoroutineExceptionHandler")
    }

    runBlocking {
        val mainScope = CoroutineScope(Dispatchers.Default + exceptionHandler)
        mainScope.launch {
            launch {
                try {
                    withTimeout(10000) {
                        println(Fibonacci.take(286))

                    }

                } catch (e: TimeoutCancellationException) {

                    println(TimeOut())


                }

            }
            launch {
                try {
                    withTimeout(5000) {
                        println(Fibonacci.take(10000))

                    }

                } catch (e: TimeoutCancellationException) {
                    println(TimeOut())


                }



            }


        }
        while (mainScope.isActive) {
            println(".")
            delay(500)

        }



    }

}


class TimeOut : Throwable(message = "Too long time")











