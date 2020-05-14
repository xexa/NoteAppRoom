package com.example.noteapproom;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.example.noteapproom.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.noteapproom.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.noteapproom.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.noteapproom.EXTRA_PRIORITY";

    private EditText titleEditText;
    private EditText descEditText;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleEditText = findViewById(R.id.title_edit_text);
        descEditText = findViewById(R.id.desc_edit_text);
        numberPickerPriority = findViewById(R.id.number_picker);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);
        numberPickerPriority.setValue(5);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");

            titleEditText.setText(intent.getStringExtra(EXTRA_TITLE));
            descEditText.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }else {
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.save_note) {
            saveNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {
        String title = titleEditText.getText().toString().trim();
        String desc = descEditText.getText().toString().trim();
        int priority = numberPickerPriority.getValue();

        if (title.isEmpty() || desc.isEmpty()) {
            Toast.makeText(this, "Please enter title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent replyIntent = new Intent();

        replyIntent.putExtra(EXTRA_TITLE, title);
        replyIntent.putExtra(EXTRA_DESCRIPTION, desc);
        replyIntent.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID , -1);

        if (id != -1){
            replyIntent.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK,replyIntent);
        finish();

    }

}
