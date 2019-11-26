package id.co.perhutani.sisdhbukuobor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;

public class LocalDatabaseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TableLayout tabelisidatabase;
    TextView txtapaajaaa;
    ArrayList<String> TableColumns;
    Spinner spinnersemuatabel;
    String semuatabel = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_database);
        //========================================================================================================================

        try {
            spinnersemuatabel = this.findViewById(R.id.spinnersemuatabel);
            tabelisidatabase =this.findViewById(R.id.tabelisidatabase);
            txtapaajaaa = this.findViewById(R.id.txtapaajaaa);
            txtapaajaaa.setVisibility(View.INVISIBLE);
            ArrayList<String> spinnerArray = new ArrayList<>();
            SQLiteHandler dbHelper = new SQLiteHandler(this);
            Cursor c = dbHelper.getReadableDatabase().rawQuery("SELECT name FROM sqlite_master WHERE name !='android_metadata' and name !='sqlite_sequence'", null);

            if (c.moveToFirst()) {
                while ( !c.isAfterLast() ) {
                    spinnerArray.add(c.getString(0));
                    c.moveToNext();
                }
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnersemuatabel.setAdapter(spinnerArrayAdapter);
            spinnersemuatabel.setOnItemSelectedListener(this);

        } catch (Exception ex) {
            Toast.makeText(this,
                    "Error in MainActivity.onCreate: " + ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void showTable(String TableName) {
        try {
            /* Get Readable DB */
            SQLiteHandler DB_Helper = new SQLiteHandler(this);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();

            /* Select All Table's Data */
            Cursor cur = null;
            cur = db.rawQuery("SELECT * FROM [" + TableName + "]", null);
//            cur = db.rawQuery("SELECT * FROM TRN_PERSEDIAAN WHERE QR_CODE_POTONGAN is not null", null);

            /* Clean TblGrid Up, excluding a last child, i.e. "Add" button */
            tabelisidatabase.removeViews(0, tabelisidatabase.getChildCount() - 1);
            cur.moveToPosition(0);
            TableRow tableHeader = new TableRow(this);
            TableColumns = new ArrayList<String>();
            for (int i = 0; i < cur.getColumnCount(); i++) {

                TextView columnHeader = new TextView(this);
                columnHeader.setBackgroundColor(Color.rgb(195, 195, 195));
                columnHeader.getBackground().setAlpha(20);
                columnHeader.setGravity(Gravity.CENTER_HORIZONTAL);
                columnHeader.setText(cur.getColumnName(i));
                columnHeader.setTextSize(15);
                columnHeader.setPadding(10, 5, 10, 5);
                tableHeader.addView(columnHeader);
                TableColumns.add(cur.getColumnName(i));
            }
            tabelisidatabase.addView(tableHeader, 0);

            /* Iterate All Rows And Create The Grid Rows */

            for (int i = 0; i < cur.getCount(); i++) {

                TableRow tableRow = new TableRow(this);

                for (int j = 0; j < cur.getColumnCount()-1; j++) {
                    TextView editText = new TextView(this);
                    editText.setGravity(Gravity.CENTER_HORIZONTAL);
                    editText.setText(cur.getString(j));
                    editText.setTextSize(15);
                    editText.setPadding(10, 5, 10, 5);
                    tableRow.addView(editText);
                }
                tableRow.setPadding(10, 5, 10, 5);
                tabelisidatabase.addView(tableRow, tabelisidatabase.getChildCount() - 1);
                cur.moveToNext();
            }
            cur.close();
            db.close();

            txtapaajaaa.setEnabled(true);
        } catch (Exception ex) {
            Toast.makeText(this,
                    "Error in MainActivity.showTable: " + ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        semuatabel = parent.getItemAtPosition(position).toString();
        showTable(semuatabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        tabelisidatabase.removeViews(0, tabelisidatabase.getChildCount() - 1);
        txtapaajaaa.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LocalDatabaseActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
