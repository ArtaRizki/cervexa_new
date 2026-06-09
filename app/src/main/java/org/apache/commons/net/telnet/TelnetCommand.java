package org.apache.commons.net.telnet;

/* JADX INFO: loaded from: classes2.dex */
public final class TelnetCommand {
    public static final int ABORT = 238;

    /* JADX INFO: renamed from: AO */
    public static final int f3464AO = 245;
    public static final int AYT = 246;
    public static final int BREAK = 243;

    /* JADX INFO: renamed from: DM */
    public static final int f3465DM = 242;

    /* JADX INFO: renamed from: DO */
    public static final int f3466DO = 253;
    public static final int DONT = 254;

    /* JADX INFO: renamed from: EC */
    public static final int f3467EC = 247;

    /* JADX INFO: renamed from: EL */
    public static final int f3468EL = 248;
    public static final int EOF = 236;
    public static final int EOR = 239;

    /* JADX INFO: renamed from: GA */
    public static final int f3469GA = 249;
    public static final int IAC = 255;

    /* JADX INFO: renamed from: IP */
    public static final int f3470IP = 244;
    public static final int MAX_COMMAND_VALUE = 255;
    public static final int NOP = 241;

    /* JADX INFO: renamed from: SB */
    public static final int f3471SB = 250;

    /* JADX INFO: renamed from: SE */
    public static final int f3472SE = 240;
    public static final int SUSP = 237;
    public static final int SYNCH = 242;
    public static final int WILL = 251;
    public static final int WONT = 252;
    private static final int __FIRST_COMMAND = 255;
    private static final int __LAST_COMMAND = 236;
    private static final String[] __commandString = {"IAC", "DONT", "DO", "WONT", "WILL", "SB", "GA", "EL", "EC", "AYT", "AO", "IP", "BRK", "DMARK", "NOP", "SE", "EOR", "ABORT", "SUSP", "EOF"};

    public static final boolean isValidCommand(int i) {
        return i <= 255 && i >= 236;
    }

    public static final String getCommand(int i) {
        return __commandString[255 - i];
    }

    private TelnetCommand() {
    }
}
