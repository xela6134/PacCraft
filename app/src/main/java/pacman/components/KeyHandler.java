package pacman.components;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private boolean upPressed, downPressed, leftPressed, rightPressed = false;

    @Override
    public void keyPressed(KeyEvent arg) {
        int code = arg.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = true;
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

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    @Override
    public void keyTyped(KeyEvent arg) { }
}
