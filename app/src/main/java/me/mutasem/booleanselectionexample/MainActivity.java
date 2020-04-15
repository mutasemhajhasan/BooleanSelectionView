package me.mutasem.booleanselectionexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import me.mutasem.booleanselection.BooleanSelectionView;

public class MainActivity extends AppCompatActivity {
    BooleanSelectionView gender, martial, onOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gender = findViewById(R.id.gender);
        martial = findViewById(R.id.martial);
        onOff = findViewById(R.id.onOff);
        gender.setSelection(BooleanSelectionView.Selection.End);
        gender.setSelectionListener(new BooleanSelectionView.SelectionListener() {
            @Override
            public void onSelectionChanged(int selection, String selectedText) {
                //selection is one off:
                // (BooleanSelectionView.Selection.None, BooleanSelectionView.Selection.End, BooleanSelectionView.Selection.Start)
                Toast.makeText(MainActivity.this, selectedText, Toast.LENGTH_SHORT).show();
            }
        });
        martial.setSelectionListener(new BooleanSelectionView.SelectionListener() {
            @Override
            public void onSelectionChanged(int selection, String selectedText) {
                Toast.makeText(MainActivity.this, selectedText, Toast.LENGTH_SHORT).show();
            }
        });
        onOff.setSelectionListener(new BooleanSelectionView.SelectionListener() {
            @Override
            public void onSelectionChanged(int selection, String selectedText) {
                Toast.makeText(MainActivity.this, selectedText, Toast.LENGTH_SHORT).show();
            }
        });
        // you could use the getSelection() to get the current selection
        // the return value is one off :
        // (BooleanSelectionView.Selection.None, BooleanSelectionView.Selection.End, BooleanSelectionView.Selection.Start)
    }
}
