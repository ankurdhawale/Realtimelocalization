package c.ankur.realtimelocalization;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button changelang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changelang=findViewById(R.id.changelang);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));


    changelang.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showchangelanguagedialogue();

        }
    });
    }

    private void showchangelanguagedialogue() {
        final String[] listitems={"English","हिंदी","मराठी"};
        final AlertDialog.Builder mbuilder= new AlertDialog.Builder(MainActivity.this);
        mbuilder.setTitle("Change Languages");
        mbuilder.setSingleChoiceItems(listitems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(i==0){
                    setLocale("En");
                    recreate();
                }
                else if (i==1){
                    setLocale("hi");
                    recreate();
                }
                else if (i==2){
                    setLocale("mr");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog mdialog = mbuilder.create();
        mdialog.show();
    }
    private void setLocale(String lang) {
        Locale locale= new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale=locale;
       getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor= getSharedPreferences("Setting",MODE_PRIVATE).edit();
        editor.putString("My_lang",lang);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang","");
        setLocale(language);
    }
}
