package ph.codeia.klee.style

const val ESCAPE = '\u001b'

class Styled(
        val text: CharSequence,
        vararg flags: Rendition,
        val closed: Boolean = true
) : CharSequence by text {
    val ansiString: String by lazy {
        buildString({
            append("$ESCAPE[")
            flags.reduce(Rendition::plus).dump(this)
            append("m")
            append(text)
            if (closed) {
                append("$ESCAPE[0m")
            }
        })
    }

    override fun toString() = ansiString
}
