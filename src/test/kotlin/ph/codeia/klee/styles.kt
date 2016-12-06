package ph.codeia.klee

import ph.codeia.klee.style.Rendition

import org.junit.Test
import org.junit.Assert.*

class StyledStrings {
    @Test
    fun visual_test() {
        "echo sugar tests".echo
        null.echo()
        1234.echo("labelled:")
        "ERROR TEXT".error.echo
        "Information".info.echo
        "debug".debug.echo
        "multiple flags".style(Rendition.RED.bg, Rendition.YELLOW, Rendition.BOLD, Rendition.INVERSE).echo
        "cyan bg intense underline".echo("label wth custom style".style(Rendition.CYAN.bgIntense, Rendition.UNDERLINE))
        print("open".style(Rendition.PURPLE, closed = false))
        print(" the style should bleed until ")
        println("here".style(Rendition.RESET))
    }
}
