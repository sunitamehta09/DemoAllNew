package controllerAll.StringUtilsAll

import examplesAll.adapterExamples.viewModelsAll.State
import examplesAll.selectUnselectExamples.viewModelsAll.CategoryModel

class StringUtils {
    companion object {
        @JvmStatic
        fun getCategoryResult(categorylist: ArrayList<CategoryModel>): String {
            var categoryResult: StringBuilder = StringBuilder()
            for (item in categorylist.indices) {
                if (item == 0)
                    categoryResult.append(categorylist.get(item).categoryName + " - " + categorylist.get(item).priority)
                else
                    categoryResult.append(" , " + categorylist.get(item).categoryName + " - " + categorylist.get(item).priority)
            }
            return categoryResult.toString()
        }

        @JvmStatic
        fun getSelectedCity(selectedCityList: ArrayList<State>): String {
            var selectedCity: StringBuilder = StringBuilder()
            for (item in selectedCityList.indices) {
                if (item == 0)
                    selectedCity.append(selectedCityList.get(item).name)
                else
                    selectedCity.append(" , " + selectedCityList.get(item).name)
            }
            return selectedCity.toString()
        }
    }
}