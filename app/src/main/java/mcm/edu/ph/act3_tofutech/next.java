package mcm.edu.ph.act3_tofutech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class next extends AppCompatActivity implements View.OnClickListener {

    Button fight;
    MediaPlayer click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        fight = findViewById(R.id.btnFight);

        fight.setOnClickListener(this);

    }
    public void  onClick(View v){
        click = MediaPlayer.create(this, R.raw.clicc);
        click.start();
        switch (v.getId()){
            case R.id.btnFight:
                Intent go = new Intent(next.this, MainActivity.class);
                startActivity(go);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                break;
        }
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}