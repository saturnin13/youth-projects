package com.mygdx.game;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Input;
        import com.badlogic.gdx.graphics.Color;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.Batch;
        import com.badlogic.gdx.scenes.scene2d.Action;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.InputListener;
        import com.badlogic.gdx.scenes.scene2d.actions.*;
        import com.badlogic.gdx.scenes.scene2d.ui.Image;
        import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

        import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
        import static com.badlogic.gdx.scenes.scene2d.actions.Actions.addAction;


public class MyActor extends Image {

    /*@Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(this.getColor());

        ((TextureRegionDrawable)getDrawable()).draw(batch, getX(),getY(),
                getOriginX(),getOriginY(),
                getWidth(),getHeight(),
                getScaleX(),getScaleY(),
                getRotation());
    }*/

    public MyActor(Texture texture){
        super(texture);

        setBounds(getX(),getY(),getWidth(),getHeight());

        addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch(keycode) {
                    case Input.Keys.NUM_1:
                        MoveToAction moveToAction = new MoveToAction();
                        moveToAction.setPosition(200f,200f);
                        moveToAction.setDuration(5f);
                        MyActor.this.addAction(moveToAction);
                        break;

                    case Input.Keys.NUM_2:
                        MoveByAction moveByAction = new MoveByAction();
                        moveByAction.setAmount(-200f,0f);
                        moveByAction.setDuration(3f);
                        MyActor.this.addAction(moveByAction);
                        break;

                    case Input.Keys.NUM_3:
                        ColorAction colorAction = new ColorAction();
                        colorAction.setEndColor(Color.PURPLE);
                        colorAction.setDuration(2f);
                        MyActor.this.addAction(colorAction);
                        break;

                    case Input.Keys.NUM_4:
                        MoveToAction mta = new MoveToAction();
                        mta.setPosition(Gdx.graphics.getWidth() - 200f,Gdx.graphics.getHeight()-200f);
                        mta.setDuration(3f);

                        ScaleByAction sba = new ScaleByAction();
                        sba.setAmount(2f);
                        sba.setDuration(3f);

                        RotateToAction rta = new RotateToAction();
                        rta.setRotation(90f);
                        rta.setDuration(3f);

                        ParallelAction pa = new ParallelAction(mta,sba,rta);
                        MyActor.this.addAction(pa);
                        break;

                    case Input.Keys.NUM_5:
                        MoveToAction mta2 = new MoveToAction();
                        mta2.setPosition(Gdx.graphics.getWidth() - 200f,Gdx.graphics.getHeight()-200f);
                        mta2.setDuration(3f);

                        ScaleByAction sba2 = new ScaleByAction();
                        sba2.setAmount(2f);
                        sba2.setDuration(3f);

                        RotateToAction rta2 = new RotateToAction();
                        rta2.setRotation(90f);
                        rta2.setDuration(3f);

                        SequenceAction sa = new SequenceAction(mta2,sba2,rta2);
                        MyActor.this.addAction(sa);
                        break;

                    case Input.Keys.NUM_6:
                        RunnableAction ra = new RunnableAction();
                        ra.setRunnable(new Runnable(){
                            @Override
                            public void run() {
                                MyActor.this.setPosition(0f,0f);
                                MyActor.this.setRotation(0f);
                                MyActor.this.setScale(1f);
                            }
                        });
                        MyActor.this.addAction(ra);
                        break;

                    case Input.Keys.SPACE:

                        addAction(parallel(
                                moveTo(200f, 200f, 3f),
                                scaleTo(2f, 3f),
                                rotateTo(90f, 3f)
                        ));
                        break;

                    case Input.Keys.NUM_7:
                        MoveToAction moveToAction2 = new MoveToAction();
                        moveToAction2.setPosition(200f,200f);
                        moveToAction2.setDuration(5f);

                        MoveToAction moveToAction3 = new MoveToAction();
                        moveToAction3.setPosition(0f,0f);
                        moveToAction3.setDuration(3f);

                        ParallelAction pa2 = new ParallelAction(moveToAction2,moveToAction3);

                        MyActor.this.addAction(pa2);
                        break;
                }
                return true;
            }
        });
    }
}