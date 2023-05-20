package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Level_1 extends ApplicationAdapter {
    static SpriteBatch batch;
    private static Texture map_01;
    private static Texture bullet;
    private static Texture img;
    private static Animation<TextureRegion> bg;
    private Music lvl1_sng;
    private static Player_01 player;
    private static Lvl1_bullet_pattern pattern;
    private static Hp_bar hp;
    static ArrayList<Bullets_01> buffer_patt = new ArrayList<>();
    static ArrayList<Bullets_01> hz = new ArrayList<>();
    boolean stop = false;
    boolean debug = false;
    ArrayList<Long> debug_cout = new ArrayList<>();
    long debug_frame_cnt = 0;
    long finish_frame = 5410;
    private float anim_frame;

    private void finish() throws IOException {
        if (debug) {
            FileOutputStream FOuS = new FileOutputStream("debug");
            for (int i = 0; i < debug_cout.size(); i++) {
                FOuS.write((debug_cout.get(i) + " ").getBytes(StandardCharsets.UTF_8));
            }
            FOuS.write((int) (debug_frame_cnt));
            debug_cout.clear();
            FOuS.close();
        }
        ScreenUtils.clear(0, 0, 0, 0);
        lvl1_sng.stop();
        img = new Texture("Button.png");
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.app.exit();

    }

    private void restart() {
        stop = true;
        pattern = new Lvl1_bullet_pattern();
        debug_frame_cnt = 0;
        hz.clear();
        hz = pattern.pattern;
        recreate();
    }

    private void recreate() {
        lvl1_sng.setPosition(0);
        stop = false;

    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("Button.png");
        map_01 = new Texture("map.png");
        bg = com.holidaystudios.tools.GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("0_10029c_465b875_orig.gif").read());
        bullet = new Texture("projectile.png");
        player = new Player_01(img, map_01);
        hp = new Hp_bar();
        pattern = new Lvl1_bullet_pattern();
        buffer_patt.addAll(pattern.pattern);
        hz.clear();
        hz.addAll(buffer_patt);
        lvl1_sng = Gdx.audio.newMusic(Gdx.files.internal("568369_The-Hallucination.mp3"));
        lvl1_sng.play();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 0);
        anim_frame += Gdx.graphics.getDeltaTime();
        batch.begin();
        batch.draw(bg.getKeyFrame(anim_frame), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player.Draw(batch);
        if (!stop) {
            pattern.draw(batch, hz);
        }
        if (debug) {
            if ((Gdx.input.isKeyJustPressed(Input.Keys.J) || (Gdx.input.isKeyJustPressed(Input.Keys.F)))) {
                debug_cout.add(debug_frame_cnt);
            }
        }
        debug_frame_cnt++;
        if (debug_frame_cnt == finish_frame) {
            try {
                finish();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        hp.idi_nahui(batch);
        batch.end();
        if (hp.hp <= 0 && !debug) {
            restart();
            hp.hp = 100;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        //batch.dispose();
        player.dispose();
        bullet.dispose();
        img.dispose();
        Hp_bar.hp_b.dispose();
        map_01.dispose();
        batch.dispose();
    }
}
