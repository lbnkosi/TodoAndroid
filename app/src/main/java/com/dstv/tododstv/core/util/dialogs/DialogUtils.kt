package com.dstv.tododstv.core.util.dialogs

import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import com.dstv.tododstv.R

class DialogUtils(aContext: Context, isFinishing: Boolean) {

    private val mIsFinishing: Boolean = isFinishing

    private val mResources: Resources = aContext.resources

    private val mDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(aContext, R.style.AboutMeDialogTheme)

    fun showDialog(aMessage: String?) {
        if (TextUtils.isEmpty(aMessage) || mIsFinishing) return
        mDialogBuilder.setMessage(aMessage).setPositiveButton(mResources.getString(R.string.okay_text)) { _, _ -> }
        mDialogBuilder.show()
    }

    @JvmOverloads
    fun showDialog(aMessage: String?, aPositiveActionText: String?, aPositiveOnClickListener: DialogInterface.OnClickListener?, isCancelable: Boolean = true) {
        if (TextUtils.isEmpty(aMessage) || mIsFinishing || aPositiveOnClickListener == null) return
        mDialogBuilder.setMessage(aMessage)
        mDialogBuilder.setPositiveButton(if (TextUtils.isEmpty(aPositiveActionText)) mResources.getString(R.string.okay_text) else aPositiveActionText, aPositiveOnClickListener)
        mDialogBuilder.setCancelable(isCancelable)
        mDialogBuilder.show()
    }

    fun showDialog(aMessage: String?, aPositiveActionText: String?, aPositiveOnClickListener: DialogInterface.OnClickListener?, aNegativeActionText: String?) {
        if (TextUtils.isEmpty(aMessage) || mIsFinishing || aPositiveOnClickListener == null) return
        mDialogBuilder.setMessage(aMessage)
        mDialogBuilder.setPositiveButton(if (TextUtils.isEmpty(aPositiveActionText)) mResources.getString(R.string.okay_text) else aPositiveActionText, aPositiveOnClickListener)
        mDialogBuilder.setMessage(aMessage).setNegativeButton(if (TextUtils.isEmpty(aNegativeActionText)) mResources.getString(R.string.cancel) else aNegativeActionText) { _, _ -> }
        val alertDialog = mDialogBuilder.create()
        if (alertDialog.window != null) alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }

    @JvmOverloads
    fun showDialog(aMessage: String?, aPositiveActionText: String?, aPositiveOnClickListener: DialogInterface.OnClickListener?, aNegativeActionText: String?, aNegativeActionListener: DialogInterface.OnClickListener?, isCancelable: Boolean = true) {
        if (TextUtils.isEmpty(aMessage) || mIsFinishing || aPositiveOnClickListener == null || aNegativeActionListener == null) return
        mDialogBuilder.setMessage(aMessage)
        mDialogBuilder.setPositiveButton(if (TextUtils.isEmpty(aPositiveActionText)) mResources.getString(R.string.okay_text) else aPositiveActionText, aPositiveOnClickListener)
        mDialogBuilder.setNegativeButton(if (TextUtils.isEmpty(aNegativeActionText)) mResources.getString(R.string.cancel) else aNegativeActionText, aNegativeActionListener)
        mDialogBuilder.setCancelable(isCancelable)
        val alertDialog = mDialogBuilder.create()
        if (alertDialog.window != null) alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }

    fun showDialog(
        aMessage: String?,
        aAction1Text: String?,
        aAction1ClickListener: DialogInterface.OnClickListener?,
        aAction2Text: String?,
        aAction2ClickListener: DialogInterface.OnClickListener?,
        aNegativeActionText: String?,
        aNegativeActionListener: DialogInterface.OnClickListener?,
        isCancelable: Boolean
    ) {
        if (TextUtils.isEmpty(aMessage) ||
            mIsFinishing || aAction1ClickListener == null || aAction2ClickListener == null || aNegativeActionListener == null ||
            TextUtils.isEmpty(aAction1Text) ||
            TextUtils.isEmpty(aAction2Text)
        ) {
            return
        }
        mDialogBuilder.setMessage(aMessage)
        mDialogBuilder.setPositiveButton(aAction1Text, aAction1ClickListener)
        mDialogBuilder.setNeutralButton(aAction2Text, aAction2ClickListener)
        mDialogBuilder.setNegativeButton(if (TextUtils.isEmpty(aNegativeActionText)) mResources.getString(R.string.cancel) else aNegativeActionText, aNegativeActionListener)
        mDialogBuilder.setCancelable(isCancelable)
        val alertDialog = mDialogBuilder.create()
        if (alertDialog.window != null) {
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        alertDialog.show()
    }

}