package com.example.shopsandroidwebadminpanel;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopsandroidwebadminpanel.api.services.ProductService;
import com.example.shopsandroidwebadminpanel.dto.ProductAddDTO;
import com.example.shopsandroidwebadminpanel.dto.ProductDTO;
import com.google.android.material.card.MaterialCardView;

import java.io.ByteArrayOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    MainActivity activity;
    ProductDTO model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }
            Uri uri = data.getData();
            ImageView imageView = findViewById(R.id.imagepost);
            imageView.setImageURI(uri);
        }
    }

    ActivityResultLauncher<Intent> someActivityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                }
            });

    public void choosefile(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        someActivityResultLaunch.launch(intent);
    }

    public void PostFunc(View v) {
        EditText nameText = findViewById(R.id.nametext);
        EditText priceText = findViewById(R.id.pricetext);
        EditText desText = findViewById(R.id.destext);
        EditText typeText = findViewById(R.id.typetext);
        EditText linkText = findViewById(R.id.linktext);
        ImageView imageView = findViewById(R.id.imagepost);
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String name = nameText.getText().toString();
        String base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
        String desc = desText.getText().toString();
        double price = Double.parseDouble(priceText.getText().toString());
        String type = typeText.getText().toString();
        String link = linkText.getText().toString();
        ProductAddDTO productaddDTO = new ProductAddDTO(name, desc, price, base64, type, link, "PRODUCT");
        ProductService.getInstance()
                .getProductsApi()
                .post(productaddDTO)
                .enqueue(new Callback<ProductAddDTO>() {
                    @Override
                    public void onResponse(@NonNull Call<ProductAddDTO> call, @NonNull Response<ProductAddDTO> response) {
                        Toast.makeText(getApplicationContext(), "CODE: " + response.code(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(@NonNull Call<ProductAddDTO> call, @NonNull Throwable t) {

                    }
                });

    }

    public void GetWithIdFunc(View v) {
        MaterialCardView card = findViewById(R.id.card);

        EditText idText = findViewById(R.id.editgetid);

        TextView prodName = findViewById(R.id.prodname);
        TextView prodPrice = findViewById(R.id.prodprice);
        TextView prodDescription = findViewById(R.id.proddescription);
        ImageView prodImage = findViewById(R.id.prodimg);
        Long id = Long.parseLong(idText.getText().toString());

        ProductService
                .getInstance()
                .getProductsApi()
                .getWithId(id)
                .enqueue(new Callback<ProductDTO>() {
                    @Override
                    public void onResponse(@NonNull Call<ProductDTO> call, @NonNull Response<ProductDTO> response) {
                        ProductDTO dto = response.body();
                        byte[] decodedString = Base64.decode(dto.getImage(), Base64.DEFAULT);
                        Bitmap btmp = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        prodName.setText(dto.getName());
                        prodDescription.setText(dto.getDescription());
                        prodPrice.setText(dto.getPrice());
                        prodImage.setImageBitmap(btmp);
                        card.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(@NonNull Call<ProductDTO> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void DeleteFunc(View v) {
        EditText delText = findViewById(R.id.edittextdelete);
        String name = String.valueOf(delText.getText());

        MaterialCardView card = findViewById(R.id.carddel);
        ConstraintLayout submitarea = findViewById(R.id.submitarea);

        ImageView prodImageDel = findViewById(R.id.prodimgdel);
        TextView prodNameDel = findViewById(R.id.prodnamedel);
        TextView prodPriceDel = findViewById(R.id.prodpricedel);
        TextView prodDescDel = findViewById(R.id.proddescdel);
        ProductService.getInstance()
                .getProductsApi()
                .getWithName(name)
                .enqueue(new Callback<ProductDTO>() {
                    @Override
                    public void onResponse(Call<ProductDTO> call, Response<ProductDTO> response) {
                        ProductDTO dto = response.body();
                        byte[] decoded = Base64.decode(dto.getImage(), Base64.DEFAULT);
                        Bitmap btmp = BitmapFactory.decodeByteArray(decoded, 0, decoded.length);
                        prodImageDel.setImageBitmap(btmp);
                        prodNameDel.setText(dto.getName());
                        prodPriceDel.setText(dto.getPrice());
                        prodDescDel.setText(dto.getDescription());
                        card.setVisibility(View.VISIBLE);
                        submitarea.setVisibility(View.VISIBLE);
                        model = dto;
                    }

                    @Override
                    public void onFailure(Call<ProductDTO> call, Throwable t) {

                    }
                });
    }

    public void DeleteSwitch(View v) {
        ConstraintLayout deleteLayout = findViewById(R.id.deletefragment);
        ConstraintLayout postLayout = findViewById(R.id.postfragment);
        ConstraintLayout getidlayout = findViewById(R.id.getidfragment);
        ConstraintLayout submitarea = findViewById(R.id.submitarea);
        MaterialCardView card = findViewById(R.id.card);

        deleteLayout.setVisibility(View.VISIBLE);
        postLayout.setVisibility(View.INVISIBLE);
        getidlayout.setVisibility(View.INVISIBLE);
        submitarea.setVisibility(View.INVISIBLE);
        card.setVisibility(View.INVISIBLE);
    }

    public void PostSwitch(View v) {
        ConstraintLayout deleteLayout = findViewById(R.id.deletefragment);
        ConstraintLayout postLayout = findViewById(R.id.postfragment);
        ConstraintLayout getidlayout = findViewById(R.id.getidfragment);
        ConstraintLayout submitarea = findViewById(R.id.submitarea);
        MaterialCardView card = findViewById(R.id.card);

        deleteLayout.setVisibility(View.INVISIBLE);
        postLayout.setVisibility(View.VISIBLE);
        getidlayout.setVisibility(View.INVISIBLE);
        card.setVisibility(View.INVISIBLE);
        submitarea.setVisibility(View.INVISIBLE);
    }

    public void GetIdSwitch(View v) {
        ConstraintLayout deleteLayout = findViewById(R.id.deletefragment);
        ConstraintLayout postLayout = findViewById(R.id.postfragment);
        ConstraintLayout getidlayout = findViewById(R.id.getidfragment);
        ConstraintLayout submitarea = findViewById(R.id.submitarea);
        MaterialCardView card = findViewById(R.id.card);

        deleteLayout.setVisibility(View.INVISIBLE);
        postLayout.setVisibility(View.INVISIBLE);
        getidlayout.setVisibility(View.VISIBLE);
        submitarea.setVisibility(View.INVISIBLE);
        card.setVisibility(View.INVISIBLE);
        submitarea.setVisibility(View.GONE);
    }

    public void GetNameSwitch(View v) {

    }

    public void YesButton(View v) {
        MaterialCardView carddel = findViewById(R.id.carddel);
        carddel.setVisibility(View.GONE);
        ConstraintLayout submitarea = findViewById(R.id.submitarea);

        String name = model.getName();
        ProductService.getInstance()
                .getProductsApi()
                .delete(name)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        Toast.makeText(getApplicationContext(), "CODE: " + response.code(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
    }

    public void NoButton(View v) {
        MaterialCardView card = findViewById(R.id.carddel);
        ConstraintLayout submitarea = findViewById(R.id.submitarea);

        card.setVisibility(View.GONE);
        submitarea.setVisibility(View.GONE);
    }
}