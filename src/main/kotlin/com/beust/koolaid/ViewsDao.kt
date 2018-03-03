package com.beust.koolaid

import java.net.URI
import java.sql.Connection
import java.sql.DriverManager

interface ViewsDao {
    fun getViewCountAndIncrement(): Int
}

class ViewsDaoPostgres : ViewsDao {
    private val conn: Connection
    init {
        Class.forName("org.postgresql.Driver")

        // postgres://{user}:{password}@{hostname}:{port}/{database-name}
        val databaseUrl = System.getenv("DATABASE_URL")
        val dbUri = URI(databaseUrl ?: "postgres://guest:guest@localhost:5432/demo")

        val username = dbUri.userInfo.split(":")[0]
        val password = dbUri.userInfo.split(":")[1]
        val dbUrl = "jdbc:postgresql://" + dbUri.host + ':' + dbUri.port + dbUri.path + "?sslmode=require"

        println("DATABASE_URL: $databaseUrl")
        println("dbUrl: $dbUrl")
        conn = DriverManager.getConnection(dbUrl, username, password)

//        val url = System.getenv("JDBC_DATABASE_URL") ?: "jdbc:postgresql:demo"
//        val user = System.getenv("JDBC_DATABASE_USER") ?: "guest"
//        val password = System.getenv("JDBC_DATABASE_PASSWORD") ?: "guest"
//        conn = DriverManager.getConnection(url, user, password)
    }

    override fun getViewCountAndIncrement(): Int {
        val result = count
        count = result + 1
        return result
    }


    private var count: Int
        set(value) {
            conn.createStatement().let { st ->
                st.execute("UPDATE views SET count=$value")
                st.close()
            }
        }

        get() {
            var result = -1
            conn.createStatement().let { st ->
                val rs = st.executeQuery("SELECT * FROM views")
                val hasNext = rs.next()
                if (hasNext) {
                    result = rs.getInt(1)
                } else {
                    throw IllegalArgumentException("No views found")
                }
                rs.close()
                st.close()
            }
            return result
        }

}