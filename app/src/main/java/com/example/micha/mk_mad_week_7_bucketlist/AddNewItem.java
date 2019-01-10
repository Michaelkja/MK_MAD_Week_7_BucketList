package com.example.micha.mk_mad_week_7_bucketlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.widget.CheckBox;
import android.widget.EditText;

public class AddNewItem {
    private BucketListItemViewModel bucketListItemViewModel;
    private EditText editTextName;
    private EditText editTextDescription;
    private CheckBox checkBoxCompleted;
    private FloatingActionButton actionButton;
    private BucketListItem item;
    private boolean add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        editTextName = findViewById(R.id.edit_item_name);
        editTextDescription = findViewById(R.id.edit_item_description);
        checkBoxCompleted = findViewById(R.id.edit_item_checkBox);
        actionButton = findViewById(R.id.actionButton);

        bucketListItemViewModel = ViewModelProviders.of(this).get(BucketListItemViewModel.class);
        Intent intent = getIntent();

        if (intent.getSerializableExtra("Item") != null){
            item = (BucketListItem) intent.getSerializableExtra("Item");
            add = false;
            editTextName.setText(item.getName());
            editTextDescription.setText(item.getDescription());
            checkBoxCompleted.setChecked(item.getChecked());
        } else {
            add = true;

        }

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(add){
                    bucketListItemViewModel.insert(new BucketListItem(
                            editTextName.getText().toString(),
                            editTextDescription.getText().toString(),
                            checkBoxCompleted.isChecked()
                    ));
                    finish();
                }else{
                    item.setName(editTextName.getText().toString());
                    item.setDescription(editTextDescription.getText().toString());
                    item.setChecked(checkBoxCompleted.isChecked());
                    bucketListItemViewModel.update(item);
                    finish();
                }
            }
        });
    }
}

