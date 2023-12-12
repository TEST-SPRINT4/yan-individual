import java.io.File

object Python {

    lateinit var pythonProcesses:Process;

    fun criarScript():String {
        val codigoPython = """
            
            COLOCAR O CÓDIGO PYTHON AQ

    """.trimIndent();

        val nomeArquivoPy = "NOME.py";
        File(nomeArquivoPy).writeText(codigoPython);
        Thread.sleep(2 * 1000L);

        return nomeArquivoPy;
    }
    fun executarScript(arquivo:String) {
        val pythonProcess:Process = Runtime.getRuntime().exec("python3 $arquivo");
        pythonProcesses = pythonProcess;
    }
    fun pararScript() {
        pythonProcesses.destroyForcibly();
    }
}