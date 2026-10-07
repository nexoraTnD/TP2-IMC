package com.example.calculimc

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var editTextPoids: EditText
    private lateinit var editTextTaille: EditText
    private lateinit var buttonCalculer: Button
    private lateinit var buttonEffacer: Button
    private lateinit var textViewImc: TextView
    private lateinit var textViewCategorie: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextPoids = findViewById(R.id.editTextPoids)
        editTextTaille = findViewById(R.id.editTextTaille)
        buttonCalculer = findViewById(R.id.buttonCalculer)
        buttonEffacer = findViewById(R.id.buttonEffacer)
        textViewImc = findViewById(R.id.textViewImc)
        textViewCategorie = findViewById(R.id.textViewCategorie)

        buttonCalculer.setOnClickListener {
            calculerIMC()
        }

        buttonEffacer.setOnClickListener {
            effacer()
        }
    }

    private fun calculerIMC() {
        val poidsText = editTextPoids.text.toString().trim()
        val tailleText = editTextTaille.text.toString().trim()

        if (poidsText.isEmpty() || tailleText.isEmpty()) {
            Toast.makeText(
                this,
                getString(R.string.erreur_champs_vides),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val poids = poidsText.toDoubleOrNull()
        val taille = tailleText.toDoubleOrNull()

        if (poids == null || taille == null) {
            Toast.makeText(
                this,
                getString(R.string.erreur_valeurs),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (poids <= 0 || taille <= 0) {
            Toast.makeText(
                this,
                getString(R.string.erreur_positif),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val imc = poids / (taille * taille)

        textViewImc.text = String.format(
            Locale.US,
            getString(R.string.resultat_imc),
            imc
        )

        if (imc < 18.5) {
            textViewCategorie.text = getString(R.string.insuffisance)
            textViewCategorie.setTextColor(Color.rgb(255, 152, 0))
        } else if (imc < 25) {
            textViewCategorie.text = getString(R.string.normale)
            textViewCategorie.setTextColor(Color.rgb(0, 150, 0))
        } else if (imc < 30) {
            textViewCategorie.text = getString(R.string.surpoids)
            textViewCategorie.setTextColor(Color.rgb(255, 152, 0))
        } else if (imc < 35) {
            textViewCategorie.text = getString(R.string.obesite_moderee)
            textViewCategorie.setTextColor(Color.RED)
        } else if (imc < 40) {
            textViewCategorie.text = getString(R.string.obesite_severe)
            textViewCategorie.setTextColor(Color.RED)
        } else {
            textViewCategorie.text = getString(R.string.obesite_morbide)
            textViewCategorie.setTextColor(Color.rgb(139, 0, 0))
        }
    }

    private fun effacer() {
        editTextPoids.text.clear()
        editTextTaille.text.clear()

        textViewImc.text = getString(R.string.resultat_initial)
        textViewCategorie.text = getString(R.string.categorie_initiale)
        textViewCategorie.setTextColor(Color.BLACK)

        editTextPoids.requestFocus()
    }
}
