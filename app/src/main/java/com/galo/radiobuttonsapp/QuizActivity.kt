package com.galo.radiobuttonsapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

/**
 * Ejercicio 3: Quiz Simple
 * Desarrollado por: Galo
 * Evalúa respuestas de opción múltiple usando RadioButtons y muestra el puntaje con desglose.
 */
class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val btnVolver = findViewById<Button>(R.id.btnGaloVolverQuiz)
        val rgP1 = findViewById<RadioGroup>(R.id.rgGaloP1)
        val rgP2 = findViewById<RadioGroup>(R.id.rgGaloP2)
        val rgP3 = findViewById<RadioGroup>(R.id.rgGaloP3)
        val rgP4 = findViewById<RadioGroup>(R.id.rgGaloP4)

        val btnEvaluar = findViewById<Button>(R.id.btnGaloEvaluarQuiz)
        val btnReiniciar = findViewById<Button>(R.id.btnGaloReiniciarQuiz)

        val cardResultado = findViewById<MaterialCardView>(R.id.cardGaloResultadoQuiz)
        val tvPuntaje = findViewById<TextView>(R.id.tvGaloPuntajeQuiz)
        val tvDetalle = findViewById<TextView>(R.id.tvGaloDetalleRespuestas)

        btnVolver.setOnClickListener {
            finish()
        }

        btnEvaluar.setOnClickListener {
            val r1 = rgP1.checkedRadioButtonId
            val r2 = rgP2.checkedRadioButtonId
            val r3 = rgP3.checkedRadioButtonId
            val r4 = rgP4.checkedRadioButtonId

            if (r1 == -1 || r2 == -1 || r3 == -1 || r4 == -1) {
                Toast.makeText(this, "Por favor contesta todas las preguntas", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var aciertos = 0
            val totalPreguntas = 4
            val revision = StringBuilder()

            // Pregunta 1: android:checked="true"
            if (r1 == R.id.g_p1_op1) {
                aciertos++
                revision.append("✔ 1. Correcto: android:checked=\"true\"\n")
            } else {
                revision.append("✘ 1. Incorrecto (Correcta: android:checked=\"true\")\n")
            }

            // Pregunta 2: setOnCheckedChangeListener
            if (r2 == R.id.g_p2_op2) {
                aciertos++
                revision.append("✔ 2. Correcto: setOnCheckedChangeListener\n")
            } else {
                revision.append("✘ 2. Incorrecto (Correcta: setOnCheckedChangeListener)\n")
            }

            // Pregunta 3: strings.xml
            if (r3 == R.id.g_p3_op1) {
                aciertos++
                revision.append("✔ 3. Correcto: strings.xml\n")
            } else {
                revision.append("✘ 3. Incorrecto (Correcta: strings.xml)\n")
            }

            // Pregunta 4: setContentView()
            if (r4 == R.id.g_p4_op3) {
                aciertos++
                revision.append("✔ 4. Correcto: setContentView()")
            } else {
                revision.append("✘ 4. Incorrecto (Correcta: setContentView())")
            }

            val porcentaje = (aciertos * 100) / totalPreguntas
            val calificacionTexto = when (aciertos) {
                4 -> "¡Excelente puntuación perfecta! 🎯"
                3 -> "¡Muy bien logrado! 👍"
                2 -> "Regular, puedes seguir repasando. 📖"
                else -> "Insuficiente, te recomendamos estudiar más. 📝"
            }

            tvPuntaje.text = "Calificación: $aciertos de $totalPreguntas ($porcentaje%) - $calificacionTexto"
            tvDetalle.text = revision.toString()
            cardResultado.visibility = View.VISIBLE

            Toast.makeText(this, "Quiz calificado", Toast.LENGTH_SHORT).show()
        }

        btnReiniciar.setOnClickListener {
            rgP1.clearCheck()
            rgP2.clearCheck()
            rgP3.clearCheck()
            rgP4.clearCheck()
            cardResultado.visibility = View.GONE
            Toast.makeText(this, "Cuestionario reseteado", Toast.LENGTH_SHORT).show()
        }
    }
}
