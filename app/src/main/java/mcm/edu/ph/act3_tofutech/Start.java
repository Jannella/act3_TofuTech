package mcm.edu.ph.act3_tofutech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start extends AppCompatActivity implements View.OnClickListener {

    Button start;
    MediaPlayer click, bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start = findViewById(R.id.btnStart);

        bg = MediaPlayer.create(this, R.raw.bgmus);
        bg.setLooping(true);
        bg.start();

        start.setOnClickListener(this);
    }
    public void onClick(View v){
        click = MediaPlayer.create(this, R.raw.clicc);
        click.start();
        switch (v.getId()){
            case R.id.btnStart:
                Intent go = new Intent(Start.this, next.class);
                startActivity(go);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                break;
        }
    }
}