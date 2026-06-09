package org.apache.commons.net.smtp;

import java.util.Enumeration;
import java.util.Vector;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes2.dex */
public final class RelayPath {
    String _emailAddress;
    Vector<String> _path = new Vector<>();

    public RelayPath(String str) {
        this._emailAddress = str;
    }

    public void addRelay(String str) {
        this._path.addElement(str);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.less);
        Enumeration<String> enumerationElements = this._path.elements();
        if (enumerationElements.hasMoreElements()) {
            sb.append('@');
            sb.append(enumerationElements.nextElement());
            while (enumerationElements.hasMoreElements()) {
                sb.append(",@");
                sb.append(enumerationElements.nextElement());
            }
            sb.append(':');
        }
        sb.append(this._emailAddress);
        sb.append(Typography.greater);
        return sb.toString();
    }
}
