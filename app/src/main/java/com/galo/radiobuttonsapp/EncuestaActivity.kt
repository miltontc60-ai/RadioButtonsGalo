package com.galo.radiobuttonsapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText

/**
 * Ejercicio 1: Encuesta de Satisfacción
 * Desarrollado por: Galo
 * Gestiona la calificación del usuario utilizando RadioButtons con retroalimentación y resumen.
 */
class EncuestaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encuesta)

        val btnVolver = findViewById<Button>(R.id.btnGaloVolverEncuesta)
        val rgNivel = findViewById<RadioGroup>(R.id.rgGaloNivel)
        val etComentarios = findViewById<TextInputEditText>(R.id.etGaloComentario)
        val btnEnviar = findViewById<Button>(R.id.btnGaloEnviarEncuesta)
        val btnLimpiar = findViewById<Button>(R.id.btnGaloLimpiarEncuesta)

        val cardResumen = findViewById<MaterialCardView>(R.id.cardGaloResumen)
        val tvCalificacion = findViewById<TextView>(R.id.tvGaloResCalificacion)
        val tvMensaje = findViewById<TextView>(R.id.tvGaloMensajeRetro)
        val tvDetalle = findViewById<TextView>(R.id.tvGaloComentarioDetalle)

        btnVolver.setOnClickListener {
            finish()
        }

        btnEnviar.setOnClickListener {
            val seleccionado = rgNivel.checkedRadioButtonId
            if (seleccionado == -1) {
                Toast.makeText(this, "Selecciona una calificación del servicio", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nombreCalificacion: String
            val mensajeRetro: String

            when (seleccionado) {
                R.id.rbGaloExcelente -> {
                    nombreCalificacion = "Excelente (⭐⭐⭐⭐⭐)"
                    mensajeRetro = "¡Muchas gracias! Es gratificante saber que nuestro servicio superó tus expectativas."
                }
                R.id.rbGaloBueno -> {
                    nombreCalificacion = "Bueno (⭐⭐⭐⭐)"
                    mensajeRetro = "¡Gracias por tus comentarios! Nos alegra haber brindado una atención satisfactoria."
                }
                R.id.rbGaloRegular -> {
                    nombreCalificacion = "Regular (⭐⭐⭐)"
                    mensajeRetro = "Valoramos tu honestidad. Trabajaremos continuamente en mejorar las áreas señaladas."
                }
                R.id.rbGaloMalo -> {
                    nombreCalificacion = "Malo (⭐)"
                    mensajeRetro = "Sentimos mucho la mala experiencia. Tomaremos medidas correctivas inmediatas."
                }
                else -> {
                    nombreCalificacion = "Sin definir"
                    mensajeRetro = ""
                }
            }

            val notas = etComentarios.text?.toString()?.trim()

            tvCalificacion.text = "Calificación elegida: $nombreCalificacion"
            tvMensaje.text = mensajeRetro

            if (!notas.isNullOrEmpty()) {
                tvDetalle.text = "Comentarios del usuario: \"$notas\""
                tvDetalle.visibility = View.VISIBLE
            } else {
                tvDetalle.text = "Comentarios: Ninguno proporcionado."
                tvDetalle.visibility = View.VISIBLE
            }

            cardResumen.visibility = View.VISIBLE
            Toast.makeText(this, "Evaluación registrada", Toast.LENGTH_SHORT).show()
        }

        btnLimpiar.setOnClickListener {
            rgNivel.clearCheck()
            etComentarios.text?.clear()
            cardResumen.visibility = View.GONE
            Toast.makeText(this, "Encuesta reiniciada", Toast.LENGTH_SHORT).show()
        }
    }
}
