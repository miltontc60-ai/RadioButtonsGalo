package com.galo.radiobuttonsapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

/**
 * Ejercicio 2: Selector de Tema
 * Desarrollado por: Galo
 * Implementa cambio de tema (Claro, Oscuro, Sistema) mediante RadioButtons y SharedPreferences.
 */
class SelectorTemaActivity : AppCompatActivity() {

    private val PREFERENCIAS = "GaloThemePrefs"
    private val CLAVE_TEMA = "modo_tema"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selector_tema)

        val btnVolver = findViewById<Button>(R.id.btnGaloVolverTema)
        val rgTema = findViewById<RadioGroup>(R.id.rgGaloTema)
        val rbClaro = findViewById<RadioButton>(R.id.rbGaloClaro)
        val rbOscuro = findViewById<RadioButton>(R.id.rbGaloOscuro)
        val rbSistema = findViewById<RadioButton>(R.id.rbGaloSistema)
        val btnAplicar = findViewById<Button>(R.id.btnGaloAplicarTema)
        val tvEstado = findViewById<TextView>(R.id.tvGaloEstadoTema)

        btnVolver.setOnClickListener {
            finish()
        }

        val sp: SharedPreferences = getSharedPreferences(PREFERENCIAS, Context.MODE_PRIVATE)
        val modoGuardado = sp.getInt(CLAVE_TEMA, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        when (modoGuardado) {
            AppCompatDelegate.MODE_NIGHT_NO -> {
                rbClaro.isChecked = true
                tvEstado.text = "Modo actualmente activo: Tema Claro ☀️"
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                rbOscuro.isChecked = true
                tvEstado.text = "Modo actualmente activo: Tema Oscuro 🌙"
            }
            else -> {
                rbSistema.isChecked = true
                tvEstado.text = "Modo actualmente activo: Predeterminado del Sistema ⚙️"
            }
        }

        fun guardarYAplicar(modo: Int, descripcion: String) {
            sp.edit().putInt(CLAVE_TEMA, modo).apply()
            AppCompatDelegate.setDefaultNightMode(modo)
            tvEstado.text = "Modo actualmente activo: $descripcion"
            Toast.makeText(this, "Tema cambiado a: $descripcion", Toast.LENGTH_SHORT).show()
        }

        btnAplicar.setOnClickListener {
            when (rgTema.checkedRadioButtonId) {
                R.id.rbGaloClaro -> guardarYAplicar(AppCompatDelegate.MODE_NIGHT_NO, "Tema Claro ☀️")
                R.id.rbGaloOscuro -> guardarYAplicar(AppCompatDelegate.MODE_NIGHT_YES, "Tema Oscuro 🌙")
                R.id.rbGaloSistema -> guardarYAplicar(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, "Predeterminado del Sistema ⚙️")
                else -> Toast.makeText(this, "Selecciona una opción de tema", Toast.LENGTH_SHORT).show()
            }
        }

        rgTema.setOnCheckedChangeListener { _, idSeleccionado ->
            when (idSeleccionado) {
                R.id.rbGaloClaro -> guardarYAplicar(AppCompatDelegate.MODE_NIGHT_NO, "Tema Claro ☀️")
                R.id.rbGaloOscuro -> guardarYAplicar(AppCompatDelegate.MODE_NIGHT_YES, "Tema Oscuro 🌙")
                R.id.rbGaloSistema -> guardarYAplicar(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, "Predeterminado del Sistema ⚙️")
            }
        }
    }
}
