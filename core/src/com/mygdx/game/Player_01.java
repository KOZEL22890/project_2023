package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Player_01 extends ApplicationAdapter{
    private Vector2 pos_p;
    private Vector2 pos_p2;
    private Vector2 pos_m;
    public static Sprite player;
    public static Sprite player2;
    private Sprite map;
    private double speed = 110;
    private float a = 0.75f;
    private float a2 = 0.75f;
    public Player_01(Texture img, Texture map_01){
        player = new Sprite(img);
        player.setScale(3f);
        player2 = new Sprite(img);
        player2.setScale(3f);
        pos_p = new Vector2(Gdx.graphics.getWidth()/2-96,0);
        pos_p2 = new Vector2(Gdx.graphics.getWidth()/2+99,0);
        map = new Sprite(map_01);
        map.setScale(3f,15f);
        pos_m = new Vector2(Gdx.graphics.getWidth()/2-player.getWidth()/2,player.getScaleY()*player.getHeight()/2);
    }
    public void Update(float deltaTime){
        player.setAlpha(a);
        player2.setAlpha(a2);
        if(Gdx.input.isKeyPressed(Input.Keys.F)){
            if(Gdx.input.isKeyJustPressed(Input.Keys.F))Hp_bar.hp-=4.9f;
            if(a<1){
                a+=0.1f;
            }
        }
        else if(a>0.75&&!Gdx.input.isKeyPressed(Input.Keys.F))a-=0.05f;
        if(Gdx.input.isKeyPressed(Input.Keys.J)){
            if(Gdx.input.isKeyJustPressed(Input.Keys.J))Hp_bar.hp-=4.9f;
            if(a2<1){
                a2+=0.1f;
            }
        }
        else if(a2>0.75&&!Gdx.input.isKeyPressed(Input.Keys.J))a2-=0.05f;
    }
    public void Draw(SpriteBatch batch){
        Update(Gdx.graphics.getDeltaTime());
        player.setPosition(pos_p.x, pos_p.y);
        map.setPosition(pos_m.x, pos_m.y);
        player2.setPosition(pos_p2.x, pos_p2.y);
        player2.draw(batch);
        map.draw(batch);
        player.draw(batch);
    }
}
