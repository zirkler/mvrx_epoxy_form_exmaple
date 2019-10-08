package com.zirkler.mvrxepoxyformexample.arch_core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.mvrx.BaseMvRxFragment
import com.zirkler.mvrxepoxyformexample.R

abstract class VLRecyclerViewBaseFragment : BaseMvRxFragment() {

    protected lateinit var recyclerView: EpoxyRecyclerView
    protected val epoxyController by lazy { epoxyController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        epoxyController.onRestoreInstanceState(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(
            R.layout.fragment_vl_recycler_view_base,
            container,
            false
        ).apply {
            recyclerView = findViewById(R.id.recycler_view)
            recyclerView.setController(epoxyController)
        }
    }

    override fun invalidate() {
        epoxyController.requestDelayedModelBuild(500)
    }

    /**
     * Provide the EpoxyController to use when building models for this Fragment.
     * Basic usages can simply use [simpleController]
     */
    abstract fun epoxyController(): MvRxEpoxyController

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        epoxyController.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        epoxyController.cancelPendingModelBuild()
        super.onDestroyView()
    }
}