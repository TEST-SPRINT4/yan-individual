import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UsuarioRepositorio {
    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciar() {
        if (Conexao.local) {
            jdbcTemplate = Conexao.jdbcTemplate!!
        } else {
            jdbcTemplate = Conexao.jdbcTemplateServer!!
        }
    }
    fun logar(email:String, senha:String):Boolean {
        val consulta = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) AS count FROM Funcionario WHERE email = ? AND senha = ?;",
            arrayOf(email, senha),
            Int::class.java
        );
        return consulta == 1
    }
}