package dev.junron.pyslice

operator fun String.get(_start: Int? = null, _end: Int? = null, step: Int = 1): String {
    val start = (_start ?: 0) % length
    val end = ((_end ?: length) - 1) % length
    if (start == end + 1) return get(start).toString()
    if (start < end && step == 1) return substring(start..end)
    return substring(this,
        if (step > 0) start..end step step
        else end downTo start step -step
    )
}

operator fun <T> List<T>.get(_start: Int? = null, _end: Int? = null, step: Int = 1): List<T> {
    val start = (_start ?: 0) % size
    val end = ((_end ?: size) - 1) % size
    if (start < end && step == 1) return subList(start, end)
    return subList(this,
        if (step > 0) start..end step step
        else end downTo start step -step
    )
}

operator fun <T> Iterable<T>.unaryMinus() = reversed()
operator fun  CharSequence.unaryMinus() = reversed()

private fun substring(string: String, range: IntProgression) = range.map { string[it] }.joinToString("")

private fun <T> subList(list: List<T>, range: IntProgression): List<T> = range.map { list[it] }
