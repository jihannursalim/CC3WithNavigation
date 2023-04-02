package com.example.cc3withnavigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class WordsFragment : Fragment(R.layout.fragment_words) {
    private lateinit var alphabet: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        alphabet = requireArguments().getString("alphabet")!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val wordsTextView = view.findViewById<TextView>(R.id.wordsTextView)
//        wordsTextView.text = generateWords(alphabet)

        val wordsTextView = view.findViewById<TextView>(R.id.wordsTextView)
        val words = wordsTextView.text.toString().split("\n")

        wordsTextView.movementMethod = LinkMovementMethod.getInstance()

        for (word in words) {
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    val query = Uri.encode(word, "utf-8")
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=$query"))
                    startActivity(intent)
                }
            }

            val start = wordsTextView.text.indexOf(word)
            val end = start + word.length
            val spannableString = SpannableString(wordsTextView.text)
            spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            wordsTextView.text = spannableString
        }

    }

    private fun generateWords(alphabet: String): String {
        val words = mutableListOf<String>()
        // Generate some words that start with the specified alphabet
        when (alphabet) {
            "A" -> words.addAll(listOf("Apple", "Ant", "Airplane"))
            "B" -> words.addAll(listOf("Banana", "Ball", "Butterfly"))
            "C" -> words.addAll(listOf("Cat", "Car", "Candy"))
            "D" -> words.addAll(listOf("Dog", "Duck", "Dolphin"))
            "E" -> words.addAll(listOf("Elephant", "Egg", "Ear"))
            "F" -> words.addAll(listOf("Fish", "Flower", "Frog"))
            "G" -> words.addAll(listOf("Giraffe", "Guitar", "Grass"))
            "H" -> words.addAll(listOf("House", "Horse", "Hat"))
            "I" -> words.addAll(listOf("Ice cream", "Iguana", "Island"))
            "J" -> words.addAll(listOf("Jellyfish", "Jeans", "Jacket"))
        }
        return words.joinToString(", ")
    }
}
