import java.util.Stack

class Stack<T> {
    private var stack = Stack<T>()

    fun push(item: T) {
        stack.add(item)
    }

    fun pop(): T? {
        return if (stack.isEmpty()) null
        else stack.pop()
    }

    fun clear() {
        return stack.clear()
    }

    fun size(): Int {
        return stack.size
    }
}