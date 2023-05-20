package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;
import java.util.ArrayList;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher extends ApplicationAdapter {
    static SpriteBatch batch;
    static Texture menu;

    static Texture loading;
    boolean start = false;
    Level_1 hz2 = new Level_1();
    static Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    public static Lwjgl3Application hz;

    AssetManager manager = new AssetManager();

    public static boolean exit = false;

    public static void main(String[] args) {
        Graphics.DisplayMode dm = Lwjgl3ApplicationConfiguration.getDisplayMode();
        config.setWindowedMode(dm.width, dm.height);
        hz = new Lwjgl3Application(new DesktopLauncher(), config);
    }

    @Override
    public void create() {
        menu = new Texture("Menu_text.png");
        batch = new SpriteBatch();
        loading = new Texture("Loading_text.png");
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 0);
        if ((Gdx.input.isKeyPressed(Input.Keys.F) || Gdx.input.isKeyPressed(Input.Keys.J)) && !start) {
            hz2.create();
            start = true;
        }
        if (!start) {
            batch.begin();
            batch.draw(menu, 620, 360, 720, 300);
            batch.end();
        }
        if (start && !manager.update()) {
            batch.begin();
            batch.draw(loading, 720, 360);
            batch.end();
        }
        if (start && manager.update()) {
            hz2.render();
        }
    }

    @Override
    public void dispose(){
        batch.dispose();
    }
}
