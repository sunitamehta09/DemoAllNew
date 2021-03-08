package examplesAll.dialogExamples.fragments

import examplesAll.dialogExamples.callBacks.SaveChangesListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.self.demoaall.R
import com.self.demoaall.databinding.LayoutShowDialogBinding
import controllerAll.utilsAll.AppConstants

class OpenDialogNewInstance(var listener: SaveChangesListener) : DialogFragment(){
    companion object {
        fun newInstance(title: String, message: String, listener: SaveChangesListener): OpenDialogNewInstance {
            val fragment = OpenDialogNewInstance(listener)
            val args = Bundle()
            args.putString(AppConstants.EXTRAS_MESSAGE, message)
            args.putString(AppConstants.EXTRAS_TITLE, title)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.round_alert_box);
        val dialogView: LayoutShowDialogBinding = DataBindingUtil.inflate(
                LayoutInflater.from(requireActivity()), R.layout.layout_show_dialog, null, true
        )

        if(arguments!!.containsKey(AppConstants.EXTRAS_MESSAGE)){
            dialogView.edtMsg.append(arguments!!.getString(AppConstants.EXTRAS_MESSAGE))
        }

        if(arguments!!.containsKey(AppConstants.EXTRAS_TITLE)){
            dialogView.txtDialogHeader.text = arguments!!.getString(AppConstants.EXTRAS_TITLE)
        }

        dialogView.imgCancel.setOnClickListener {
            dismiss()
        }

        dialogView.txtCancel.setOnClickListener {
            dismiss()
        }

        dialogView.txtSave.setOnClickListener {
            listener.doneChanges(dialogView.edtMsg.text.toString())
            dismiss()
        }
        return dialogView!!.root
    }
}