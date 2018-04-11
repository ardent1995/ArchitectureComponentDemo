package se.indpro.architecturecomponentdemo.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "word_table")
class Word(
        @PrimaryKey
        @NotNull
        @ColumnInfo(name = "word")
        private var word : String) {
    fun getWord() = word
}