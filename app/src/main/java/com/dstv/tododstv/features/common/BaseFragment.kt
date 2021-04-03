package com.dstv.tododstv.features.common

import androidx.fragment.app.Fragment
import com.dstv.tododstv.core.util.dialogs.BottomSheetDialogUtilFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    private lateinit var bottomSheetUtil: BottomSheetDialogUtilFragment

    open fun openBottomSheet(aFragment: BaseFragment): BottomSheetDialogUtilFragment {
        bottomSheetUtil = BottomSheetDialogUtilFragment.newInstance(aFragment)
        return bottomSheetUtil
    }

    fun dismissBottomSheet() {
        bottomSheetUtil.dismiss()
    }

}