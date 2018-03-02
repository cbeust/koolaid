package com.beust.koolaid

import java.sql.Connection
import java.sql.DriverManager

interface ViewsDao {
    fun getViewCountAndIncrement(): Int
}

class ViewsDaoPostgres : ViewsDao {
    private val conn: Connection
    init {
        Class.forName("org.postgresql.Driver")
        conn = DriverManager.getConnection("jdbc:postgresql:demo", "guest", "guest")
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