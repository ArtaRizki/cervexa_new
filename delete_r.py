import os
import re

java_dir = r'd:\INFORMATICS\FREELANCE\cervexa_new\app\src\main\java'

c_r_file_pattern = re.compile(r'^C\d{4}R\.java$')

deleted_files = 0
for root, dirs, files in os.walk(java_dir):
    for file in files:
        if c_r_file_pattern.match(file):
            filepath = os.path.join(root, file)
            os.remove(filepath)
            deleted_files += 1
            print(f"Deleted {filepath}")
        elif file == 'R.java' and 'KmedHealthIndonesia' in root:
            filepath = os.path.join(root, file)
            os.remove(filepath)
            deleted_files += 1
            print(f"Deleted {filepath}")

print(f"Total deleted: {deleted_files}")
