import os
import re

java_dir = r'd:\INFORMATICS\FREELANCE\cervexa_new\app\src\main\java'

c_r_import_pattern = re.compile(r'import\s+[\w\.]+\.C\d{4}R;')
c_r_usage_pattern = re.compile(r'C\d{4}R\.(layout|id|string|drawable|attr|color|dimen|anim|array|bool|integer|mipmap|raw|xml|menu)\.')

count = 0
for root, dirs, files in os.walk(java_dir):
    for file in files:
        if file.endswith('.java') and not file.endswith('R.java'):
            filepath = os.path.join(root, file)
            with open(filepath, 'r', encoding='utf-8') as f:
                content = f.read()
            
            new_content = content
            
            # Replace C...R.type. with com.weioa.KmedHealthIndonesia.R.type.
            if c_r_usage_pattern.search(new_content):
                new_content = c_r_usage_pattern.sub(r'com.weioa.KmedHealthIndonesia.R.\1.', new_content)
                # Note: We intentionally do NOT remove the import of C...R because it might still be used for C...R.styleable!
            
            if new_content != content:
                with open(filepath, 'w', encoding='utf-8') as f:
                    f.write(new_content)
                count += 1

print(f'Done! Modified {count} files.')
