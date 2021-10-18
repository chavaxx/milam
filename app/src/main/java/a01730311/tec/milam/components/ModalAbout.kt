package a01730311.tec.milam.components

import a01730311.tec.milam.R
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class ModalAbout(context: Context) {

    private val infoDialog: Dialog = Dialog(context)
    private lateinit var closeDialogButton: ImageView
    private lateinit var descriptionLabel: TextView
    private lateinit var titleLabel: TextView


    fun showInfo(description: String, title: String) {
        //TODO : CHANGE NAME
        infoDialog.setContentView(R.layout.info_dialog)

        infoDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        infoDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //TODO: MAKE BINDING

        closeDialogButton = infoDialog.findViewById(R.id.close_info_dialog)
        descriptionLabel = infoDialog.findViewById(R.id.modal_info_description)
        titleLabel = infoDialog.findViewById(R.id.modal_info_title)

        closeDialogButton.setOnClickListener {
            infoDialog.hide()
        }

        descriptionLabel.text = description
        titleLabel.text = title



        infoDialog.show()
    }


}