package pacman.components;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private boolean upPressed, downPressed, leftPressed, rightPressed = false;
    private LastPressed pressed;
    
    public enum LastPressed {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    @Override
    public void keyPressed(KeyEvent arg) {
        int code = arg.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
            pressed = LastPressed.UP;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = true;
            pressed = LastPressed.LEFT;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = true;
            pressed = LastPressed.DOWN;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = true;
            pressed = LastPressed.RIGHT;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg) {
        int code = arg.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

    public boolean getUpPressed() {
        return upPressed;
    }

    public boolean getDownPressed() {
        return downPressed;
    }

    public boolean getLeftPressed() {
        return leftPressed;
    }

    public boolean getRightPressed() {
        return rightPressed;
    }

    public boolean getSomethingPressed() {
        return upPressed || downPressed || leftPressed || rightPressed;
    }

    public LastPressed getLastPressed() {
        return pressed;
    }

    @Override
    public void keyTyped(KeyEvent arg) { }
}
