package examplesAll.dialogExamples.fragments

import examplesAll.dialogExamples.callBacks.SaveChangesListener
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import controllerAll.utilsAll.AppConstants


class AlertDialogFragment(val listener: SaveChangesListener) : DialogFragment() {
    companion object {
        fun newInstance(message: String, listener: SaveChangesListener): AlertDialogFragment {
            val fragment = AlertDialogFragment(listener)
            val args = Bundle()
            args.putString(AppConstants.EXTRAS_MESSAGE, message)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = arguments!!.getString(AppConstants.EXTRAS_MESSAGE)
        val alertDialogBuilder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage("Are you sure?")
        alertDialogBuilder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            listener.doneChanges(title!!)
            dismiss()
        })
        alertDialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            if (dialog != null)
                dialog.dismiss()
        })

        return alertDialogBuilder.create()
    }

}