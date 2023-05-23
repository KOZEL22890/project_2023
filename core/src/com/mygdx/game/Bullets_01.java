package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullets_01 {
    Texture b = new Texture(Gdx.files.internal("projectile.png"));
    Sprite bullet = new Sprite(b);
    float speed;
    float pos;
    boolean stop = false;
    Score score;

    public Bullets_01(float pos, float speed) {
        this.speed = speed;
        this.pos = pos;
        bullet.setScale(3);
        score = new Score();
    }

    public void spawn_bullet_left(SpriteBatch batch) {
        if (!stop) {
            Sprite bull = bullet;
            if (pos >= -64) {
                bull.setPosition(Gdx.graphics.getWidth() / 2 - 96, pos);
                if(pos<=1200) {
                    bull.draw(batch);
                }
                pos -= speed;
                if (pos < -64) {
                    score.Miss();
                    Hp_bar.hp -= 10;
                    bull.setPosition(Gdx.graphics.getWidth() / 2 - 96, -500);
                    stop = true;
                }
            }
            if (Player_01.player.getBoundingRectangle().overlaps(bull.getBoundingRectangle()) && Gdx.input.isKeyJustPressed(Input.Keys.F)) {
                stop = true;
                if(pos>-8&&pos<8)score.Perfect_plus();
                else if(pos>-16&&pos<16)score.Perfect();
                else if(pos>-24&&pos<28)score.Good();
                else score.Decent();
                bull.setPosition(Gdx.graphics.getWidth() / 2 - 96, -500);
                if (Hp_bar.hp < 100) {
                    Hp_bar.hp += 5.5f;
                }

            }
        }
        //}
    }

    public void spawn_hold_bullet_left(SpriteBatch batch) {
        if(!stop) {
            Sprite bull = bullet;
            if (pos >= -64) {
                bull.setPosition(Gdx.graphics.getWidth() / 2 - 96, pos);
                if (pos <= 1200) {
                    bull.draw(batch);
                }
                pos -= speed;
                if (pos < -64) {
                    Hp_bar.hp -= 10;
                    bull.setPosition(Gdx.graphics.getWidth() / 2 - 96, -500);
                    stop = true;
                }
            }
            if (Player_01.player.getBoundingRectangle().overlaps(bull.getBoundingRectangle()) && Gdx.input.isKeyPressed(Input.Keys.F)) {
                stop = true;
                score.Hold();
                bull.setPosition(Gdx.graphics.getWidth() / 2 - 96, -500);
                if (Hp_bar.hp < 100) {
                    Hp_bar.hp += 0.2;
                }
            }
        }
        //}
    }

    public void spawn_bullet_right(SpriteBatch batch) {
        if(!stop) {
            Sprite bull = bullet;
            if (pos >= -64) {
                bull.setPosition(Gdx.graphics.getWidth() / 2 + 99, pos);
                if (pos <= 1200) {
                    bull.draw(batch);
                }
                pos -= speed;
                if (pos < -64) {
                    score.Miss();
                    Hp_bar.hp -= 10;
                    bull.setPosition(Gdx.graphics.getWidth() / 2 + 99, -500);
                    stop = true;
                }
            }
            if (Player_01.player2.getBoundingRectangle().overlaps(bull.getBoundingRectangle()) && Gdx.input.isKeyJustPressed(Input.Keys.J)) {
                if(pos>-8&&pos<8)score.Perfect_plus();
                else if(pos>-16&&pos<16)score.Perfect();
                else if(pos>-24&&pos<28)score.Good();
                else score.Decent();
                if (Hp_bar.hp < 100) {
                    Hp_bar.hp += 5.5f;
                }
                bull.setPosition(Gdx.graphics.getWidth() / 2 + 99, -500);
                stop = true;
            }
        }
        //}
    }

    public void spawn_hold_bullet_right(SpriteBatch batch) {
        if(!stop) {
            Sprite bull = bullet;
            if (pos >= -64) {
                bull.setPosition(Gdx.graphics.getWidth() / 2 + 99, pos);
                if (pos <= 1200) {
                    bull.draw(batch);
                }
                pos -= speed;
                if (pos < -64) {
                    Hp_bar.hp -= 10;
                    bull.setPosition(Gdx.graphics.getWidth() / 2 + 99, -500);
                    stop = true;
                }
            }
            if (Player_01.player2.getBoundingRectangle().overlaps(bull.getBoundingRectangle()) && Gdx.input.isKeyPressed(Input.Keys.J)) {
                score.Hold();
                if (Hp_bar.hp < 100) {
                    Hp_bar.hp += 0.2;
                }
                bull.setPosition(Gdx.graphics.getWidth() / 2 + 99, -500);
                stop = true;
            }
        }
        //}
    }
}
