package com.dstv.tododstv.core.util.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.dstv.tododstv.R
import com.dstv.tododstv.features.common.BaseFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialogUtilFragment : BottomSheetDialogFragment() {

    var mBaseFragment: BaseFragment? = null

    companion object {

        @JvmStatic
        fun newInstance(aBaseFragment: BaseFragment): BottomSheetDialogUtilFragment {
            val fragment = BottomSheetDialogUtilFragment()
            fragment.mBaseFragment = aBaseFragment
            return fragment
        }

    }

    override fun onCreateDialog(aSavedInstanceState: Bundle?): Dialog {
        return configureDialog(super.onCreateDialog(aSavedInstanceState) as BottomSheetDialog)
    }

    override fun onCreateView(aInflater: LayoutInflater, aContainer: ViewGroup?, aSavedInstanceState: Bundle?): View {
        super.onCreateView(aInflater, aContainer, aSavedInstanceState)
        val view: View = aInflater.inflate(R.layout.bottom_sheet_dialog_custom_view, aContainer, false)
        if (mBaseFragment == null) {
            dismiss()
        } else {
            childFragmentManager.beginTransaction().add(R.id.fragment_container, mBaseFragment!!).commit()
        }
        return view
    }

    private fun configureDialog(aDialog: BottomSheetDialog): BottomSheetDialog {
        aDialog.setOnShowListener {
            if (mBaseFragment == null) {
                dismiss()
            } else if (aDialog.findViewById<View>(R.id.design_bottom_sheet) != null) {
                BottomSheetBehavior.from(aDialog.findViewById<FrameLayout>(R.id.design_bottom_sheet) as FrameLayout).isHideable = false
            }
        }
        return aDialog
    }
}