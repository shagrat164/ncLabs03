/*
 * @(#)LogSrv.java 1.0 11.01.2017
 */

package ru.solpro.game.server.core.datasrv;

import ru.solpro.game.server.core.ServerLoader;
import ru.solpro.game.server.util.DateUtil;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class LogServer {
    private LogServer() {}

    public static void info(String message) {
        ServerLoader.getRootLayoutController().getTextLog().appendText(DateUtil.getCurrentDateTime() + " " + message + "\n");
    }
}
