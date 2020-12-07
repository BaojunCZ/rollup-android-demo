package demo.rollup.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import demo.rollup.R

class LoadingDialog(context: Context) : Dialog(context, R.style.ProgressDialog) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
    }
}