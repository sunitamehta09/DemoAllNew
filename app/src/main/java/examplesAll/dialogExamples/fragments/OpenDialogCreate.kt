package examplesAll.dialogExamples.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.self.demoaall.R
import com.self.demoaall.databinding.LayoutShowDialogBinding
import controllerAll.utilsAll.AppConstants
import examplesAll.dialogExamples.callBacks.SaveChangesListener

class OpenDialogCreate : DialogFragment {
    lateinit var listener: SaveChangesListener

    constructor() : super() {}
    constructor(listener: SaveChangesListener) : super() {
        this.listener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        retainInstance  = true
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.round_alert_box);
        val dialogView: LayoutShowDialogBinding = DataBindingUtil.inflate(
                LayoutInflater.from(requireActivity()), R.layout.layout_show_dialog, null, true
        )

        if (arguments!!.containsKey(AppConstants.EXTRAS_MESSAGE)) {
            dialogView.edtMsg.append(arguments!!.getString(AppConstants.EXTRAS_MESSAGE))
        }

        if (arguments!!.containsKey(AppConstants.EXTRAS_TITLE)) {
            dialogView.txtDialogHeader.text = arguments!!.getString(AppConstants.EXTRAS_TITLE)
        }

        dialogView.imgCancel.setOnClickListener {
            dismiss()
        }

        dialogView.txtCancel.setOnClickListener {
            dismiss()
        }

        dialogView.txtSave.setOnClickListener {
            if (this::listener.isInitialized)
                listener.doneChanges(dialogView.edtMsg.text.toString())
            dismiss()
        }

        return dialogView!!.root
    }

    companion object {
        @JvmOverloads
        @JvmStatic
        fun create(message: String, listener: SaveChangesListener): OpenDialogCreate {
            val dialog = OpenDialogCreate(listener)
            val args = Bundle()
            args.putString(AppConstants.EXTRAS_MESSAGE, message)
            dialog.arguments = args
            return dialog
        }

        fun create(title: String, message: String, listener: SaveChangesListener): OpenDialogCreate {
            val dialog = OpenDialogCreate(listener)
            val args = Bundle()
            args.putString(AppConstants.EXTRAS_MESSAGE, message)
            args.putString(AppConstants.EXTRAS_TITLE, title)
            dialog.arguments = args
            return dialog
        }
    }
}

