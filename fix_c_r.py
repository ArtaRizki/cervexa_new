import os
import re
import glob

def run():
    java_files = glob.glob('app/src/main/java/**/*.java', recursive=True)
    c_r_pattern = re.compile(r'^C\d+R\.java$')
    
    deleted_files = 0
    modified_files = 0
    
    for filepath in java_files:
        filename = os.path.basename(filepath)
        if c_r_pattern.match(filename):
            print(f"Deleting {filepath}")
            os.remove(filepath)
            deleted_files += 1
            continue
            
        with open(filepath, 'r', encoding='utf-8') as f:
            content = f.read()
            
        original_content = content
        
        # Replace `CXXXXR.` with `com.weioa.KmedHealthIndonesia.R.`
        content = re.sub(r'\bC\d+R\.', 'com.weioa.KmedHealthIndonesia.R.', content)
        
        # Remove imports of `CXXXXR`
        content = re.sub(r'import\s+[a-zA-Z0-9_\.]+\.C\d+R;\s*', '', content)
        
        if content != original_content:
            with open(filepath, 'w', encoding='utf-8') as f:
                f.write(content)
            modified_files += 1

    print(f"Deleted {deleted_files} CXXXXR.java files.")
    print(f"Modified {modified_files} java files to use com.weioa.KmedHealthIndonesia.R.")

if __name__ == '__main__':
    run()
