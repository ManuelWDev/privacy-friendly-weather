package org.secuso.privacyfriendlyweather.ui.RecycleList;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import org.jetbrains.annotations.NotNull;

/**
 * To use the ItemTouchHelper we need to create an ItemTouchHelper.Callback which this class is.
 * For the most part it has been taken from
 * https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-b9456d2b1aaf#.hmhbe8sku
 * as of 2016-08-03
 */
public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter adapter;

    /**
     * Constructor.
     *
     * @param adapter The adapter to bind the ItemTouchHelper to.
     */
    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * @see ItemTouchHelper.Callback#isLongPressDragEnabled()
     * As it is not supported, false will be returned.
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /**
     * @see ItemTouchHelper.Callback#isItemViewSwipeEnabled()
     * As this feature is supported, true will be returned.
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /**
     * @see androidx.recyclerview.widget.ItemTouchHelper.Callback#getMovementFlags(RecyclerView, RecyclerView.ViewHolder)
     * Sets the swipe flags for start and end.
     */
    @Override
    public int getMovementFlags(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * @see androidx.recyclerview.widget.ItemTouchHelper.Callback#onMove(RecyclerView, RecyclerView.ViewHolder, RecyclerView.ViewHolder)
     */
    @Override
    public boolean onMove(@NotNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    /**
     * @see androidx.recyclerview.widget.ItemTouchHelper.Callback#onSwiped(RecyclerView.ViewHolder, int)
     * On swipe, the corresponding element is removed from the list.
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

}
