
import kotlinx.coroutines.yield
import java.math.BigInteger

object Fibonacci {
    suspend fun take(index: Int): BigInteger {
        if (index == 0) {
            return (0).toBigInteger()
        }
        if (index < 0) {
            throw IndexOutOfBoundsException(index.toString())
        }

        var n0 = (0L).toBigInteger()
        var n1 = (1L).toBigInteger()
        for (i in 3..index) {
            yield()
            val n2 = n0 + n1
            n0=n1
            n1=n2


        }
        return n1
    }
}