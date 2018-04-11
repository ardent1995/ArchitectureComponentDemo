package se.indpro.architecturecomponentdemo

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewWordActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }

    private val mEditWordView:EditText by lazy { findViewById<EditText>(R.id.edit_word) }
    private val button:Button by lazy { findViewById<Button>(R.id.button_save) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        button.setOnClickListener {
            val replyIntent = Intent()
            if(TextUtils.isEmpty(mEditWordView.text)){
                setResult(RESULT_CANCELED,replyIntent)
            }else{
                val word = mEditWordView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY,word)
                setResult(RESULT_OK,replyIntent)
            }
            finish()
        }
    }
}
