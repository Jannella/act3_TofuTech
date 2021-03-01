package mcm.edu.ph.act3_tofutech;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Hero hero = new Hero("Albert Einstein", 1200, 85, 100);
    Monster monster = new Monster("Charles Darwin", 1000, 100, 130);

    TextView txtMonsName, txtHeroName, txtMonsHP, txtHeroHP, txtHeroDPT, txtMonsDPT, txtCombatLog, txtView;
    Button nextTurn;
    GifImageView player, enemy;
    SoundPool soundPool;
    int playerpunch, enemypunch, finalpunch;
    int playerStreamId, enemyStreamId, finalStreamId;

    int turnNumber = 1;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        }
        playerpunch = soundPool.load(this, R.raw.albertpunches,1);
        enemypunch = soundPool.load(this, R.raw.charlespunches,1);
        finalpunch = soundPool.load(this, R.raw.lastpunch,1);

        nextTurn = findViewById(R.id.btnNextTurn);

        txtMonsName = findViewById(R.id.txtEnemy_name);
        txtHeroName = findViewById(R.id.txtPlayer_name);
        txtMonsHP = findViewById(R.id.txtEnemy_hp);
        txtHeroHP = findViewById(R.id.txtPlayer_hp);
        txtHeroDPT = findViewById(R.id.txtPlayerDPT);
        txtMonsDPT = findViewById(R.id.txtEnemyDPT);

        txtHeroName.setText(hero.unitName);
        txtMonsName.setText(monster.unitName);
        txtHeroHP.setText(String.valueOf(hero.baseHealth));
        txtMonsHP.setText(String.valueOf(monster.baseHealth));
        txtHeroDPT.setText(hero.minDPT + " ~ " + hero.maxDPT);
        txtMonsDPT.setText(monster.minDPT + " ~ " + monster.maxDPT);

        player = findViewById(R.id.gifPlayer);
        enemy = findViewById(R.id.gifEnemy);
        player.setImageResource(R.drawable.einstein);
        enemy.setImageResource(R.drawable.darwin);

        nextTurn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Random randomizer = new Random();

        txtCombatLog = findViewById(R.id.txtTurnLog);
        txtView = findViewById(R.id.txtView);

        int heroDPT = randomizer.nextInt(hero.maxDPT - hero.minDPT) + hero.minDPT;
        int monsDPT = randomizer.nextInt(monster.maxDPT - monster.minDPT) + monster.minDPT;

        switch (v.getId()) {
            case R.id.btnNextTurn:
                if (turnNumber % 2 == 1) {

                    playerStreamId = soundPool.play(playerpunch,1,1,0,0,1);
                    soundPool.pause(enemyStreamId);

                    player.setImageResource(R.drawable.albertattack);
                    enemy.setImageResource(R.drawable.darwin);

                    monster.baseHealth = monster.baseHealth - heroDPT;
                    turnNumber++;
                    txtCombatLog.setText("Einstein dealt " + heroDPT + " damage to Darwin");
                    txtView.setText(" ");
                    txtMonsHP.setText(String.valueOf(monster.baseHealth));
                    nextTurn.setText("TAP TO ATTACK \n-enemy- ");
                    if (monster.baseHealth < 0) {

                        finalStreamId = soundPool.play(finalpunch,1,1,0,0,1);
                        soundPool.pause(playerStreamId);

                        player.setImageResource(R.drawable.einstein);
                        enemy.setImageResource(R.drawable.darwin);

                        txtCombatLog.setText("Einstein dealt " + heroDPT + " damage to Darwin");
                        txtView.setText("YOU WON!");
                        hero.baseHealth = 1200;
                        monster.baseHealth = 1000;
                        turnNumber = 1;
                        nextTurn.setText("Reset Game");
                    }
                } else if (turnNumber % 2 != 1) {

                    enemyStreamId = soundPool.play(enemypunch,1,1,0,0,1);
                    soundPool.pause(playerStreamId);

                    enemy.setImageResource(R.drawable.charlesattack);
                    player.setImageResource(R.drawable.einstein);

                    hero.baseHealth = hero.baseHealth - monsDPT;
                    turnNumber++;
                    txtCombatLog.setText("Darwin dealt " + monsDPT + " damage to Einstein");
                    txtView.setText(" ");
                    txtHeroHP.setText(String.valueOf(hero.baseHealth));
                    nextTurn.setText("TAP TO ATTACK \n-player- ");
                    if (hero.baseHealth < 0) {

                        finalStreamId = soundPool.play(finalpunch,1,1,0,0,1);
                        soundPool.pause(enemyStreamId);

                        player.setImageResource(R.drawable.einstein);
                        enemy.setImageResource(R.drawable.darwin);

                        txtCombatLog.setText("Darwin dealt " + monsDPT + " damage to Einstein");
                        txtView.setText("YOU LOST!");
                        hero.baseHealth = 1200;
                        monster.baseHealth = 1000;
                        turnNumber = 1;
                        nextTurn.setText("Reset Game");
                    }
                }
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}