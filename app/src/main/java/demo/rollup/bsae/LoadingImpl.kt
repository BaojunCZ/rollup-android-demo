package demo.rollup.bsae

import demo.rollup.dialog.LoadingDialog

interface LoadingImpl {
    val loadingDialog: LoadingDialog

    fun showLoading() {
        loadingDialog.show()
    }

    fun showUnCancelOutsideLoading() {
        loadingDialog.setCanceledOnTouchOutside(false)
        loadingDialog.show()
    }

    fun hideLoading() {
        loadingDialog.dismiss()
    }

    fun isShowing(): Boolean {
        return loadingDialog.isShowing
    }
}