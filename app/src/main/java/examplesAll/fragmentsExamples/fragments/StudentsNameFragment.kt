package examplesAll.fragmentsExamples.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.self.demoaall.R
import com.self.demoaall.databinding.FragmentStudentNameBinding
import controllerAll.utilsAll.AppConstants
import controllerAll.utilsAll.SetDataAll
import examplesAll.fragmentsExamples.adapter.StudentAdapter
import java.util.ArrayList
import java.util.HashMap

class StudentsNameFragment : Fragment(){
    var viewBinding: FragmentStudentNameBinding? = null
    var studentAdapter: StudentAdapter? = null
    private var data: ArrayList<HashMap<String, Any>>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_student_name, container, false)

        data = SetDataAll.getOptionsAll(requireContext(), AppConstants.STUDENT_DATA)
        studentAdapter = StudentAdapter(requireContext(), requireActivity(),
                R.layout.adapter_show_options, data!!)

        val linearLayoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL, false)
        viewBinding!!.recyclerView.layoutManager = linearLayoutManager
        viewBinding!!.recyclerView.adapter = studentAdapter

        return viewBinding!!.root
    }
}