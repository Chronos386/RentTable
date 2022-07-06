package ru.bscmsk.renttable.presentation.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class HorisontalSpaceItemDecoration(private val horisontalSpaceHeight: Int) :
    ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.right = horisontalSpaceHeight
    }
}