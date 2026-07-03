import re
with open('app/src/main/res/values/styles.xml', 'r', encoding='utf-8') as f:
    content = f.read()

content = re.sub(r'>-1<', '>match_parent<', content)
content = re.sub(r'>-2<', '>wrap_content<', content)
content = re.sub(r'<item name="android:textStyle">0x0</item>', '<item name="android:textStyle">normal</item>', content)
content = re.sub(r'<item name="android:textStyle">0x1</item>', '<item name="android:textStyle">bold</item>', content)
content = re.sub(r'<item name="android:gravity">0x1</item>', '<item name="android:gravity">center_horizontal</item>', content)
content = re.sub(r'<item name="android:ellipsize">3</item>', '<item name="android:ellipsize">end</item>', content)
content = re.sub(r'<item name="android:scaleType">3</item>', '<item name="android:scaleType">fitCenter</item>', content)
content = re.sub(r'^\s*<item[^>]*>@drawable/abc_ic_.*?</item>\n?', '', content, flags=re.MULTILINE)

with open('app/src/main/res/values/styles.xml', 'w', encoding='utf-8') as f:
    f.write(content)
print("Fixed styles.xml")
