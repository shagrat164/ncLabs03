/*
 * @(#)StatusPlayer.java 1.0 11.01.2017
 */

package ru.solpro.game.client.network.model;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public enum StatusPlayer {
    /**
     * Свободен.
     */
    FREE,

    /**
     * В бою.
     */
    FIGHT,

    /**
     * Ожидание боя.
     */
    WAITING
}
