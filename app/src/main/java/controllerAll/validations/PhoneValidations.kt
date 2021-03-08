package controllerAll.validations

import android.util.Log
import android.widget.EditText
import java.util.regex.Pattern

class PhoneValidations {
    companion object{
        fun isPhoneNoValid(email: String): Boolean {
            var isValid = true
            val pattern = Pattern.compile("[^0-9]")
            val m = pattern.matcher(email)
            val b = m.find()
            if (b) {
                isValid = false
            }
            return isValid
        }

        fun isPhoneNoWithCode(editText: EditText, start: Int): Boolean {
            return editText.text.toString().trim().length >= start
        }

        fun removeCharactersInPhoneNo(phoneText: String): String{
            var phone : String = ""
            phone = phoneText.replace(Regex("[()\\-\\s]"), "")
            return phone
        }

        fun showPhoneNoWithCode(countryCode: String, phoneText: String): String{
            var phoneNumberCountryCode : String
            if (phoneText.startsWith("00")) {
                phoneNumberCountryCode = phoneText.replaceFirst("00".toRegex(), "+")
              //  Log.e("checkapp", "phoneNumberCountryCode1- $phoneNumberCountryCode")
            } else if (phoneText.startsWith("0")) {
                phoneNumberCountryCode = phoneText.replaceFirst("0".toRegex(), countryCode)
              //  Log.e("checkapp", "phoneNumberCountryCode2- $phoneNumberCountryCode")
            } else if (phoneText.startsWith("+")) {
                phoneNumberCountryCode = phoneText
              //  Log.e("checkapp", "phoneNumberCountryCode3- $phoneNumberCountryCode")
            } else {
                if (countryCode.equals("+"))
                    phoneNumberCountryCode = phoneText
                else
                    phoneNumberCountryCode = countryCode + phoneText
               // Log.e("checkapp", "phoneNumberCountryCode4- $phoneNumberCountryCode")
            }
            return phoneNumberCountryCode
        }

    }
}