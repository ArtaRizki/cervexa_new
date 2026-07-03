import re
import os
import glob

res_dir = 'app/src/main/res/'
styles_files = glob.glob(res_dir + '**/styles.xml', recursive=True)

def fix_content(content):
    # Dimensions
    content = re.sub(r'>-1<', '>match_parent<', content)
    content = re.sub(r'>-2<', '>wrap_content<', content)
    
    # Simple regex replacing inside tags
    # \s* helps with potential spacing issues
    content = re.sub(r'<item\s+name="android:textStyle"\s*>0x0</item>', '<item name="android:textStyle">normal</item>', content)
    content = re.sub(r'<item\s+name="android:textStyle"\s*>0x1</item>', '<item name="android:textStyle">bold</item>', content)
    content = re.sub(r'<item\s+name="android:textStyle"\s*>0x2</item>', '<item name="android:textStyle">italic</item>', content)
    
    content = re.sub(r'<item\s+name="android:gravity"\s*>0x10</item>', '<item name="android:gravity">center_vertical</item>', content)
    content = re.sub(r'<item\s+name="android:gravity"\s*>0x11</item>', '<item name="android:gravity">center</item>', content)
    content = re.sub(r'<item\s+name="android:layout_gravity"\s*>0x11</item>', '<item name="android:layout_gravity">center</item>', content)
    content = re.sub(r'<item\s+name="android:layout_gravity"\s*>0x10</item>', '<item name="android:layout_gravity">center_vertical</item>', content)
    content = re.sub(r'<item\s+name="android:gravity"\s*>0x20</item>', '<item name="android:gravity">fill_vertical</item>', content)
    content = re.sub(r'<item\s+name="android:layout_gravity"\s*>0x20</item>', '<item name="android:layout_gravity">fill_vertical</item>', content)
    content = re.sub(r'<item\s+name="android:gravity"\s*>0x800013</item>', '<item name="android:gravity">start|center_vertical</item>', content)
    content = re.sub(r'<item\s+name="android:layout_gravity"\s*>0x800013</item>', '<item name="android:layout_gravity">start|center_vertical</item>', content)

    content = re.sub(r'<item\s+name="android:windowSoftInputMode"\s*>0x20</item>', '<item name="android:windowSoftInputMode">adjustPan</item>', content)
    content = re.sub(r'<item\s+name="android:windowSoftInputMode"\s*>0x10</item>', '<item name="android:windowSoftInputMode">adjustResize</item>', content)
    
    content = re.sub(r'<item\s+name="android:ellipsize"\s*>3</item>', '<item name="android:ellipsize">end</item>', content)
    content = re.sub(r'<item\s+name="android:scaleType"\s*>3</item>', '<item name="android:scaleType">fitCenter</item>', content)
    
    # 0, 1, 2 for enums
    content = re.sub(r'<item\s+name="android:datePickerMode"\s*>1</item>', '<item name="android:datePickerMode">spinner</item>', content)
    content = re.sub(r'<item\s+name="android:datePickerMode"\s*>2</item>', '<item name="android:datePickerMode">calendar</item>', content)
    
    content = re.sub(r'<item\s+name="android:visibility"\s*>0</item>', '<item name="android:visibility">visible</item>', content)
    content = re.sub(r'<item\s+name="android:visibility"\s*>1</item>', '<item name="android:visibility">invisible</item>', content)
    content = re.sub(r'<item\s+name="android:visibility"\s*>2</item>', '<item name="android:visibility">gone</item>', content)
    
    content = re.sub(r'<item\s+name="android:inputType"\s*>0x2</item>', '<item name="android:inputType">number</item>', content)

    content = re.sub(r'<item\s+name="android:breakStrategy"\s*>0</item>', '<item name="android:breakStrategy">simple</item>', content)
    content = re.sub(r'<item\s+name="android:breakStrategy"\s*>1</item>', '<item name="android:breakStrategy">high_quality</item>', content)
    content = re.sub(r'<item\s+name="android:breakStrategy"\s*>2</item>', '<item name="android:breakStrategy">balanced</item>', content)

    content = re.sub(r'<item\s+name="android:hyphenationFrequency"\s*>0</item>', '<item name="android:hyphenationFrequency">none</item>', content)
    content = re.sub(r'<item\s+name="android:hyphenationFrequency"\s*>1</item>', '<item name="android:hyphenationFrequency">normal</item>', content)
    content = re.sub(r'<item\s+name="android:hyphenationFrequency"\s*>2</item>', '<item name="android:hyphenationFrequency">full</item>', content)

    # Clean out references to missing abc_ drawables
    content = re.sub(r'^\s*<item[^>]*>@drawable/abc_.*?</item>\n?', '', content, flags=re.MULTILINE)

    return content

for file_path in styles_files:
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
            
        new_content = fix_content(content)
        
        if new_content != content:
            with open(file_path, 'w', encoding='utf-8') as f:
                f.write(new_content)
            print("Fixed", file_path)
    except Exception as e:
        print("Error processing", file_path, e)
