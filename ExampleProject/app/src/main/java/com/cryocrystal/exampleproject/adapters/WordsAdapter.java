package com.cryocrystal.exampleproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cryocrystal.exampleproject.R;
import com.cryocrystal.exampleproject.models.Word;

public class WordsAdapter extends ArrayAdapter<Word> {
    private static final int layoutResource = R.layout.item_words;
    private boolean inEditMode = false;

    public WordsAdapter(Context context) {
        super(context, layoutResource);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(layoutResource, parent, false);
        }
        final Word word = getItem(position);
        TextView wordName = (TextView) convertView.findViewById(R.id.word_text_view);
        wordName.setText(word.getName());


        Button buttonDelete = (Button) convertView.findViewById(R.id.delete_word_button);
        buttonDelete.setVisibility(inEditMode ? View.VISIBLE : View.GONE);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(word);
            }
        });
        return convertView;
    }

    public void setEditMode(boolean editMode) {
        this.inEditMode = editMode;
        notifyDataSetChanged();
    }
}
