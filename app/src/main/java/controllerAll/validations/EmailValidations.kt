package controllerAll.validations

import android.widget.EditText
import android.widget.TextView
import java.util.regex.Matcher
import java.util.regex.Pattern

public class EmailValidations {
    companion object {

        //Check is email id is correct in format or not
        fun isEmailValids(email: String): Boolean {
            val EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
            val inputStr: CharSequence = email
            val pattern = Pattern.compile(EMAIL_PATTERN)
            val matcher: Matcher = pattern.matcher(inputStr)
            return matcher.matches()
        }

        // Check Email is valid or not
        fun isEmailValid(email: String): Boolean {
            var isValid = false
            val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
            val inputStr: CharSequence = email
            val pattern =
                    Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(inputStr)
            if (matcher.matches()) {
                isValid = true
            }
            return isValid
        }
    }
}