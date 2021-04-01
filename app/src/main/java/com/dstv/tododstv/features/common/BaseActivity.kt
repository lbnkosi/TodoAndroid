package com.dstv.tododstv.features.common

import androidx.appcompat.app.AppCompatActivity
import com.dstv.tododstv.core.util.dialogs.BottomSheetDialogUtilFragment
import com.dstv.tododstv.core.util.dialogs.DialogUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity: AppCompatActivity() {

    private var mDialogUtils: DialogUtils? = null

    private var mBottomSheetDialogUtilFragment: BottomSheetDialogUtilFragment? = null

    open fun getBottomSheetDialogUtilFragment(aFragment: BaseFragment?): BottomSheetDialogUtilFragment? {
        mBottomSheetDialogUtilFragment = BottomSheetDialogUtilFragment.newInstance(aFragment!!)
        return mBottomSheetDialogUtilFragment
    }

    protected open fun dismissBottomSheet() {
        if (mBottomSheetDialogUtilFragment != null) mBottomSheetDialogUtilFragment!!.dismiss()
    }

    fun getDialog(): DialogUtils = if (mDialogUtils != null) mDialogUtils!! else DialogUtils(this, false)

}