package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Chrome;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.ScrollPane;
import com.shatteredpixel.shatteredpixeldungeon.ui.StyledButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Image;
import com.watabou.noosa.PointerArea;
import com.watabou.noosa.Visual;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;

public class WndActionList extends Window {

    private static final int WIDTH_P = 105;
    private static final int WIDTH_L = 220;

    private static final int MARGIN  = 2;

    private ScrollPane modeList;
    private ArrayList<ActionButton> slots = new ArrayList<>();

    public WndActionList(){
        super();

        int width = PixelScene.landscape() ? WIDTH_L : WIDTH_P;

        float pos = MARGIN;
        RenderedTextBlock title = PixelScene.renderTextBlock(Messages.titleCase(Messages.get(this, "title")), 9);
        title.hardlight(TITLE_COLOR);
        title.setPos((width-title.width())/2, pos);
        title.maxWidth(width - MARGIN * 2);
        add(title);
        pos += MARGIN + title.height();
        RenderedTextBlock desc = PixelScene.renderTextBlock(Messages.capitalize(Messages.get(this, "desc")), 6);
        desc.setPos((width-desc.width())/2, pos);
        desc.maxWidth(width - MARGIN * 2);
        add(desc);

        modeList = new ScrollPane( new Component());
        add(modeList);

        Component content = modeList.content();
        int positem = 0;

        ArrayList<ActionIndicator.Action> possibleActions = new ArrayList<>();
        for (Class<? extends Buff> possibleAction : ActionIndicator.actionBuffClasses){
            for(Buff b : Dungeon.hero.buffs(possibleAction)){
                if (((ActionIndicator.Action)b).usable())
                    possibleActions.add((ActionIndicator.Action) b);
            }
        }

        for (ActionIndicator.Action possibleAction : possibleActions) {
            ActionButton actionBtn = new ActionButton(Messages.titleCase(possibleAction.actionName()), 6, possibleAction);
            actionBtn.icon(new Image(Assets.Sprites.ITEMS, 240, 16, 16, 16));
            actionBtn.leftJustify = true;
            actionBtn.multiline = true;
            actionBtn.hardlight(possibleAction.indicatorColor());
            actionBtn.setSize(width, actionBtn.reqHeight());
            actionBtn.setRect(0, positem, width, actionBtn.reqHeight());
            actionBtn.enable(true);
            content.add(actionBtn);
            slots.add(actionBtn);
            positem += actionBtn.height() + MARGIN;
        }
        content.setSize(width, positem+1);
        resize(width, PixelScene.uiCamera.height-80);
        modeList.setRect(0, desc.bottom()+MARGIN, width, height - MARGIN*4.5f);
    }

    public class ActionButton extends StyledButton {

        ActionIndicator.Action action;
        Visual primaryVis;
        Visual secondVis;

        public ActionButton(String label, int size, ActionIndicator.Action action){
            super(Chrome.Type.GREY_BUTTON, label, size);
            hotArea.blockLevel = PointerArea.NEVER_BLOCK;

            this.action = action;
            primaryVis = action.primaryVisual();
            secondVis = action.secondaryVisual();
            add(primaryVis);
            if (secondVis != null)
                add(secondVis);
        }

        @Override
        protected void onClick() {
            super.onClick();
            action.doAction();
            WndActionList.this.hide();
        }

        @Override
        protected boolean onLongClick() {
            ActionIndicator.setAction(action);
            WndActionList.this.hide();
            return true;
        }

        public void hardlight(int color){
            bg.hardlight(color);
        }

        @Override
        public void update() {
            super.update();

            if (primaryVis != null){
                primaryVis.x = x + (20 - primaryVis.width()) / 2f + 1;
                primaryVis.y = y + (20 - primaryVis.height()) / 2f;
                PixelScene.align(primaryVis);
                if (secondVis != null){
                    if (secondVis.width() > 16) secondVis.x = primaryVis.center().x - secondVis.width()/2f;
                    else                        secondVis.x = primaryVis.center().x + 8 - secondVis.width();
                    if (secondVis instanceof BitmapText){
                        //need a special case here for text unfortunately
                        secondVis.y = primaryVis.center().y + 8 - ((BitmapText) secondVis).baseLine();
                    } else {
                        secondVis.y = primaryVis.center().y + 8 - secondVis.height();
                    }
                    PixelScene.align(secondVis);
                }
            }
        }
    }
}
