package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;

public class ButtonGroup {
    private final Array buttons;
    private Array checkedButtons;
    private Button lastChecked;
    private int maxCheckCount;
    private int minCheckCount;
    private boolean uncheckLast;

    public ButtonGroup() {
        this.buttons = new Array();
        this.checkedButtons = new Array(1);
        this.maxCheckCount = 1;
        this.uncheckLast = true;
        this.minCheckCount = 1;
    }

    public ButtonGroup(Button[] arr_button) {
        this.buttons = new Array();
        this.checkedButtons = new Array(1);
        this.maxCheckCount = 1;
        this.uncheckLast = true;
        this.minCheckCount = 0;
        this.add(arr_button);
        this.minCheckCount = 1;
    }

    public void add(Button button0) {
        if(button0 == null) {
            throw new IllegalArgumentException("button cannot be null.");
        }
        button0.buttonGroup = null;
        boolean z = button0.isChecked() || this.buttons.size < this.minCheckCount;
        button0.setChecked(false);
        button0.buttonGroup = this;
        this.buttons.add(button0);
        button0.setChecked(z);
    }

    public void add(Button[] arr_button) {
        if(arr_button == null) {
            throw new IllegalArgumentException("buttons cannot be null.");
        }
        for(int v = 0; v < arr_button.length; ++v) {
            this.add(arr_button[v]);
        }
    }

    protected boolean canCheck(Button button0, boolean z) {
        if(button0.isChecked == z) {
            return false;
        }
        if(!z) {
            if(this.checkedButtons.size <= this.minCheckCount) {
                return false;
            }
            this.checkedButtons.removeValue(button0, true);
            return true;
        }
        if(this.maxCheckCount != -1 && this.checkedButtons.size >= this.maxCheckCount) {
            if(!this.uncheckLast) {
                return false;
            }
            for(int v = 0; true; ++v) {
                int v1 = this.minCheckCount;
                this.minCheckCount = 0;
                this.lastChecked.setChecked(false);
                this.minCheckCount = v1;
                if(button0.isChecked) {
                    return false;
                }
                if(this.checkedButtons.size < this.maxCheckCount) {
                    break;
                }
                if(v > 10) {
                    return false;
                }
            }
        }
        this.checkedButtons.add(button0);
        this.lastChecked = button0;
        return true;
    }

    public void clear() {
        this.buttons.clear();
        this.checkedButtons.clear();
    }

    public Array getAllChecked() {
        return this.checkedButtons;
    }

    public Array getButtons() {
        return this.buttons;
    }

    @Null
    public Button getChecked() {
        return this.checkedButtons.size <= 0 ? null : ((Button)this.checkedButtons.get(0));
    }

    public int getCheckedIndex() {
        if(this.checkedButtons.size > 0) {
            Object object0 = this.checkedButtons.get(0);
            return this.buttons.indexOf(object0, true);
        }
        return -1;
    }

    public void remove(Button button0) {
        if(button0 == null) {
            throw new IllegalArgumentException("button cannot be null.");
        }
        button0.buttonGroup = null;
        this.buttons.removeValue(button0, true);
        this.checkedButtons.removeValue(button0, true);
    }

    public void remove(Button[] arr_button) {
        if(arr_button == null) {
            throw new IllegalArgumentException("buttons cannot be null.");
        }
        for(int v = 0; v < arr_button.length; ++v) {
            this.remove(arr_button[v]);
        }
    }

    public void setChecked(String s) {
        if(s == null) {
            throw new IllegalArgumentException("text cannot be null.");
        }
        int v1 = this.buttons.size;
        for(int v = 0; v < v1; ++v) {
            Button button0 = (Button)this.buttons.get(v);
            if(button0 instanceof TextButton && s.contentEquals(((TextButton)button0).getText())) {
                button0.setChecked(true);
                return;
            }
        }
    }

    public void setMaxCheckCount(int v) {
        if(v == 0) {
            v = -1;
        }
        this.maxCheckCount = v;
    }

    public void setMinCheckCount(int v) {
        this.minCheckCount = v;
    }

    public void setUncheckLast(boolean z) {
        this.uncheckLast = z;
    }

    public void uncheckAll() {
        int v = this.minCheckCount;
        this.minCheckCount = 0;
        int v1 = this.buttons.size;
        for(int v2 = 0; v2 < v1; ++v2) {
            ((Button)this.buttons.get(v2)).setChecked(false);
        }
        this.minCheckCount = v;
    }
}

