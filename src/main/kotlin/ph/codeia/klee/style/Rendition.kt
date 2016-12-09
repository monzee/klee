package ph.codeia.klee.style

sealed class Rendition(val code: Int) {

    open fun dump(sb: StringBuilder) {
        sb.append(code)
    }

    open operator fun plus(other: Rendition): Aggregate = Aggregate(this) + other

    class Aggregate(starter: Rendition) : Rendition(-1) {
        private val elems = mutableSetOf<Rendition>()

        init {
            elems += starter
        }

        override fun dump(sb: StringBuilder) {
            elems.forEach {
                it.dump(sb)
                sb.append(';')
            }
            sb.deleteCharAt(sb.lastIndex)
        }

        override fun plus(other: Rendition): Aggregate {
            when (other) {
                is Aggregate -> elems += other.elems
                else -> elems += other
            }
            return this
        }
    }

    object RESET : Rendition(0)

    // only these 4 seem to work in vte (gnome) terminals. excluded styles:
    // faint, blink{slow,fast}, conceal, crossed-out and a dozen others
    object BOLD : Rendition(1)
    object ITALIC : Rendition(3)
    object UNDERLINE : Rendition(4)
    object INVERSE : Rendition(7)

    class Color private constructor(code: Byte) : Rendition(code + 30) {
        constructor(code: Int) : this(code.coerceIn(0, 7).toByte())
        val intense: Color by lazy { Color((code + 60).toByte()) }
        val bg: Color by lazy { Color((code + 10).toByte()) }
        val bgIntense: Color by lazy { Color((code + 70).toByte()) }
    }

    companion object {
        val GRAY = Color(0)
        val RED = Color(1)
        val GREEN = Color(2)
        val YELLOW = Color(3)
        val BLUE = Color(4)
        val MAGENTA = Color(5)
        val CYAN = Color(6)
        val WHITE = Color(7)
    }
}
