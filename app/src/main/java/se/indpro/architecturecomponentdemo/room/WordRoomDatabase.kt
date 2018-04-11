package se.indpro.architecturecomponentdemo.room

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask

@Database(entities = arrayOf(Word::class),version = 1)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun getWordDao() : WordDao
    companion object {
        @Volatile
        private var INSTANCE : WordRoomDatabase? = null
        fun getDatabase(context: Context) =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        WordRoomDatabase::class.java, "word_database")
                        .addCallback(sRoomDatabaseCallback)
                        .build()

        private val sRoomDatabaseCallback = object : RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }

        private class PopulateDbAsync(db: WordRoomDatabase) : AsyncTask<Unit,Unit,Unit>(){
            private val dao:WordDao by lazy { db.getWordDao() }
            override fun doInBackground(vararg p0: Unit?) {
                dao.deleteAll()
                val word = Word("Hello")
                dao.insert(word)
                val word2 = Word("World")
                dao.insert(word2)
                return
            }
        }
    }
}