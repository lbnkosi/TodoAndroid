package com.dstv.tododstv.features.common

import androidx.fragment.app.Fragment
import com.dstv.tododstv.core.util.dialogs.BottomSheetDialogUtilFragment
import com.dstv.tododstv.core.util.dialogs.DialogUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment: Fragment() {

    private var mDialogUtils: DialogUtils? = null

    private var mBottomSheetDialogUtilFragment: BottomSheetDialogUtilFragment? = null

    open fun getBottomSheet(aFragment: BaseFragment?): BottomSheetDialogUtilFragment? {
        mBottomSheetDialogUtilFragment = BottomSheetDialogUtilFragment.newInstance(aFragment!!)
        return mBottomSheetDialogUtilFragment
    }

    fun dismissBottomSheet() {
        mBottomSheetDialogUtilFragment?.dismiss()
    }

    fun getDialog(): DialogUtils = if (mDialogUtils != null) mDialogUtils!! else DialogUtils(requireContext(), false)

}