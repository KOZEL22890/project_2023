package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Score {
    private static BitmapFont font = new BitmapFont(Gdx.files.internal("Font.fnt"));
    private static BitmapFont font_2 = new BitmapFont(Gdx.files.internal("hz.fnt"));
    private static BitmapFont finish_font = new BitmapFont(Gdx.files.internal("finish font.fnt"));
    static String state = "";
    static int pos_x = 0;
    static int pos_y = 700;
    static float alpha = 0;
    static int score = 0;
    static int score2 = 0;
    static int pplus_cnt = 0;
    static int p_cnt = 0;
    static int g_cnt = 0;
    static int d_cnt = 0;
    static int m_cnt = 0;
    static int pplus_cnt2 = 0;
    static int p_cnt2 = 0;
    static int g_cnt2 = 0;
    static int d_cnt2 = 0;
    static int m_cnt2 = 0;
    static int current_state = 0;
    static int high_score;
    static boolean exitable = false;
    static boolean hz = false;
    static int hs = 0;
    static Texture white_sq = new Texture("projectile.png");
    static Sprite white_square = new Sprite(white_sq);

    public static void Draw(SpriteBatch batch) {
        font.setColor(1, 0, 1, alpha);
        font.draw(batch, state, pos_x, pos_y);
        if (alpha > 0) {
            alpha -= 0.05f;
            if (state.equals("TRY AGAIN")) {
                alpha += 0.04f;
            }
        }
        font_2.draw(batch, score + "", 1820 - (score + "").length() * 25, 100);
    }

    public static void Finish(int S_score, int A_score, int B_score, int C_score, SpriteBatch batch) {
        if (current_state < 7) {
            if (current_state == 0) {
                try {
                    FileInputStream FInS = new FileInputStream("score.txt");
                    Scanner sc = new Scanner(FInS);
                    high_score = Integer.parseInt(sc.nextLine().trim());
                    FInS.close();
                    if (high_score < score) {
                        FileOutputStream FOuS = new FileOutputStream("score.txt");
                        FOuS.write(Integer.toString(score).getBytes(StandardCharsets.UTF_8));
                        FOuS.close();
                        hs = 1;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                current_state++;
            } else if (score2 <= score - 1000) {
                score2 += score / 75 + (Math.random() + 3 * 5);
            } else if (score2 > score - 1000 && current_state == 1) {
                score2 = score;
                current_state++;
            } else if (pplus_cnt2 <= pplus_cnt - 5) {
                pplus_cnt2 += pplus_cnt / 10 + (Math.random() + 1 * 2);
            } else if (pplus_cnt2 > pplus_cnt - 5 && current_state == 2) {
                pplus_cnt2 = pplus_cnt;
                current_state++;
            } else if (p_cnt2 <= p_cnt - 5) {
                p_cnt2 += p_cnt / 10 + (Math.random() + 1 * 2);
            } else if (p_cnt2 > p_cnt - 5 && current_state == 3) {
                p_cnt2 = p_cnt;
                current_state++;
            } else if (g_cnt2 <= g_cnt - 5) {
                g_cnt2 += g_cnt / 10 + (Math.random() + 1 * 2);
            } else if (g_cnt2 > g_cnt - 5 && current_state == 4) {
                g_cnt2 = g_cnt;
                current_state++;
            } else if (d_cnt2 <= d_cnt - 5) {
                d_cnt2 += d_cnt / 10 + (Math.random() + 1 * 2);
            } else if (d_cnt2 > d_cnt - 5 && current_state == 5) {
                d_cnt2 = d_cnt;
                current_state++;
            } else if (m_cnt2 <= m_cnt - 5) {
                m_cnt2 += m_cnt / 10 + (Math.random() + 1 * 2);
            } else if (m_cnt2 > m_cnt - 5 && current_state == 6) {
                m_cnt2 = m_cnt;
                current_state++;
                alpha = 1;
            }
        }

        finish_font.setColor(Color.YELLOW);
        finish_font.draw(batch, "Score:" + score2, 10, 980);
        finish_font.draw(batch, "High Score:" + high_score, 1920 - ("High Score:" + high_score).length() * 47, 980);
        finish_font.setColor(Color.PINK);
        finish_font.draw(batch, "Perfect+:" + pplus_cnt2, 10, 780);
        finish_font.setColor(Color.GREEN);
        finish_font.draw(batch, "Perfect:" + p_cnt2, 10, 580);
        finish_font.setColor(Color.BLUE);
        finish_font.draw(batch, "Good:" + g_cnt2, 1920 - ("Good:" + g_cnt).length() * 50, 780);
        finish_font.setColor(Color.CYAN);
        finish_font.draw(batch, "Decent:" + d_cnt2, 1920 - ("Decent:" + d_cnt).length() * 48, 580);
        finish_font.setColor(Color.RED);
        finish_font.draw(batch, "Misses:" + m_cnt2, 800, 100);
        if (hs == 2) {
            finish_font.setColor(Color.ROYAL);
            finish_font.draw(batch, "New High Score!", 650, 280);
        }
        if (current_state == 7) {
            white_square.setScale(1920, 1080);
            if (alpha <= 0) {
                alpha = 0;
                exitable = true;
            }
            white_square.setAlpha(alpha);
            white_square.draw(batch);
            if (alpha > 0) {
                alpha -= 0.02f;
            }
            BitmapFont grade = new BitmapFont(Gdx.files.internal("grade_font.fnt"));
            if (hs == 1) {
                hs = 2;
            }
            if (score < C_score) {
                grade.draw(batch, "C", 900, 600);
            } else if (score < B_score) {
                grade.draw(batch, "B", 900, 600);
            } else if (score < A_score) {
                grade.draw(batch, "A", 900, 600);
            } else if (score < S_score) {
                grade.draw(batch, "S", 900, 600);
            } else {
                grade.draw(batch, "P", 900, 600);
            }
            if (exitable && Gdx.input.isKeyPressed(Input.Keys.R)) {
                Hp_bar.hp = 0;
                hz = true;
                current_state = 0;
            } else if (exitable && Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
                Gdx.app.exit();
            }
        }
    }

    public static void restart() {
        score = 0;
        current_state = 1;
        score2 = 0;
        pplus_cnt = 0;
        p_cnt = 0;
        g_cnt = 0;
        d_cnt = 0;
        m_cnt = 0;
        pplus_cnt2 = 0;
        p_cnt2 = 0;
        g_cnt2 = 0;
        d_cnt2 = 0;
        m_cnt2 = 0;
        if (!hz) {
            state = "TRY AGAIN";
            pos_x = 800;
            alpha = 1;
        }
        hz = false;
        hs = 0;
        current_state = 0;
    }

    public static void Perfect_plus() {
        pos_x = 850;
        state = "PERFECT+";
        alpha = 1;
        score += 400;
        pplus_cnt++;
    }

    public static void Perfect() {
        pos_x = 865;
        state = "PERFECT";
        alpha = 1;
        score += 300;
        p_cnt++;
    }

    public static void Good() {
        pos_x = 900;
        state = "GOOD";
        alpha = 1;
        score += 200;
        g_cnt++;
    }

    public static void Decent() {
        pos_x = 880;
        state = "DECENT";
        alpha = 1;
        score += 100;
        d_cnt++;
    }

    public static void Miss() {
        pos_x = 900;
        state = "MISS";
        alpha = 1;
        m_cnt++;
    }

    public static void Hold() {
        score += 20;
    }
}
