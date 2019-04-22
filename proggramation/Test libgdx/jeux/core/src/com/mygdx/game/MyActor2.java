package com.mygdx.game;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Input;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.Batch;
        import com.badlogic.gdx.graphics.g2d.Sprite;
        import com.badlogic.gdx.scenes.scene2d.Actor;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.InputListener;
        import com.badlogic.gdx.scenes.scene2d.Touchable;
        import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class MyActor2 extends Actor {
    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
    int test = 0;

    public MyActor2(){
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        setTouchable(Touchable.enabled);

        addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Input.Keys.RIGHT){
                    MoveByAction mba = new MoveByAction();
                    mba.setAmount(100f,0f);
                    mba.setDuration(5f);

                    MyActor2.this.addAction(mba);
                }
                return true;
            }
        });
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (test == 0) {
            sprite.setTexture(new Texture(Gdx.files.internal("walkcycle.png")));
            sprite.setPosition(0f, 0f);
        }
        else if (test == 1) {
            sprite.setTexture(new Texture(Gdx.files.internal("badlogic.jpg")));
            sprite.setPosition(200f, 200f);
        }
        if (test == 1) {
            test = 0;
        }
        else if (test == 0) {
            test = 1;
        }
    }
}
