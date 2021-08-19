/*
 * Assignment -
 * Sun Wu Choi
 * COSC 237-004
 * Spring, 2019
 */
package gui;

/**
 *
 * @author Sun Wu Choi
 */
public interface UserInterface {
    void start();
    void display();
    void exit();
    void printer(String[] input);
    void controller(int selection);
}
