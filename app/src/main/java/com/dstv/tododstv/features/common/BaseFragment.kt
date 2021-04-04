package com.dstv.tododstv.features.common

import androidx.fragment.app.Fragment
import com.dstv.tododstv.core.util.dialogs.BottomSheetDialogUtilFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    private lateinit var bottomSheetUtil: BottomSheetDialogUtilFragment

    fun show(aFragment: BaseFragment) {
        openBottomSheet(aFragment).show(childFragmentManager, aFragment::class.java.name)
    }

    fun openBottomSheet(aFragment: BaseFragment): BottomSheetDialogUtilFragment {
        bottomSheetUtil = BottomSheetDialogUtilFragment.newInstance(aFragment)
        return bottomSheetUtil
    }

    fun dismissBottomSheet() {
        bottomSheetUtil.dismiss()
    }

}