package com.example.visualcomponents;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class ItemManager {
    private final Set<String> itemList;
    private final LinearLayout linearLayoutItems;
    private final Context context;

    public ItemManager(Context context, LinearLayout linearLayoutItems) {
        this.itemList = new HashSet<>();
        this.linearLayoutItems = linearLayoutItems;
        this.context = context;
    }

    public void addItem(final String item) {
        if (itemList.contains(item)) {
            Toast toast = Toast.makeText(context, "O item ao qual você deseja adicionar já consta na lista", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        itemList.add(item);
        final TextView textView = new TextView(context);
        textView.setText(item);
        textView.setTextSize(18);
        textView.setPadding(16, 16, 16, 16);
        textView.setBackgroundResource(R.drawable.edit_text_background);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutItems.removeView(textView);
                itemList.remove(item);
                Toast toast = Toast.makeText(context, "Item " + item + " removido da lista!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        linearLayoutItems.addView(textView);
        Toast toast = Toast.makeText(context, "Item " + item + " adicionado à lista", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void clearItems() {
        linearLayoutItems.removeAllViews();
        itemList.clear();
        Toast toast = Toast.makeText(context, "Todos os itens foram removidos da lista!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void searchItem(String query) {
        for (int i = 0; i < linearLayoutItems.getChildCount(); i++) {
            View view = linearLayoutItems.getChildAt(i);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                if (textView.getText().toString().toLowerCase().contains(query.toLowerCase())) {
                    textView.setVisibility(View.VISIBLE);
                } else {
                    textView.setVisibility(View.GONE);
                }
            }
        }
    }
}
