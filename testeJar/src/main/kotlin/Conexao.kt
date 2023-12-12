import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate

object Conexao {
    var user = "root";
    var senha = "yanyan";

    var userServer = "sa";
    var senhaServer = "senha";

    val local = true;

    var jdbcTemplate: JdbcTemplate? = null
        get() {
            if (field == null) {
                val dataSource = BasicDataSource();
                dataSource.url = "jdbc:mysql://localhost:3306/testIndividual?serverTimezone=UTC";
                dataSource.driverClassName = "com.mysql.cj.jdbc.Driver";
                dataSource.username = user;
                dataSource.password = senha;
                val novoJdbcTemplate = JdbcTemplate(dataSource);
                field = novoJdbcTemplate;
                jdbcTemplate!!.execute("use testIndividual;");
            }
            return field;
        }
    var jdbcTemplateServer: JdbcTemplate? = null
        get() {
            if (field == null) {
                val dataSourceServer = BasicDataSource();
                dataSourceServer.url = "jdbc:sqlserver://COLOCARIPDOSERVER;encrypt=false";
                dataSourceServer.driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                dataSourceServer.username = userServer;
                dataSourceServer.password = senhaServer;
                val novoJdbcTemplateServer = JdbcTemplate(dataSourceServer);
                field = novoJdbcTemplateServer;
                jdbcTemplateServer!!.execute("use test;");
            }
            return field;
        }
}