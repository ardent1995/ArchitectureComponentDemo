package se.indpro.architecturecomponentdemo.adapters

import android.arch.lifecycle.LiveData
import android.content.Context
import android.provider.UserDictionary
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.content_main.view.*
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import se.indpro.architecturecomponentdemo.R
import se.indpro.architecturecomponentdemo.room.Word

class WordListAdapter(context : Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val wordItemView : TextView by lazy { itemView.findViewById<TextView>(R.id.textView) }
    }

    private val mInflater: LayoutInflater by lazy { LayoutInflater.from(context) }
    private var mWords : List<Word>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        var itemView = mInflater.inflate(R.layout.recyclerview_item,parent,false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if(mWords != null){
            var current = mWords?.get(position)
            holder.wordItemView.setText(current?.getWord())
        }else{
            holder.wordItemView.setText("No Word")
        }
    }

    override fun getItemCount(): Int {
        if(mWords != null){
            return mWords!!.size
        }else {
            return 0
        }
    }

    fun setWords(words:List<Word>){
        mWords = words
        notifyDataSetChanged()
    }
}