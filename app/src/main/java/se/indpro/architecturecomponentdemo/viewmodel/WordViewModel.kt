package se.indpro.architecturecomponentdemo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import se.indpro.architecturecomponentdemo.repository.WordRepository
import se.indpro.architecturecomponentdemo.room.Word

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository: WordRepository
    init {
        mRepository = WordRepository(application)
    }
    private var mAllWords = mRepository.getAllWords()
    fun getAllWords() = mAllWords
    fun insert(word: Word) {
        mRepository.insert(word)
    }
}