package apps.hungtrquoc.quicknotes.widget.template;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import apps.hungtrquoc.quicknotes.R;
import apps.hungtrquoc.quicknotes.model.DatabaseModel;

abstract public class ModelViewHolder<T extends DatabaseModel> extends RecyclerView.ViewHolder {
	public View holder;
	public View selected;

	public ModelViewHolder(View itemView) {
		super(itemView);
		holder = itemView.findViewById(R.id.holder);
		selected = itemView.findViewById(R.id.selected);
	}

	public void setSelected(boolean status) {
		selected.setVisibility(status ? View.VISIBLE : View.GONE);
	}

	abstract public void populate(T item);
}
