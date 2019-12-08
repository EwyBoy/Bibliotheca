package com.ewyboy.bibliotheca.util;

import net.minecraftforge.fml.ModLoadingContext;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;


import java.util.TreeMap;

public class ModLogger {

    private static final TreeMap<String, Logger> LOGGERS = new TreeMap<>();
    private static final Level LEVEL = Level.DEBUG;

    private ModLogger() {}

    public static void setLevel(Level level) {
        getLogger().setLevel(level);
    }

    public static void info(String message, Object... args) {
        log(Level.INFO, message, args);
    }

    public static void debug(String message, Object... args) {
        log(Level.DEBUG, message, args);
    }

    public static void error(String message, Object... args) {
        log(Level.ERROR, message, args);
    }

    private static void log(Level level, String message, Object... args) {
        if (level == LEVEL || level.isMoreSpecificThan(LEVEL))
            getLogger().log(level, message, args);
    }

    private static Logger getLogger() {
        return LOGGERS.computeIfAbsent(ModLoadingContext.get().getActiveContainer().getModId(), id -> (Logger) LogManager.getLogger(id));
    }

}
