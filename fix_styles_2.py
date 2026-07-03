import re
with open('app/src/main/res/values/styles.xml', 'r', encoding='utf-8') as f:
    content = f.read()

content = re.sub(r'<item name="android:windowSoftInputMode">0x20</item>', '<item name="android:windowSoftInputMode">adjustPan</item>', content)
content = re.sub(r'<item name="android:gravity">0x10</item>', '<item name="android:gravity">center_vertical</item>', content)
content = re.sub(r'<item name="android:gravity">0x11</item>', '<item name="android:gravity">center</item>', content)
content = re.sub(r'<item name="android:layout_gravity">0x11</item>', '<item name="android:layout_gravity">center</item>', content)
content = re.sub(r'^\s*<item[^>]*>@drawable/abc_.*?</item>\n?', '', content, flags=re.MULTILINE)

with open('app/src/main/res/values/styles.xml', 'w', encoding='utf-8') as f:
    f.write(content)
print("Fixed more styles.xml")
