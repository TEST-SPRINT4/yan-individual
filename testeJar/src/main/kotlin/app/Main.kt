package app

import AppMonitoramento

open class Main {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            AppMonitoramento().iniciar();
        }
    }
}