package com.alex.inventario;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ListViewAdapter extends CursorAdapter {

    public ListViewAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_row, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name = (TextView) view.findViewById(R.id.name_output);
        TextView details = (TextView) view.findViewById(R.id.detail_output);
        TextView lote = (TextView) view.findViewById(R.id.lote_output);
        TextView caducity = (TextView) view.findViewById(R.id.caducity_output);
        TextView quantity = (TextView) view.findViewById(R.id.quantity_output);
        TextView key = (TextView) view.findViewById(R.id.key_output);

        name.setText(cursor.getString(cursor.getColumnIndex(SqliteConstants.COL_NAME)));
        details.setText(cursor.getString(cursor.getColumnIndex(SqliteConstants.COL_DETAILS)));
        lote.setText(cursor.getString(cursor.getColumnIndex(SqliteConstants.COL_LOTE)));
        caducity.setText(cursor.getString(cursor.getColumnIndex(SqliteConstants.COL_CADUCITY)));
        quantity.setText(cursor.getString(cursor.getColumnIndex(SqliteConstants.COL_QUANTITY)));
        key.setText(cursor.getString(cursor.getColumnIndex(SqliteConstants.COL_KEY)));
    }
}
