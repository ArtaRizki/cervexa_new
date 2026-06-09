package org.apache.commons.net.p033io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: classes2.dex */
public final class CRLFLineReader extends BufferedReader {

    /* JADX INFO: renamed from: CR */
    private static final char f3457CR = '\r';

    /* JADX INFO: renamed from: LF */
    private static final char f3458LF = '\n';

    public CRLFLineReader(Reader reader) {
        super(reader);
    }

    @Override // java.io.BufferedReader
    public String readLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        synchronized (this.lock) {
            boolean z = false;
            while (true) {
                int i = read();
                if (i == -1) {
                    String string = sb.toString();
                    if (string.length() == 0) {
                        return null;
                    }
                    return string;
                }
                if (z && i == 10) {
                    return sb.substring(0, sb.length() - 1);
                }
                z = i == 13;
                sb.append((char) i);
            }
        }
    }
}
