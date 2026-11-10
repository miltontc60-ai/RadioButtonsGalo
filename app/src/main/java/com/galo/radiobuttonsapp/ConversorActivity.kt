package com.galo.radiobuttonsapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Locale

/**
 * Ejercicio 5: Conversor de Unidades
 * Desarrollado por: Galo
 * Conversor dinámico entre longitud, peso y temperatura mediante RadioButtons.
 */
class ConversorActivity : AppCompatActivity() {

    private lateinit var tilEntrada: TextInputLayout
    private lateinit var etEntrada: TextInputEditText
    private lateinit var rgMagnitud: RadioGroup
    private lateinit var rgSubOpciones: RadioGroup
    private lateinit var rbSub1: RadioButton
    private lateinit var rbSub2: RadioButton
    private lateinit var rbSub3: RadioButton
    private lateinit var cardResultado: MaterialCardView
    private lateinit var tvValorConvertido: TextView
    private lateinit var tvFormulaAplicada: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversor)

        val btnVolver = findViewById<Button>(R.id.btnGaloVolverConversor)
        tilEntrada = findViewById(R.id.tilGaloValorEntrada)
        etEntrada = findViewById(R.id.etGaloValorEntrada)
        rgMagnitud = findViewById(R.id.rgGaloMagnitud)
        rgSubOpciones = findViewById(R.id.rgGaloSubOpciones)
        rbSub1 = findViewById(R.id.rbGaloSubOpt1)
        rbSub2 = findViewById(R.id.rbGaloSubOpt2)
        rbSub3 = findViewById(R.id.rbGaloSubOpt3)

        val btnConvertir = findViewById<Button>(R.id.btnGaloConvertir)
        val btnLimpiar = findViewById<Button>(R.id.btnGaloLimpiarConversor)
        cardResultado = findViewById(R.id.cardGaloResultadoConversor)
        tvValorConvertido = findViewById(R.id.tvGaloValorConvertido)
        tvFormulaAplicada = findViewById(R.id.tvGaloFormulaAplicada)

        btnVolver.setOnClickListener {
            finish()
        }

        // Listener para cambiar dinámicamente los campos según el RadioButton seleccionado
        rgMagnitud.setOnCheckedChangeListener { _, checkedId ->
            actualizarCampos(checkedId)
            cardResultado.visibility = View.GONE
        }

        btnConvertir.setOnClickListener {
            calcular()
        }

        btnLimpiar.setOnClickListener {
            etEntrada.text?.clear()
            etEntrada.error = null
            cardResultado.visibility = View.GONE
            Toast.makeText(this, "Datos reiniciados", Toast.LENGTH_SHORT).show()
        }

        // Configuración inicial por defecto
        actualizarCampos(R.id.rbGaloLongitud)
    }

    private fun actualizarCampos(id: Int) {
        when (id) {
            R.id.rbGaloLongitud -> {
                tilEntrada.hint = "Valor a convertir en Metros (m)"
                rbSub1.text = "Metros (m) a Kilómetros (km)"
                rbSub2.text = "Metros (m) a Millas (mi)"
                rbSub3.text = "Metros (m) a Pies (ft)"
                rbSub1.isChecked = true
            }
            R.id.rbGaloPeso -> {
                tilEntrada.hint = "Valor a convertir en Kilogramos (kg)"
                rbSub1.text = "Kilogramos (kg) a Libras (lb)"
                rbSub2.text = "Kilogramos (kg) a Gramos (g)"
                rbSub3.text = "Kilogramos (kg) a Onzas (oz)"
                rbSub1.isChecked = true
            }
            R.id.rbGaloTemperatura -> {
                tilEntrada.hint = "Valor térmico a convertir"
                rbSub1.text = "Celsius (°C) a Fahrenheit (°F)"
                rbSub2.text = "Fahrenheit (°F) a Celsius (°C)"
                rbSub3.text = "Celsius (°C) a Kelvin (K)"
                rbSub1.isChecked = true
            }
        }
    }

    private fun calcular() {
        val entrada = etEntrada.text?.toString()?.trim()

        if (entrada.isNullOrEmpty()) {
            etEntrada.error = "Ingresa una cantidad"
            Toast.makeText(this, "Por favor escribe un número a convertir", Toast.LENGTH_SHORT).show()
            return
        }

        val num = entrada.toDoubleOrNull()
        if (num == null) {
            etEntrada.error = "Número no válido"
            return
        }

        val tipoMag = rgMagnitud.checkedRadioButtonId
        val subTipo = rgSubOpciones.checkedRadioButtonId

        var valRes = 0.0
        var textoRes = ""
        var formula = ""

        when (tipoMag) {
            R.id.rbGaloLongitud -> {
                if (num < 0) {
                    etEntrada.error = "La longitud debe ser positiva o cero"
                    return
                }
                when (subTipo) {
                    R.id.rbGaloSubOpt1 -> {
                        valRes = num / 1000.0
                        textoRes = String.format(Locale.US, "%.2f m = %.4f km", num, valRes)
                        formula = "Fórmula: metros / 1000"
                    }
                    R.id.rbGaloSubOpt2 -> {
                        valRes = num * 0.000621371
                        textoRes = String.format(Locale.US, "%.2f m = %.4f mi", num, valRes)
                        formula = "Fórmula: metros × 0.000621371"
                    }
                    R.id.rbGaloSubOpt3 -> {
                        valRes = num * 3.28084
                        textoRes = String.format(Locale.US, "%.2f m = %.2f ft", num, valRes)
                        formula = "Fórmula: metros × 3.28084"
                    }
                }
            }
            R.id.rbGaloPeso -> {
                if (num < 0) {
                    etEntrada.error = "El peso debe ser positivo o cero"
                    return
                }
                when (subTipo) {
                    R.id.rbGaloSubOpt1 -> {
                        valRes = num * 2.20462
                        textoRes = String.format(Locale.US, "%.2f kg = %.2f lb", num, valRes)
                        formula = "Fórmula: kg × 2.20462"
                    }
                    R.id.rbGaloSubOpt2 -> {
                        valRes = num * 1000.0
                        textoRes = String.format(Locale.US, "%.2f kg = %.2f g", num, valRes)
                        formula = "Fórmula: kg × 1000"
                    }
                    R.id.rbGaloSubOpt3 -> {
                        valRes = num * 35.274
                        textoRes = String.format(Locale.US, "%.2f kg = %.2f oz", num, valRes)
                        formula = "Fórmula: kg × 35.274"
                    }
                }
            }
            R.id.rbGaloTemperatura -> {
                when (subTipo) {
                    R.id.rbGaloSubOpt1 -> {
                        valRes = (num * 9.0 / 5.0) + 32.0
                        textoRes = String.format(Locale.US, "%.2f °C = %.2f °F", num, valRes)
                        formula = "Fórmula: (°C × 9/5) + 32 = °F"
                    }
                    R.id.rbGaloSubOpt2 -> {
                        valRes = (num - 32.0) * 5.0 / 9.0
                        textoRes = String.format(Locale.US, "%.2f °F = %.2f °C", num, valRes)
                        formula = "Fórmula: (°F - 32) × 5/9 = °C"
                    }
                    R.id.rbGaloSubOpt3 -> {
                        valRes = num + 273.15
                        textoRes = String.format(Locale.US, "%.2f °C = %.2f K", num, valRes)
                        formula = "Fórmula: °C + 273.15 = K"
                    }
                }
            }
        }

        tvValorConvertido.text = textoRes
        tvFormulaAplicada.text = formula
        cardResultado.visibility = View.VISIBLE
        Toast.makeText(this, "Conversión calculada", Toast.LENGTH_SHORT).show()
    }
}
