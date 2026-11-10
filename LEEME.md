# Deber de Aplicaciones Móviles - Semana 2: RadioButtons
**Estudiante:** Galo  
**Universidad:** UNIANDES  
**Materia:** 07_A Aplicaciones Móviles  

---

## 📱 Contenido del Proyecto
Este proyecto contiene los 5 ejercicios solicitados implementados mediante una interfaz estilizada en tonos **Esmeralda / Teal** y componentes **Material Design 3**, con una pantalla principal (`MainActivity`) que actúa como panel de acceso para cada práctica:

1. **Encuesta de Satisfacción (`EncuestaActivity`):**
   - Utiliza `RadioGroup` y `RadioButton` con 4 niveles: *Excelente*, *Bueno*, *Regular* y *Malo*.
   - Permite añadir observaciones adicionales.
   - Presenta un informe de calificación con retroalimentación contextual y botón de reinicio.

2. **Selector de Tema (`SelectorTemaActivity`):**
   - Selección entre *Tema Claro ☀️*, *Tema Oscuro 🌙* y *Predeterminado del Sistema ⚙️*.
   - Aplica el modo visual en tiempo real usando `AppCompatDelegate.setDefaultNightMode(...)`.
   - Guarda la preferencia en `SharedPreferences` para que persista en toda la aplicación.

3. **Quiz Simple de Conocimientos (`QuizActivity`):**
   - Cuestionario evaluativo con 4 preguntas sobre componentes Android (listeners, XML, ciclo de vida, atributos).
   - Uso de `RadioGroup` en cada pregunta para garantizar selección única.
   - Calcula la calificación final, el porcentaje de aciertos y genera un resumen con las respuestas correctas.

4. **Calculadora de Propinas (`PropinasActivity`):**
   - Ingreso del valor de la cuenta con validación de números positivos.
   - Selección de porcentaje: *10%*, *15%* y *20%*.
   - Función adicional para dividir la cuenta equitativamente entre comensales si son más de una persona.

5. **Conversor de Magnitudes (`ConversorActivity`):**
   - Selección de la magnitud con RadioButtons: *Longitud*, *Peso/Masa* y *Temperatura*.
   - Actualización dinámica de campos de entrada y subtipos de conversión según el RadioButton activo.
   - Despliega el resultado numérico y la fórmula física aplicada.

---

## 🚀 Cómo abrir y ejecutar en Android Studio
1. Abre **Android Studio**.
2. Haz clic en **Open** (o `File -> Open`).
3. Selecciona la carpeta **Galo** (`c:\Users\milto\Downloads\deber josue y galo\Galo`).
4. Espera a que Gradle sincronice automáticamente y presiona el botón verde de **Run** (`Shift + F10`).
