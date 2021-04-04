package com.dstv.tododstv.core.util.recyclerview

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.RecyclerView
import com.dstv.tododstv.core.util.dialogs.BottomSheetDialogUtilFragment
import com.dstv.tododstv.features.common.BaseFragment

abstract class DataBoundListAdapter<T, V : ViewDataBinding?> : RecyclerView.Adapter<DataBoundViewHolder<V>>() {

    private var dataVersion = 0

    private var items: List<T>? = null

    private var bottomSheet: BottomSheetDialogUtilFragment? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataBoundViewHolder(createBinding(parent))

    protected abstract fun createBinding(parent: ViewGroup?): V

    override fun onBindViewHolder(holder: DataBoundViewHolder<V>, position: Int) {
        bind(holder.binding, items!![position])
        holder.binding!!.executePendingBindings()
    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    fun replace(update: List<T>?) {
        dataVersion++
        when {
            items == null -> {
                if (update == null) return
                items = update
                notifyDataSetChanged()
            }
            update == null -> {
                val oldSize = items!!.size
                items = null
                notifyItemRangeRemoved(0, oldSize)
            }
            else -> {
                val startVersion = dataVersion
                val oldItems: List<T> = items!!
                object : AsyncTask<Void?, Void?, DiffResult>() {
                    override fun doInBackground(vararg p0: Void?): DiffResult {
                        return DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                            override fun getOldListSize() = oldItems.size

                            override fun getNewListSize() = update.size

                            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                                val oldItem = oldItems[oldItemPosition]
                                val newItem = update[newItemPosition]
                                return this@DataBoundListAdapter.areItemsTheSame(oldItem, newItem)
                            }

                            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                                val oldItem = oldItems[oldItemPosition]
                                val newItem = update[newItemPosition]
                                return this@DataBoundListAdapter.areContentsTheSame(oldItem, newItem)
                            }
                        })
                    }

                    override fun onPostExecute(diffResult: DiffResult) {
                        if (startVersion != dataVersion) return
                        items = update
                        diffResult.dispatchUpdatesTo(this@DataBoundListAdapter)
                    }
                }.execute()
            }
        }
    }

    protected abstract fun bind(binding: V, item: T)

    protected fun areItemsTheSame(oldItem: T, newItem: T): Boolean = newItem == oldItem

    protected fun areContentsTheSame(oldItem: T, newItem: T): Boolean = newItem == oldItem

    override fun getItemCount() = if (items == null) 0 else items!!.size

    protected fun showBottomSheet(baseFragment: BaseFragment, fragmentManager: FragmentManager) {
        getBottomSheet(baseFragment).show(fragmentManager, baseFragment::class.java.name)
    }

    private fun getBottomSheet(aFragment: BaseFragment): BottomSheetDialogUtilFragment {
        bottomSheet = BottomSheetDialogUtilFragment.newInstance(aFragment)
        return bottomSheet!!
    }

    protected fun dismissBottomSheetDialog() {
        bottomSheet?.dismiss()
    }
}