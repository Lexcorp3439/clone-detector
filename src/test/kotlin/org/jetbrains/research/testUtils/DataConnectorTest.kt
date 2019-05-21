package org.jetbrains.research.testUtils

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object DataConnectorTest {
    object Users : Table() {
        val id = integer("id").primaryKey() // Column<String>
        val name = varchar("name", length = 50) // Column<String>
        val city = integer("city") // Column<Int?>
    }

    lateinit var db: Database
    fun connect() {
        db = Database.connect(
            "jdbc:postgresql://localhost:5432/test", driver = "org.postgresql.Driver",
            user = "postgres", password = "Lexa3439"
        )
    }

    fun insert(id: Int, name: String, city: Int) {
        transaction {
            SchemaUtils.create(Users)
            DataConnectorTest.Users.insert {
                it[DataConnectorTest.Users.id] = id
                it[DataConnectorTest.Users.name] = name
                it[DataConnectorTest.Users.city] = city
            }
        }
    }

    fun get(): String {
        var nameS = ""
        transaction {
            SchemaUtils.create(Users)
            DataConnectorTest.Users.selectAll().forEach {
                println(it[DataConnectorTest.Users.name])
            }
//            Users.select {id}.forEach {
//                nameS = it[Users.name]
//            }
        }
        return nameS
    }
}