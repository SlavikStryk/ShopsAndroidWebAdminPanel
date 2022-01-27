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
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shopsandroidwebadminpanel.api.services.ProductService;
import com.example.shopsandroidwebadminpanel.dto.ProductAddDTO;
import com.example.shopsandroidwebadminpanel.dto.ProductDTO;

import java.io.ByteArrayOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    MainActivity activity;

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
        ImageView imageView = findViewById(R.id.imagepost);
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
        String name = nameText.getText().toString();
        String desc = desText.getText().toString();
        String price = priceText.getText().toString();
        String type = typeText.getText().toString();
        String link = typeText.getText().toString();
        switch (name) {
            case "SSD Kingston A400 120GB":
                link = "/kingston a 400 120 gb";
                break;
            case "SSD KINGSTON A400 240GB":
                link = "/kingston a 400 240 gb";
                break;
            case "SSD KINGSTON A400 480GB":
                link = "/kingston a 400 480 gb";
                break;
            case "SSD KINGSTON A400 960GB":
                link = "/kingston a 400 960 gb";
                break;
            case "SSD KINGSTON A400 1.92TB":
                link = "/kingston a 400 1 92 tb";
                break;
            case "SSD KINGSTON FURY RGB 240GB":
                link = "/kingston hyperx fury rgb 240 gb";
                break;
            case "SSD KINGSTON FURY RGB 480GB":
                link = "/kingston hyperx fury rgb 480 gb";
                break;
            case "SSD KINGSTON FURY RGB 960GB":
                link = "/kingston hyperx fury rgb 960 gb";
                break;
            case "SSD KINGSTON FURY 120GB":
                link = "/kingston hyperx fury 120 gb";
                break;
            case "SSD KINGSTON FURY 240GB":
                link = "/kingston hyperx fury 240 gb";
                break;
            case "SSD KINGSTON FURY 480GB":
                link = "/kingston hyperx fury 480 gb";
                break;
            case "CPU Intel Core i3-10100 3.6GHz":
                link = "/intel core i 3 10100 box 3 6 G h z";
                break;
            case "CPU Intel Core i5-10600KF 4.1 GHz":
                link = "/intel core i 5 10600  K F box 4 1 G h z";
                break;
            case "CPU Intel Core i9-10900K 3.7 GHz":
                link = "/intel core i 9 10900 K box 3 7 G h z";
                break;
            case "CPU AMD Ryzen 3 1200 3.2 GHz":
                link = "/amd ryzen 3 1200 sAM4 box 3 2 G h z";
                break;
            case "CPU AMD Ryzen 5 1600 3.2 GHz":
                link = "/amd ryzen 5 1600 sAM4 box 3 2 G h z";
                break;
            case "CPU AMD Ryzen 5 3500 3.2 GHz":
                link = "/amd ryzen 5 3500 sAM4 box 3 2 G h z";
                break;
            case "GPU Nitro + RX 470 4G GDDR5":
                link = "/amd sapphire radeon nitro rx 470 4 g b";
                break;
            case "GPU RX 570 8192Mb NITRO+":
                link = "/amd sapphire radeon nitro rx 570 8 g b";
                break;
            case "GPU Sapphire RX 580 Nitro+":
                link = "/amd sapphire radeon nitro rx 580 4 g b";
                break;
            case "GPU GeForce GTX 770 4096MB GDDR5":
                link = "/asus nvidia gtx 770 4096 g b";
                break;
            case "GPU GeForce GT1030 2048Mb OC":
                link = "/asus nvidia gt 1030 2 g b";
                break;
            case "GPU MSI GTX 1050Ti 4Gb Gaming X":
                link = "/msi nvidia gtx 1050 4 g b";
                break;
            case "RAM Kingston Fury DDR4-3200 4096 MB":
                link = "/kingston hyperx fury 1 x 4 gb 3200";
                break;
            case "RAM Kingston Fury DDR4-3200 2x4096 MB":
                link = "/kingston hyperx fury 2 x 4 gb 3200";
                break;
            case "RAM Kingston Fury DDR4-3200 2x8192 MB":
                link = "/kingston hyperx fury rgb 2 x 8 gb 3200";
                break;
            case "RAM Kingston Fury RGB DDR4-3200 4x8192 MB":
                link = "/kingston hyperx fury rgb 4 x 8 gb 3200";
                break;
            case "PSU be quiet! Dark Power Pro 11 550W":
                link = "/be quiet dark power pro 11 5 5 0 w";
                break;
            case "PSU be quiet! Dark Power Pro 11 750W":
                link = "/be quiet dark power pro 11 7 5 0 w";
                break;
            case "PSU be quiet! Dark Power Pro 11 1200W":
                link = "/be quiet dark power pro 11 1 2 0 0 w";
                break;
            case "PSU GameMax GM-400-8CM 400W":
                link = "/gamemax 4 0 0 w gm - 4 0 0 - 8 c м";
                break;
            case "PSU GameMax GM-600 600W":
                link = "/gamemax 6 0 0 w gm - 6 0 0 - 8 c м";
                break;
            case "PSU GameMax GM-800 800W":
                link = "/be quiet dark power pro 11 1 2 0 0 w";
                break;
            case "WATERCOOLING ID-COOLING SE-903-B":
                link = "/id cooling se 903 b blue";
                break;
            case "WATERCOOLING ID-COOLING SE-914-XT Basic":
                link = "/id cooling se 914 xt basic";
                break;
            case "WATERCOOLING ID-COOLING SE-207-XT Black":
                link = "/id cooling se 207 xt black";
                break;
            case "WATERCOOLING ID-COOLING Auraflow X 240 Snow":
                link = "/id cooling auraflow x 240 snow";
                break;
            case "WATERCOOLING ID-COOLING Frostflow X360":
                link = "/id cooling frostflow x 360";
                break;
            case "WATERCOOLING ID-COOLING Zoomflow 360XT":
                link = "/id cooling zoomflow 360 xt";
                break;
        }
        Toast.makeText(getApplicationContext(), link, Toast.LENGTH_SHORT).show();
        ProductAddDTO productaddDTO = new ProductAddDTO();
        productaddDTO.setName(name);
        productaddDTO.setImage(base64);
        productaddDTO.setPrice(Double.parseDouble(price));
        productaddDTO.setDescription(desc);
        productaddDTO.setType_product(type);
        productaddDTO.setLink(link);
        productaddDTO.setIdentityANDROID("PRODUCT");
        ProductService.getInstance()
                .getProductsApi()
                .post(productaddDTO)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getApplicationContext(), "CODE: " + response.code(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

    }

    public void Deleteswitch(View v) {
        ConstraintLayout deleteLayout = findViewById(R.id.deletefragment);
        ConstraintLayout postLayout = findViewById(R.id.postfragment);


        deleteLayout.setVisibility(View.VISIBLE);
        postLayout.setVisibility(View.INVISIBLE);
    }

    public void Postswitch(View v) {
        ConstraintLayout deleteLayout = findViewById(R.id.deletefragment);
        ConstraintLayout postLayout = findViewById(R.id.postfragment);


        deleteLayout.setVisibility(View.INVISIBLE);
        postLayout.setVisibility(View.VISIBLE);
    }
}