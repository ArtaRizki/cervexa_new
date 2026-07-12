import re

log_path = r'C:\Users\arta\.gemini\antigravity-ide\brain\636e6082-1539-4a60-abdd-c2f3d06fcea0\.system_generated\tasks\task-1584.log'
with open(log_path, 'r', encoding='utf-8') as f:
    text = f.read()

matches = re.findall(r'R\.styleable\.([A-Za-z0-9_]+)', text)
styleables = {}
for m in set(matches):
    if '_' in m:
        group, attr = m.split('_', 1)
        if group not in styleables:
            styleables[group] = set()
        styleables[group].add(attr)
    else:
        if m not in styleables:
            styleables[m] = set()

for g, attrs in styleables.items():
    print(f'<declare-styleable name="{g}">')
    for a in sorted(attrs):
        print(f'    <attr name="{a}" />')
    print(f'</declare-styleable>')
