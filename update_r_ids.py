import os
import re

java_dir = r'd:\INFORMATICS\FREELANCE\cervexa_new\app\src\main\java'
r_txt_path = r'd:\INFORMATICS\FREELANCE\cervexa_new\app\build\intermediates\runtime_symbol_list\debug\processDebugResources\R.txt'

# Parse R.txt
r_dict = {}
with open(r_txt_path, 'r', encoding='utf-8') as f:
    for line in f:
        parts = line.strip().split(' ', 3)
        if len(parts) >= 4 and parts[0] == 'int':
            res_type = parts[1]
            res_name = parts[2]
            res_value = parts[3]
            if res_type not in r_dict:
                r_dict[res_type] = {}
            r_dict[res_type][res_name] = res_value

# Regexes
class_pattern = re.compile(r'public static final class (\w+) \{')
field_pattern = re.compile(r'(\s*public static final int\s+)(\w+)(\s*=\s*)(0x[0-9a-fA-F]+|-?\d+)(;)')

count = 0
for root, dirs, files in os.walk(java_dir):
    for file in files:
        if file.endswith('R.java'):
            filepath = os.path.join(root, file)
            with open(filepath, 'r', encoding='utf-8') as f:
                lines = f.readlines()
            
            modified = False
            current_type = None
            
            for i, line in enumerate(lines):
                class_match = class_pattern.search(line)
                if class_match:
                    current_type = class_match.group(1)
                    continue
                
                if current_type:
                    field_match = field_pattern.search(line)
                    if field_match:
                        name = field_match.group(2)
                        if current_type in r_dict and name in r_dict[current_type]:
                            new_val = r_dict[current_type][name]
                            old_val = field_match.group(4)
                            if not (old_val == new_val or (old_val.isdigit() and hex(int(old_val)) == new_val)):
                                lines[i] = field_pattern.sub(rf'\g<1>\g<2>\g<3>{new_val}\g<5>', line)
                                modified = True

            if modified:
                with open(filepath, 'w', encoding='utf-8') as f:
                    f.writelines(lines)
                count += 1

print(f"Updated {count} R.java files with new AAPT2 IDs.")
