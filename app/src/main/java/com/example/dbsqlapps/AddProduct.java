package com.example.dbsqlapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddProduct extends AppCompatActivity {

    EditText mNameEditText;
    EditText mPriceEditText;
    Button mInsertBtn;
    EditText mKeywordEditText;
    Button mSearchBtn;
    TextView mPriceResult;
    Button mGetAllBtn;
    TextView mAllResult;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        mNameEditText = findViewById(R.id.edittext_name);
        mPriceEditText = findViewById(R.id.edittext_price);
        mInsertBtn = findViewById(R.id.btn_insert);
        mKeywordEditText = findViewById(R.id.edittext_search);
        mSearchBtn = findViewById(R.id.btn_search);
        mPriceResult = findViewById(R.id.price_result);
        mGetAllBtn = findViewById(R.id.btn_getAll);
        mAllResult = findViewById(R.id.all_result);

        mDatabaseHelper = new DatabaseHelper(this);

        mInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(AddProduct.this);
                insertData();
            }
        });

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPriceFromName(mKeywordEditText.getText().toString());
            }
        });

        mGetAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProduct.this, MainActivity.class);
                startActivity(intent);
                showAll();
            }
        });
    }

    public void showAll(){
        List<Product> products = mDatabaseHelper.getAllProducts();
        String result = "";
        for (int i=0; i<products.size();i++){
            Product product = products.get(i);
            result = result + product.getId()+",";
        }
        mAllResult.setText(result);
    }

    public void insertData(){
        Product product = new Product();
        product.setName(mNameEditText.getText().toString());
        product.setPrice(Integer.parseInt(mPriceEditText.getText().toString()));

        mDatabaseHelper.insertRecord(product);
        Toast.makeText(AddProduct.this, "success", Toast.LENGTH_SHORT).show();
    }

    public void showPriceFromName(String keyword){
        Product product = mDatabaseHelper.getProductFromName(keyword);
        mPriceResult.setText(String.valueOf(product.getPrice()));
    }
}