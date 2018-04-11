package se.indpro.architecturecomponentdemo.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import se.indpro.architecturecomponentdemo.room.Word
import se.indpro.architecturecomponentdemo.room.WordDao
import se.indpro.architecturecomponentdemo.room.WordRoomDatabase

class WordRepository(application: Application) {
    private val mWordDao: WordDao
    private val mAllWords: LiveData<List<Word>>
    init {
        val db = WordRoomDatabase.getDatabase(application)
        mWordDao = db.getWordDao()
        mAllWords = mWordDao.getAllWords()
    }

    fun getAllWords() = mAllWords
    fun insert(word : Word){
        insertAsyncTask(mWordDao).execute(word)
    }

    companion object {
        private class insertAsyncTask(private val dao: WordDao): AsyncTask<Word, Unit, Unit>(){
            override fun doInBackground(vararg p0: Word?) {
                dao.insert(p0[0]!!)
                return
            }
        }
    }
}