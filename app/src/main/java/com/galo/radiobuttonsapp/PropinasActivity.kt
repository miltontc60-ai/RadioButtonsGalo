package com.galo.radiobuttonsapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import java.util.Locale

/**
 * Ejercicio 4: Calculadora de Propinas
 * Desarrollado por: Galo
 * Calcula la propina seleccionada mediante RadioButtons (10%, 15%, 20%) y permite dividirla entre comensales.
 */
class PropinasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_propinas)

        val btnVolver = findViewById<Button>(R.id.btnGaloVolverPropinas)
        val etMonto = findViewById<TextInputEditText>(R.id.etGaloMonto)
        val rgPropina = findViewById<RadioGroup>(R.id.rgGaloPropina)
        val etPersonas = findViewById<TextInputEditText>(R.id.etGaloPersonas)
        val btnCalcular = findViewById<Button>(R.id.btnGaloCalcularPropina)
        val btnLimpiar = findViewById<Button>(R.id.btnGaloLimpiarPropina)

        val cardResultado = findViewById<MaterialCardView>(R.id.cardGaloResultadoPropina)
        val tvMontoBase = findViewById<TextView>(R.id.tvGaloMontoBase)
        val tvEtiquetaPorc = findViewById<TextView>(R.id.tvGaloEtiquetaPorc)
        val tvMontoPropina = findViewById<TextView>(R.id.tvGaloMontoPropina)
        val tvTotalPagar = findViewById<TextView>(R.id.tvGaloTotalPagar)
        val layoutPorPersona = findViewById<LinearLayout>(R.id.layoutGaloPorPersona)
        val tvEtiquetaPersonas = findViewById<TextView>(R.id.tvGaloEtiquetaPersonas)
        val tvPorPersona = findViewById<TextView>(R.id.tvGaloPorPersona)

        btnVolver.setOnClickListener {
            finish()
        }

        btnCalcular.setOnClickListener {
            val entradaMonto = etMonto.text?.toString()?.trim()

            if (entradaMonto.isNullOrEmpty()) {
                etMonto.error = "Ingresa el importe de la cuenta"
                Toast.makeText(this, "Por favor introduce el total consumido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val monto = entradaMonto.toDoubleOrNull()
            if (monto == null || monto <= 0.0) {
                etMonto.error = "Debe ser un valor positivo"
                return@setOnClickListener
            }

            val personasNum = etPersonas.text?.toString()?.toIntOrNull() ?: 1
            if (personasNum < 1) {
                etPersonas.error = "Mínimo 1 persona"
                return@setOnClickListener
            }

            val porcentaje = when (rgPropina.checkedRadioButtonId) {
                R.id.rbGaloProp10 -> 10
                R.id.rbGaloProp15 -> 15
                R.id.rbGaloProp20 -> 20
                else -> 15
            }

            val totalPropina = monto * (porcentaje / 100.0)
            val granTotal = monto + totalPropina

            tvMontoBase.text = String.format(Locale.US, "$%.2f", monto)
            tvEtiquetaPorc.text = "Propina ($porcentaje%):"
            tvMontoPropina.text = String.format(Locale.US, "$%.2f", totalPropina)
            tvTotalPagar.text = String.format(Locale.US, "$%.2f", granTotal)

            if (personasNum > 1) {
                val valorIndividual = granTotal / personasNum
                tvEtiquetaPersonas.text = "Por comensal ($personasNum personas):"
                tvPorPersona.text = String.format(Locale.US, "$%.2f", valorIndividual)
                layoutPorPersona.visibility = View.VISIBLE
            } else {
                layoutPorPersona.visibility = View.GONE
            }

            cardResultado.visibility = View.VISIBLE
            Toast.makeText(this, "Cálculo efectuado", Toast.LENGTH_SHORT).show()
        }

        btnLimpiar.setOnClickListener {
            etMonto.text?.clear()
            etMonto.error = null
            etPersonas.setText("1")
            etPersonas.error = null
            rgPropina.check(R.id.rbGaloProp15)
            cardResultado.visibility = View.GONE
            Toast.makeText(this, "Formulario limpiado", Toast.LENGTH_SHORT).show()
        }
    }
}
