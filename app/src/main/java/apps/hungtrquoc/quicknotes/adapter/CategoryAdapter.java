package apps.hungtrquoc.quicknotes.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import apps.hungtrquoc.quicknotes.R;
import apps.hungtrquoc.quicknotes.adapter.template.ModelAdapter;
import apps.hungtrquoc.quicknotes.model.Category;
import apps.hungtrquoc.quicknotes.widget.CategoryViewHolder;

public class CategoryAdapter extends ModelAdapter<Category, CategoryViewHolder> {
	public CategoryAdapter(ArrayList<Category> items, ArrayList<Category> selected, ClickListener<Category> listener) {
		super(items, selected, listener);
	}

	@Override
	public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false));
	}
}
