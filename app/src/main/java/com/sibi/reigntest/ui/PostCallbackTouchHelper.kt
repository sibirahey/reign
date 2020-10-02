package com.sibi.reigntest.ui

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class PostCallbackTouchHelper(private val onSwiped: (position: Long) -> Unit) :
    ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT
    ) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (viewHolder is PostAdapter.PostViewHolder) {
            onSwiped(viewHolder.id)
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        getDefaultUIUtil().clearView(getForegroundView(viewHolder))
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        getDefaultUIUtil().onSelected(getForegroundView(viewHolder))
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        getDefaultUIUtil().onDraw(
            c,
            recyclerView,
            getForegroundView(viewHolder),
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
    }

    override fun onChildDrawOver(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder?,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        getDefaultUIUtil().onDrawOver(
            c, recyclerView,
            getForegroundView(viewHolder), dX, dY,
            actionState, isCurrentlyActive
        )
    }

    private fun getForegroundView(viewHolder: RecyclerView.ViewHolder?): View? {
        return viewHolder?.let { (viewHolder as PostAdapter.PostViewHolder).foregroundView }
    }


}