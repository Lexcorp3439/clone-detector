package org.jetbrains.research.postgresql

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.research.detectors.SWindow
import java.util.*

object DataConnector {
    object Clone : IntIdTable() {
        val uniq = integer("uniq").uniqueIndex()    // Column<Int>
        val file = varchar("file", length = 128)
        val from = integer("from")                  // Column<Int>
        val to = integer("to")                      // Column<Int>
        val hash = double("hash")
    }

    var db: Database = Database.connect(
        "jdbc:postgresql://localhost:5432/clones", driver = "org.postgresql.Driver",
        user = "postgres", password = "Lexa3439"
    )

    fun insert(sw: SWindow) {
        insert(Collections.singletonList(sw))
    }

    fun insert(list: List<SWindow>) {
        transaction {
            SchemaUtils.create(Clone)
            for (sw in list) {
                DataConnector.Clone.insert {
                    it[uniq] = sw.uniq
                    it[file] = sw.fileName
                    it[from] = sw.from
                    it[to]   = sw.to
                    it[hash] = sw.hash
                }
            }
        }
    }

    fun getBy(cond: SqlExpressionBuilder.() -> Op<Boolean>): List<SWindow> {
        val swList = mutableListOf<SWindow>()
        transaction {
            SchemaUtils.create(Clone)
            DataConnector.Clone.select(cond).orderBy(DataConnector.Clone.uniq).forEach {
                val uniq = it[DataConnector.Clone.uniq]
                val file = it[DataConnector.Clone.file]
                val from = it[DataConnector.Clone.from]
                val to   = it[DataConnector.Clone.to]
                val hash = it[DataConnector.Clone.hash]
                swList.add(SWindow(uniq, file, from, to, hash))
            }
        }
        return swList
    }

    fun getByFile(file: String): List<SWindow> {
        return getBy { DataConnector.Clone.file eq file }
    }

    fun getByUniq(uniq: Int): List<SWindow> {
        return getBy { DataConnector.Clone.uniq eq uniq }
    }

    fun getByHash(hash: Double): List<SWindow> {
        return getBy { DataConnector.Clone.hash eq hash }
    }

    fun getAll(): List<SWindow> {
        val list = mutableListOf<SWindow>()
        transaction {
            SchemaUtils.create(Clone)
            DataConnector.Clone.selectAll().forEach {
                val uniq = it[DataConnector.Clone.uniq]
                val file = it[DataConnector.Clone.file]
                val from = it[DataConnector.Clone.from]
                val to = it[DataConnector.Clone.to]
                val hash = it[DataConnector.Clone.hash]
                list.add(SWindow(uniq, file, from, to, hash))
            }
        }
        return list
    }
}