package org.apache.commons.net.ftp.parser;

import org.apache.commons.net.ftp.FTPClientConfig;

/* JADX INFO: loaded from: classes2.dex */
public class OS400FTPEntryParser extends ConfigurableFTPFileEntryParserImpl {
    private static final String DEFAULT_DATE_FORMAT = "yy/MM/dd HH:mm:ss";
    private static final String REGEX = "(\\S+)\\s+(?:(\\d+)\\s+)?(?:(\\S+)\\s+(\\S+)\\s+)?(\\*STMF|\\*DIR|\\*FILE|\\*MEM)\\s+(?:(\\S+)\\s*)?";

    public OS400FTPEntryParser() {
        this(null);
    }

    public OS400FTPEntryParser(FTPClientConfig fTPClientConfig) {
        super(REGEX);
        configure(fTPClientConfig);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:4|(1:10)(1:9)|11|(2:68|12)|15|(2:17|(1:24)(1:22))(10:25|(2:27|(1:33)(1:32))(3:34|(1:(1:40))(2:41|(2:43|(1:51)(1:52))(1:53))|39)|54|70|55|58|(1:60)|(1:64)|65|66)|23|54|70|55|58|(0)|(2:62|64)|65|66) */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ec  */
    @Override // org.apache.commons.net.ftp.FTPFileEntryParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.apache.commons.net.ftp.FTPFile parseFTPEntry(java.lang.String r12) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.ftp.parser.OS400FTPEntryParser.parseFTPEntry(java.lang.String):org.apache.commons.net.ftp.FTPFile");
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    @Override // org.apache.commons.net.ftp.parser.ConfigurableFTPFileEntryParserImpl
    protected FTPClientConfig getDefaultConfiguration() {
        return new FTPClientConfig(FTPClientConfig.SYST_OS400, DEFAULT_DATE_FORMAT, null, null, null, null);
    }
}
