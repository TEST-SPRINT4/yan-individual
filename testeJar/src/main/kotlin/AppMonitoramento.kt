import javax.swing.JOptionPane
import com.github.britooo.looca.api.core.Looca
import kotlin.system.exitProcess

class AppMonitoramento {
    fun iniciar(){
        val repositorioUsuario = UsuarioRepositorio()
        val looca = Looca()
        repositorioUsuario.iniciar()
        enviarMensagem("Bem vindo ao sistema da T.E.S.T.!")
        while (true){
            while (true){
                val email = JOptionPane.showInputDialog("Digite o seu email:")
                val senha = JOptionPane.showInputDialog("Digite a sua senha:")
                val login = repositorioUsuario.logar(email,senha)
                if (login){
                    enviarMensagem("Login efetuado com sucesso!")
                    break
                } else {
                    enviarMensagem("Email ou senha incorretos. Tente novamente.")
                }
            }
            val SO = looca.sistema.sistemaOperacional
            val CPU = looca.processador.nome
            val RAM = looca.memoria.total.toDouble() / 1000000000
            val DISCO = looca.grupoDeDiscos.tamanhoTotal.toDouble() / 1000000000

            enviarMensagem("Especificações do seu computador:\r\n" +
                    "SO: ${SO}\r\n" +
                    "CPU: ${CPU}\r\n" +
                    "RAM: %.2f GB\r\n".format(RAM) +
                    "DISCO: %.2f GB".format(DISCO))

            val arquivo = Python.criarScript();
            Python.executarScript(arquivo)
            val escolha:Int = JOptionPane.showInputDialog("Digite...\r\n" +
                    "1. Encerrar Programa").toInt()
            when(escolha) {
                1 -> {
                    enviarMensagem("Encerrando Programa...")
                    Python.pararScript()
                    exitProcess(0)
                }
                else -> {
                    enviarMensagem("Opção inválida. Por favor, escolha uma opção válida.")
                }
            }
        }
    }
    fun enviarMensagem(mensagem:String){
        JOptionPane.showMessageDialog(null,mensagem)
    }
}