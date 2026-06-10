package generalplus.com.GPCamLib;

import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: classes2.dex */
public class GPXMLParse {
    public static int CaptureResolution_Setting_ID = 256;
    public static int CategoryLevel = 12;
    public static int RecordResolution_Setting_ID = 0;
    public static int SettingLevel = 6;
    public static int ValueLevel = 0;
    public static int Version_Setting_ID = 521;
    public static int Version_Value_Index;
    private static ArrayList<GPXMLSetting> m_aryListGPXMLSettings;
    private static ArrayList<GPXMLValue> m_aryListGPXMLValues;
    private GPXMLCategory m_GPXMLCategory;
    private GPXMLSetting m_GPXMLSetting;
    private GPXMLValue m_GPXMLValue;
    private static ArrayList<GPXMLCategory> m_aryListGPXMLCategroies = new ArrayList<>();
    private static String GPTag = "GPXMLParseLog";

    public GPXMLParse() {
        if (m_aryListGPXMLValues == null) {
            m_aryListGPXMLValues = new ArrayList<>();
        }
        if (m_aryListGPXMLSettings == null) {
            m_aryListGPXMLSettings = new ArrayList<>();
        }
        if (m_aryListGPXMLCategroies == null) {
            m_aryListGPXMLCategroies = new ArrayList<>();
        }
    }

    public ArrayList<GPXMLCategory> GetCategories() {
        m_aryListGPXMLValues.clear();
        m_aryListGPXMLSettings.clear();
        m_aryListGPXMLCategroies.clear();
        return m_aryListGPXMLCategroies;
    }

    public class GPXMLValue {
        public int i32TreeLevel;
        public String strXMLValueID;
        public String strXMLValueName;

        public GPXMLValue(String str, String str2, int i) {
            this.strXMLValueName = str;
            this.strXMLValueID = str2;
            this.i32TreeLevel = i;
        }
    }

    public class GPXMLSetting {
        public ArrayList<GPXMLValue> aryListGPXMLValues;
        public int i32TreeLevel;
        public String strXMLSettingCurrent;
        public String strXMLSettingDefaultValue;
        public String strXMLSettingID;
        public String strXMLSettingName;
        public String strXMLSettingReflash;
        public String strXMLSettingType;

        public GPXMLSetting(String str, String str2, String str3, String str4, String str5, int i, ArrayList<GPXMLValue> arrayList) {
            this.strXMLSettingCurrent = null;
            this.strXMLSettingName = str;
            this.strXMLSettingID = str2;
            this.strXMLSettingType = str3;
            this.strXMLSettingReflash = str4;
            this.strXMLSettingDefaultValue = str5;
            if (arrayList != null) {
                int i2 = 0;
                while (true) {
                    if (i2 >= arrayList.size()) {
                        break;
                    }
                    if (arrayList.get(i2).strXMLValueID.equalsIgnoreCase(str5)) {
                        this.strXMLSettingCurrent = arrayList.get(i2).strXMLValueName;
                        break;
                    }
                    i2++;
                }
            }
            this.i32TreeLevel = i;
            this.aryListGPXMLValues = (ArrayList) arrayList.clone();
        }
    }

    public class GPXMLCategory {
        public ArrayList<GPXMLSetting> aryListGPXMLSettings;
        public String strXMLCategoryName;
        public int i32TreeLevel = 0;


        public GPXMLCategory(String str, int i, ArrayList<GPXMLSetting> arrayList) {
            this.strXMLCategoryName = str;
            this.aryListGPXMLSettings = (ArrayList) arrayList.clone();
        }
    }

