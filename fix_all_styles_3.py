import re
import os
import glob

res_dir = 'app/src/main/res/'
styles_files = glob.glob(res_dir + '**/styles.xml', recursive=True)

def fix_content(content):
    # Dimensions
    content = re.sub(r'>-1<', '>match_parent<', content)
    content = re.sub(r'>-2<', '>wrap_content<', content)
    
    # Missing abc_ and mtrl_ resources of all types (layout, anim, color, etc.)
    content = re.sub(r'^\s*<item[^>]*>@\w+/(?:abc_|mtrl_|notification_).*?</item>\n?', '', content, flags=re.MULTILINE)

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
