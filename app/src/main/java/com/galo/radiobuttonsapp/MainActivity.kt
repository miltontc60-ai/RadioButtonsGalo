package com.galo.radiobuttonsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

/**
 * Actividad Principal - Panel de Navegación de Ejercicios
 * Curso: 07_A Aplicaciones Móviles - Semana 2: RadioButtons
 * Alumno: Galo
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a los componentes de la interfaz
        val btnEncuesta = findViewById<Button>(R.id.btnGaloAbrirEncuesta)
        val cardEncuesta = findViewById<MaterialCardView>(R.id.cardGaloEncuesta)

        val btnTema = findViewById<Button>(R.id.btnGaloAbrirTema)
        val cardTema = findViewById<MaterialCardView>(R.id.cardGaloTema)

        val btnQuiz = findViewById<Button>(R.id.btnGaloAbrirQuiz)
        val cardQuiz = findViewById<MaterialCardView>(R.id.cardGaloQuiz)

        val btnPropinas = findViewById<Button>(R.id.btnGaloAbrirPropinas)
        val cardPropinas = findViewById<MaterialCardView>(R.id.cardGaloPropinas)

        val btnConversor = findViewById<Button>(R.id.btnGaloAbrirConversor)
        val cardConversor = findViewById<MaterialCardView>(R.id.cardGaloConversor)

        // Navegación a cada actividad
        btnEncuesta.setOnClickListener { irA(EncuestaActivity::class.java) }
        cardEncuesta.setOnClickListener { irA(EncuestaActivity::class.java) }

        btnTema.setOnClickListener { irA(SelectorTemaActivity::class.java) }
        cardTema.setOnClickListener { irA(SelectorTemaActivity::class.java) }

        btnQuiz.setOnClickListener { irA(QuizActivity::class.java) }
        cardQuiz.setOnClickListener { irA(QuizActivity::class.java) }

        btnPropinas.setOnClickListener { irA(PropinasActivity::class.java) }
        cardPropinas.setOnClickListener { irA(PropinasActivity::class.java) }

        btnConversor.setOnClickListener { irA(ConversorActivity::class.java) }
        cardConversor.setOnClickListener { irA(ConversorActivity::class.java) }
    }

    private fun irA(destino: Class<*>) {
        startActivity(Intent(this, destino))
    }
}