    public ArrayList<GPXMLCategory> GetGPXMLInfo(String str) {
        String str2;
        int i;
        NodeList nodeList;
        String str3;
        int i2;
        NodeList nodeList2;
        int i3;
        NodeList nodeList3;
        String str4;
        int i4;
        NodeList nodeList4;
        String nodeValue;
        int i5;
        String nodeValue2;
        String str5;
        String str6 = "ID";
        try {
            NodeList elementsByTagName = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(str)).getElementsByTagName("Categories");
            m_aryListGPXMLCategroies.clear();
            int i6 = 0;
            int i7 = 0;
            while (i7 < elementsByTagName.getLength()) {
                Node nodeItem = elementsByTagName.item(i7);
                short s = 1;
                if (nodeItem.getNodeType() == 1) {
                    NodeList elementsByTagName2 = ((Element) nodeItem).getElementsByTagName("Category");
                    int i8 = 0;
                    while (i8 < elementsByTagName2.getLength()) {
                        Node nodeItem2 = elementsByTagName2.item(i8);
                        if (nodeItem2.getNodeType() == s) {
                            Element element = (Element) nodeItem2;
                            m_aryListGPXMLSettings.clear();
                            NodeList elementsByTagName3 = element.getElementsByTagName("Name");
                            String nodeValue3 = elementsByTagName3.getLength() > 0 ? ((Element) elementsByTagName3.item(i6)).getChildNodes().item(i6).getNodeValue() : "";
                            NodeList elementsByTagName4 = element.getElementsByTagName("Settings");
                            int i9 = 0;
                            while (i9 < elementsByTagName4.getLength()) {
                                Node nodeItem3 = elementsByTagName4.item(i9);
                                if (nodeItem3.getNodeType() == s) {
                                    NodeList elementsByTagName5 = ((Element) nodeItem3).getElementsByTagName("Setting");
                                    int i10 = 0;
                                    while (i10 < elementsByTagName5.getLength()) {
                                        Node nodeItem4 = elementsByTagName5.item(i10);
                                        if (nodeItem4.getNodeType() == s) {
                                            Element element2 = (Element) nodeItem4;
                                            m_aryListGPXMLValues.clear();
                                            NodeList elementsByTagName6 = element2.getElementsByTagName("Name");
                                            String nodeValue4 = elementsByTagName6.getLength() > 0 ? ((Element) elementsByTagName6.item(i6)).getChildNodes().item(i6).getNodeValue() : "";
                                            NodeList elementsByTagName7 = element2.getElementsByTagName(str6);
                                            String nodeValue5 = elementsByTagName7.getLength() > 0 ? ((Element) elementsByTagName7.item(i6)).getChildNodes().item(i6).getNodeValue() : "";
                                            NodeList elementsByTagName8 = element2.getElementsByTagName("Type");
                                            if (elementsByTagName8.getLength() > 0) {
                                                nodeList2 = elementsByTagName5;
                                                nodeValue = ((Element) elementsByTagName8.item(0)).getChildNodes().item(0).getNodeValue();
                                            } else {
                                                nodeList2 = elementsByTagName5;
                                                nodeValue = "";
                                            }
                                            NodeList elementsByTagName9 = element2.getElementsByTagName("Reflash");
                                            if (elementsByTagName9.getLength() > 0) {
                                                i5 = i9;
                                                nodeValue2 = ((Element) elementsByTagName9.item(0)).getChildNodes().item(0).getNodeValue();
                                            } else {
                                                i5 = i9;
                                                nodeValue2 = "";
                                            }
                                            NodeList elementsByTagName10 = element2.getElementsByTagName("Default");
                                            String nodeValue6 = elementsByTagName10.getLength() > 0 ? ((Element) elementsByTagName10.item(0)).getChildNodes().item(0).getNodeValue() : "";
                                            NodeList elementsByTagName11 = element2.getElementsByTagName("Values");
                                            int i11 = 0;
                                            while (i11 < elementsByTagName11.getLength()) {
                                                Node nodeItem5 = elementsByTagName11.item(i11);
                                                NodeList nodeList5 = elementsByTagName11;
                                                NodeList nodeList6 = elementsByTagName4;
                                                if (nodeItem5.getNodeType() == 1) {
                                                    NodeList elementsByTagName12 = ((Element) nodeItem5).getElementsByTagName("Value");
                                                    int i12 = 0;
                                                    while (i12 < elementsByTagName12.getLength()) {
                                                        Node nodeItem6 = elementsByTagName12.item(i12);
                                                        NodeList nodeList7 = elementsByTagName12;
                                                        String str7 = nodeValue3;
                                                        if (nodeItem6.getNodeType() == 1) {
                                                            Element element3 = (Element) nodeItem6;
                                                            NodeList elementsByTagName13 = element3.getElementsByTagName("Name");
                                                            String nodeValue7 = elementsByTagName13.getLength() > 0 ? ((Element) elementsByTagName13.item(0)).getChildNodes().item(0).getNodeValue() : "";
                                                            NodeList elementsByTagName14 = element3.getElementsByTagName(str6);
                                                            str5 = str6;
                                                            GPXMLValue gPXMLValue = new GPXMLValue(nodeValue7, elementsByTagName14.getLength() > 0 ? ((Element) elementsByTagName14.item(0)).getChildNodes().item(0).getNodeValue() : "", ((1 << CategoryLevel) * i8) + ((1 << SettingLevel) * i10) + ((1 << ValueLevel) * i12));
                                                            this.m_GPXMLValue = gPXMLValue;
                                                            m_aryListGPXMLValues.add(gPXMLValue);
                                                            this.m_GPXMLValue = null;
                                                        } else {
                                                            str5 = str6;
                                                        }
                                                        i12++;
                                                        elementsByTagName12 = nodeList7;
                                                        nodeValue3 = str7;
                                                        str6 = str5;
                                                    }
                                                }
                                                i11++;
                                                elementsByTagName11 = nodeList5;
                                                elementsByTagName4 = nodeList6;
                                                nodeValue3 = nodeValue3;
                                                str6 = str6;
                                            }
                                            str3 = str6;
                                            i2 = i10;
                                            String str8 = nodeValue4;
                                            String str9 = nodeValue5;
                                            i3 = i5;
                                            String str10 = nodeValue;
                                            nodeList3 = elementsByTagName4;
                                            str4 = nodeValue3;
                                            i4 = i8;
                                            nodeList4 = elementsByTagName2;
                                            GPXMLSetting gPXMLSetting = new GPXMLSetting(str8, str9, str10, nodeValue2, nodeValue6, (i8 << CategoryLevel) | (i10 << SettingLevel), m_aryListGPXMLValues);
                                            this.m_GPXMLSetting = gPXMLSetting;
                                            m_aryListGPXMLSettings.add(gPXMLSetting);
                                            this.m_GPXMLSetting = null;
                                        } else {
                                            str3 = str6;
                                            i2 = i10;
                                            nodeList2 = elementsByTagName5;
                                            i3 = i9;
                                            nodeList3 = elementsByTagName4;
                                            str4 = nodeValue3;
                                            i4 = i8;
                                            nodeList4 = elementsByTagName2;
                                        }
                                        i10 = i2 + 1;
                                        elementsByTagName4 = nodeList3;
                                        i9 = i3;
                                        elementsByTagName5 = nodeList2;
                                        i8 = i4;
                                        elementsByTagName2 = nodeList4;
                                        str6 = str3;
                                        nodeValue3 = str4;
                                        i6 = 0;
                                        s = 1;
                                    }
                                }
                                i9++;
                                elementsByTagName4 = elementsByTagName4;
                                i8 = i8;
                                elementsByTagName2 = elementsByTagName2;
                                str6 = str6;
                                nodeValue3 = nodeValue3;
                                i6 = 0;
                                s = 1;
                            }
                            str2 = str6;
                            i = i8;
                            nodeList = elementsByTagName2;
                            GPXMLCategory gPXMLCategory = new GPXMLCategory(nodeValue3, i << CategoryLevel, m_aryListGPXMLSettings);
                            this.m_GPXMLCategory = gPXMLCategory;
                            m_aryListGPXMLCategroies.add(gPXMLCategory);
                            this.m_GPXMLCategory = null;
                        } else {
                            str2 = str6;
                            i = i8;
                            nodeList = elementsByTagName2;
                        }
                        i8 = i + 1;
                        elementsByTagName2 = nodeList;
                        str6 = str2;
                        i6 = 0;
                        s = 1;
                    }
                }
                i7++;
                str6 = str6;
                i6 = 0;
            }
        } catch (Exception e) {
            Log.e(GPTag, e.getMessage());
        }
        return m_aryListGPXMLCategroies;
    }
}
