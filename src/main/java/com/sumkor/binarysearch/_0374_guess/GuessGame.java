package com.sumkor.binarysearch._0374_guess;

/**
 * @author Sumkor
 * @since 2021/9/23
 */
public class GuessGame {

    private int pick;

    public void setPick(int pick) {
        this.pick = pick;
    }

    /**
     * Forward declaration of guess API.
     * @param  num   your guess
     * @return 	     -1 if num is lower than the guess number
     *			      1 if num is higher than the guess number
     *               otherwise return 0
     * int guess(int num);
     */
    public int guess(int num) {
        if (pick < num) {
            return -1;
        }
        if (pick > num) {
            return 1;
        }
        return 0;
    }
}
