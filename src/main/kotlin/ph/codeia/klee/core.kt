package ph.codeia.klee

import ph.codeia.klee.style.Rendition
import ph.codeia.klee.style.Styled

fun <T> T?.echo(label: CharSequence? = null): T? = this.apply {
    when (label) {
        is Styled -> println("${label.toString()} ${indentBy(this, label.length)}")
        is CharSequence -> println("${label.info} ${indentBy(this, label.length)}")
        else -> println(this)
    }
}

val <T> T.echo: T get() = echo()!!

val CharSequence.error: Styled get() = Styled(this, Rendition.RED.intense)
val CharSequence.alert: Styled get() = Styled(this, Rendition.RED)
val CharSequence.warn: Styled get() = Styled(this, Rendition.YELLOW)
val CharSequence.info: Styled get() = Styled(this, Rendition.GREEN.intense)
val CharSequence.debug: Styled get() = Styled(this, Rendition.GRAY.intense)

fun CharSequence.style(vararg flags: Rendition, closed: Boolean = true) =
        Styled(this, *flags, closed = closed)

private fun indentBy(t: Any?, indent: Int): String? = t
        ?.toString()
        ?.replace("\n", "\n${".".repeat(indent).debug} ")
