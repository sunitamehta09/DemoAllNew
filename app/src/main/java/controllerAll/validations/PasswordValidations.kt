package controllerAll.validations

import android.widget.EditText
import java.util.regex.Pattern

class PasswordValidations {
    companion object {
        //Check password and confirm password matched or not
        fun isPasswordMatch(text: String, match: String): Boolean {
            var isMatch = false
            if (text.equals(match))
                isMatch = true
            return isMatch
        }

        //Check password and confirm password matched or not
        fun matchesTextView(text: String, match: String): Boolean {
            var isMatch = false
            if (text == match)
                isMatch = true
            return isMatch
        }

        fun checkPasswordLength(editText: EditText, start: Int, end: Int): Boolean {
            var isPasswordLengthValid = false
            if (editText.text.toString().trim().length >= start && editText.text.toString().trim().length <= end) {
                isPasswordLengthValid = true
            }
            return isPasswordLengthValid
        }

        // Check String contain special characters
        fun isPasswordContainSpecialCharacters(email: String): Boolean {
            var isValid = false
            val pattern = Pattern.compile("[^A-Za-z0-9]")
            val m = pattern.matcher(email)
            val b = m.find()
            if (b) {
                isValid = true
            }
            return isValid
        }

        // Check String contain special characters
        fun isPasswordContainAlphabet(email: String): Boolean {
            var isValid = false
            val pattern = Pattern.compile("[^0-9]")
            val m = pattern.matcher(email)
            val b = m.find()
            if (b) {
                isValid = true
            }
            return isValid
        }
    }
}