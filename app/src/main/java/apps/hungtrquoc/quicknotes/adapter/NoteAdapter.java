package apps.hungtrquoc.quicknotes.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import apps.hungtrquoc.quicknotes.R;
import apps.hungtrquoc.quicknotes.adapter.template.ModelAdapter;
import apps.hungtrquoc.quicknotes.model.Note;
import apps.hungtrquoc.quicknotes.widget.NoteViewHolder;

public class NoteAdapter extends ModelAdapter<Note, NoteViewHolder> {
	public NoteAdapter(ArrayList<Note> items, ArrayList<Note> selected, ClickListener<Note> listener) {
		super(items, selected, listener);
	}

	@Override
	public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false));
	}
}