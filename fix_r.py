import os
import re

java_dir = r'd:\INFORMATICS\FREELANCE\cervexa_new\app\src\main\java'

c_r_import_pattern = re.compile(r'import\s+[\w\.]+\.C\d{4}R;')
c_r_usage_pattern = re.compile(r'C\d{4}R\.')

count = 0
for root, dirs, files in os.walk(java_dir):
    for file in files:
        if file.endswith('.java') and not file.endswith('R.java'):
            filepath = os.path.join(root, file)
            with open(filepath, 'r', encoding='utf-8') as f:
                content = f.read()
            
            new_content = content
            
            # Replace all C...R. with R.
            if c_r_usage_pattern.search(new_content):
                new_content = c_r_usage_pattern.sub('R.', new_content)
                new_content = c_r_import_pattern.sub('import com.weioa.KmedHealthIndonesia.R;', new_content)
                
                # Make sure com.weioa.KmedHealthIndonesia.R is imported if not already
                if 'import com.weioa.KmedHealthIndonesia.R;' not in new_content:
                    package_match = re.search(r'package\s+[\w\.]+;', new_content)
                    if package_match:
                        insert_pos = package_match.end()
                        new_content = new_content[:insert_pos] + '\nimport com.weioa.KmedHealthIndonesia.R;' + new_content[insert_pos:]
            
            if new_content != content:
                with open(filepath, 'w', encoding='utf-8') as f:
                    f.write(new_content)
                count += 1

print(f'Done! Modified {count} files.')
