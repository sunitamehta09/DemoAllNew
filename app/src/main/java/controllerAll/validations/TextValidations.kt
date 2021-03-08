package controllerAll.validations

import android.widget.EditText
import android.widget.TextView
import java.util.regex.Pattern

class TextValidations {
    companion object{
        //Check is EditText is empty or not
        fun emptyCheckEditText(editText: EditText): Boolean {
            var isEmpty = false
            if (editText.getText().toString().trim().isEmpty()) {
                isEmpty = true;
            }
            return isEmpty
        }

        //Check is TextView is empty or not
        fun emptyCheckTextView(textView: TextView): Boolean {
            var isEmpty = false
            if (textView.getText().toString().trim().isEmpty()) {
                isEmpty = true;
            }
            return isEmpty
        }

        // insert space
        fun spacePut(text: String): String? {
            return text.replace("\\B|\\b".toRegex(), " ")
        }

        // Check String contain special characters
        fun isTextContainSpecialCharacters(email: String): Boolean {
            var isValid = false
            val pattern = Pattern.compile("[^A-Za-z\\s]")
            val m = pattern.matcher(email)
            val b = m.find()
            if (b) {
                isValid = true
            }
            return isValid
        }

        // Check String range
        fun IsLengthRangeValid(text: String, start: Int, end: Int): Boolean {
            var isValid = false
            if (text.trim().length >= start && text.trim().length <= end)
                isValid = true;
            return isValid
        }



    }
}