package controllerAll.validations

import java.text.SimpleDateFormat
import java.util.*

class DateValidations {
    companion object {
        //Format date
        fun changeDateFormat(date: String?): String? {
            return try {
                val simpleFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+SSSS")
                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                val date: Date = simpleFormatter.parse(date)
                simpleDateFormat.format(date)
            } catch (e: Exception) {
                null
            }
        }
    }

}