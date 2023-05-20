package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hp_bar {
    public static float hp = 100;
    public static Texture hp_b = new Texture(Gdx.files.internal("health_bar.png"));
    public static Sprite hp_bar = new Sprite(hp_b);
    public void idi_nahui(SpriteBatch batch){
        //hp_structure.setPosition(80,400);
        //hp_structure.setScale(5,5);
        hp_bar.setColor(1,1,1,1);
        hp_bar.setPosition(-27,-20);
        hp_bar.setScale(0.5f,450/100*hp);
        //hp_structure.draw(batch);
        hp_bar.draw(batch);
    }
}
