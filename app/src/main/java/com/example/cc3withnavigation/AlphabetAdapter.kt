package com.example.cc3withnavigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class AlphabetAdapter(private val alphabets: List<Char>) :
    RecyclerView.Adapter<AlphabetAdapter.AlphabetViewHolder>() {

    class AlphabetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val alphabetTextView: TextView = view.findViewById(R.id.alphabetTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlphabetViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_alphabet, parent, false)
        return AlphabetViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlphabetViewHolder, position: Int) {
        val alphabet = alphabets[position]
        holder.alphabetTextView.text = alphabet.toString()

        holder.itemView.setOnClickListener {
            val bundle = bundleOf("alphabet" to alphabet.toString())
            it.findNavController()
                .navigate(R.id.action_alphabetsFragment_to_wordsFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return alphabets.size
    }
}
