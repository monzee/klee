package ph.codeia.klee

import ph.codeia.klee.style.Rendition
import ph.codeia.klee.style.Styled

fun <T> T?.echo(label: CharSequence? = null): T? = this.apply {
    when (label) {
        is Styled -> print("${label.toString()} ")
        is CharSequence -> print("${label.info} ")
    }
    println(this)
}

val <T> T.echo: T get() = echo()!!

val CharSequence.error: Styled get() = Styled(this, Rendition.RED.intense)
val CharSequence.info: Styled get() = Styled(this, Rendition.GREEN.intense)
val CharSequence.debug: Styled get() = Styled(this, Rendition.GRAY.intense)

fun CharSequence.style(vararg flags: Rendition, closed: Boolean = true) =
        Styled(this, *flags, closed = closed)
