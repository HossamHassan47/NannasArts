package com.wordpress.hossamhassan47.nannasarts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Make call
        TextView txtMobile = findViewById(R.id.text_view_mobile);
        txtMobile.setPaintFlags(txtMobile.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getResources().getString(R.string.mobile)));

                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(HomeActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);
                } else {
                    // Already have permission
                    try {
                        startActivity(callIntent);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Send Email
        TextView txtEmail = findViewById(R.id.text_view_email);
        txtEmail.setPaintFlags(txtEmail.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                        Uri.fromParts("mailto", getResources().getString(R.string.email), null));

                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Nanna's Arts");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "** type your message here **");

                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        // Blog
        TextView txtBlog = findViewById(R.id.text_view_blog);
        txtBlog.setPaintFlags(txtBlog.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://" + getResources().getString(R.string.blog);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        // Facebook
        TextView txtFacebook = findViewById(R.id.text_view_facebook);
        txtFacebook.setPaintFlags(txtFacebook.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/" + getResources().getString(R.string.facebook);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        // Twitter
        TextView txtTwitter = findViewById(R.id.text_view_twitter);
        txtTwitter.setPaintFlags(txtTwitter.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://twitter.com/" + getResources().getString(R.string.twitter);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        // Instagram
        TextView txtInstagram = findViewById(R.id.text_view_instagram);
        txtInstagram.setPaintFlags(txtInstagram.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.instagram.com/" + getResources().getString(R.string.instagram);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        // Snapchat
        TextView txtSnapchat = findViewById(R.id.text_view_snapchat);
        txtSnapchat.setPaintFlags(txtSnapchat.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtSnapchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("*/*");
                    intent.setPackage("com.snapchat.android");
                    startActivity(Intent.createChooser(intent, "Open Snapchat"));
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(),
                            "Oops, Snapchat not installed on this device.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the phone call
                    Toast.makeText(getApplicationContext(),
                            "Access granted, try again now please.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(),
                            "Oops, Access denied.",
                            Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
