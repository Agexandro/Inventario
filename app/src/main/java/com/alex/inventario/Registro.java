package com.alex.inventario;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    private SqliteHelper sqliteHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        sqliteHelper = new SqliteHelper(this);
    }
    public void onSaveExit (View view) {
        EditText name_input = (EditText) findViewById(R.id.name_input);
        EditText details_input = (EditText) findViewById(R.id.detail_input);
        EditText lote_input = (EditText) findViewById(R.id.lote_input);
        EditText caducity_input = (EditText) findViewById(R.id.caducity_input);
        EditText quantity_input = (EditText) findViewById(R.id.quantity_input);
        EditText key_input = (EditText) findViewById(R.id.key_input);

        Intent intent = getIntent();
        intent.putExtra(SqliteConstants.COL_NAME,name_input.getText().toString());
        intent.putExtra(SqliteConstants.COL_DETAILS,details_input.getText().toString());
        intent.putExtra(SqliteConstants.COL_LOTE,lote_input.getText().toString());
        intent.putExtra(SqliteConstants.COL_CADUCITY,caducity_input.getText().toString());
        intent.putExtra(SqliteConstants.COL_QUANTITY,quantity_input.getText().toString());
        intent.putExtra(SqliteConstants.COL_KEY,key_input.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onSave(View view){
        EditText name_input = (EditText) findViewById(R.id.name_input);
        EditText details_input = (EditText) findViewById(R.id.detail_input);
        EditText lote_input = (EditText) findViewById(R.id.lote_input);
        EditText caducity_input = (EditText) findViewById(R.id.caducity_input);
        EditText quantity_input = (EditText) findViewById(R.id.quantity_input);
        EditText key_input = (EditText) findViewById(R.id.key_input);
        sqliteHelper.saveItem(
                name_input.getText().toString(),
                details_input.getText().toString(),
                lote_input.getText().toString(),
                caducity_input.getText().toString(),
                quantity_input.getText().toString(),
                key_input.getText().toString()
        );
        Toast.makeText(this,"Guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    public void onCancel (View view)  {
        finish();
    }
}